package hr.foi.air.microsensor.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.ButterKnife;
import hr.foi.air.core.NavigationItem;
import hr.foi.air.microsensor.R;
import hr.foi.air.webservice.Attendance.AttendanceObservable;
import hr.foi.air.webservice.Attendance.Lecture;
import hr.foi.air.webservice.Attendance.LectureLoader;
import hr.foi.air.webservice.Attendance.LectureResponse;
import hr.foi.air.webservice.Data.DataObservable;

public class AttendanceSubmissionFragment extends Fragment implements NavigationItem, Observer {
    String currentSubject;
    String currentHall;
    String subjectType;
    FragmentTransaction fragmentTransaction;
    FormAttendanceSubmission formAttendanceSubmission;
    MessageAttendanceSubmitted messageAttendanceSubmitted;
    MessageStudentNotInSubject messageStudentNotInSubject;
    MessageNoLecture messageNoLecture;
    private boolean beaconActiveState = false;
    private boolean attendanceSubmitted = false;
    private boolean userAttendsSubjectStatus = true;
    private boolean moduleReadyFlag = false;
    private boolean dataReadyFlag = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_attendance_submission, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        formAttendanceSubmission = new FormAttendanceSubmission();
        messageAttendanceSubmitted = new MessageAttendanceSubmitted();
        messageStudentNotInSubject = new MessageStudentNotInSubject();
        messageNoLecture = new MessageNoLecture();
        this.moduleReadyFlag = true;

        ButterKnife.bind(this, view);
    }

    @Override
    public void setBeaconState(boolean state){
        this.beaconActiveState = state;
        if (moduleReadyFlag){
            if (state && dataReadyFlag && !attendanceSubmitted && userAttendsSubjectStatus){
                switchFragment(formAttendanceSubmission);
            }
            else if (state && dataReadyFlag && !userAttendsSubjectStatus) {
                switchFragment(messageStudentNotInSubject);
            }
            else if (state && attendanceSubmitted){
                switchFragment(messageAttendanceSubmitted);
            }
            else {
                switchFragment(messageNoLecture);
            }
        }
    }

    @Override
    public Fragment getFragment() {
        return this;
    }

    @Override
    public String getName(Context context) {
        return context.getString(R.string.attendance_submission_module_name);
    }

    @Override
    public Drawable getIcon(Context context) {
        return context.getResources().getDrawable(R.drawable.ic_assignment_turned_in, context.getTheme());
    }

    @Override
    public void setData(String optionalData) {
        String[] rawData = optionalData.split(";");
        Log.d("MainActivity", optionalData);
        formAttendanceSubmission.setIdUser(Integer.parseInt(rawData[4]));
        DataObservable.getInstance().addObserver(this);
        LectureLoader controller = new LectureLoader();
        controller.getLecture(controller.create(), Integer.parseInt(rawData[0]));
    }

    public void switchFragment(Fragment f)
    {
        if (isAdded())
        {
            fragmentTransaction = getChildFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.attendance_submission_fragment_container, f, "");
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    public void setAttendanceStatus(boolean status){
        this.attendanceSubmitted = status;
    }

    public void setUserAttendsSubjectStatus(boolean status) { this.userAttendsSubjectStatus = status;}

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof AttendanceObservable)
        {
            String message = (String) arg;
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        }
        else
        {
            LectureResponse lectureResponse = (LectureResponse) arg;

            switch (lectureResponse.getMessage())
            {
                case "Query failed!":
                    switchFragment(messageNoLecture);
                    break;
                case "Data arrived!":
                    {
                        List<Lecture> list;
                        if(!lectureResponse.getData().isEmpty())
                        {
                            list = lectureResponse.getData();
                            currentSubject = list.get(0).getKolegij();
                            currentHall = list.get(0).getDvorana();
                            subjectType = list.get(0).getTip();
                            formAttendanceSubmission.setData(currentSubject, currentHall, subjectType);
                            formAttendanceSubmission.setParentFragment(this);
                            formAttendanceSubmission.setIdSchedule(list.get(0).getIdRaspored());
                            this.dataReadyFlag = true;
                        }
                        if (!attendanceSubmitted){
                            switchFragment(formAttendanceSubmission);
                        } else {
                            switchFragment(messageAttendanceSubmitted);
                        }
                    }
                    break;
                default: break;
            }
            DataObservable.getInstance().deleteObserver(this);
        }
    }
}

