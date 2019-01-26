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
import hr.foi.air.webservice.Weather.WeatherResponse;

public class AttendanceSubmissionFragment extends Fragment implements NavigationItem, Observer {
    String currentSubject;
    String currentHall;
    FragmentTransaction fragmentTransaction;
    FormAttendanceSubmission formAttendanceSubmission;
    MessageAttendanceSubmitted messageAttendanceSubmitted;
    MessageNoLecture messageNoLecture;

    private boolean moduleReadyFlag = false;

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
        messageNoLecture = new MessageNoLecture();

        ButterKnife.bind(this, view);
        moduleReadyFlag = true;
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
                            formAttendanceSubmission.setData(currentSubject, currentHall);
                            formAttendanceSubmission.setParentFragment(this);
                            formAttendanceSubmission.setIdLecture(list.get(0).getIdKolegij());
                        }
                        switchFragment(formAttendanceSubmission);
                    }
                    break;
                default: break;
            }
            DataObservable.getInstance().deleteObserver(this);
        }
    }
}

