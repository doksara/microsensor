package hr.foi.air.microsensor.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.OnClick;
import hr.foi.air.core.NavigationItem;
import hr.foi.air.microsensor.R;
import hr.foi.air.webservice.Attendance.AttendanceObservable;
import hr.foi.air.webservice.Attendance.AttendanceResponse;
import hr.foi.air.webservice.Attendance.AttendanceSender;
import hr.foi.air.webservice.Attendance.Lecture;
import hr.foi.air.webservice.Attendance.LectureLoader;
import hr.foi.air.webservice.Attendance.LectureResponse;
import hr.foi.air.webservice.Data.DataObservable;
import hr.foi.air.webservice.Data.DataResponse;

public class AttendanceSubmissionFragment extends Fragment implements NavigationItem, Observer {
    private boolean moduleReadyFlag;
    private boolean dataReadyFlag;
    private String currentSubject;
    private String currentHall;
    private int kolegij;
    private int korisnik;
    TextView mCurrentSubject;
    TextView mCurrentHall;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_realtime_view, container, false);
        mCurrentSubject = (TextView) rootView.findViewById(R.id.textCurrentSubject);
        mCurrentHall = (TextView) rootView.findViewById(R.id.textCurrentHall);
        return inflater.inflate(R.layout.fragment_attendance_submission, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.moduleReadyFlag = true;
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

    @OnClick(R.id.mSubmitAttendance)
    public void onClick(View view)
    {
        AttendanceObservable.getInstance().addObserver(this);
        AttendanceSender controller = new AttendanceSender();
        controller.sendAttendance(controller.create(), kolegij, korisnik);
    }

    @Override
    public void setData(String optionalData) {
        /*
         * Na ovom mjestu je potrebno implementirati logiku za postavljanje lokalnih podataka
         * kao što je npr trenutna ucionica, trenutna temperatura, vlaga zraka i razina svijetlosti
         * HINT: na kraju metode potrebno je staviti zastavicu dataReadyFlag na true kako bi dali do
         * znanja programu da su podaci spremni, a nakon toga pokusati prikazati podatke sa metodom
         * tryToDisplayData()
         **/
        String[] rawData = optionalData.split(";");
        korisnik = Integer.parseInt(rawData[5]);
        DataObservable.getInstance().addObserver(this);
        LectureLoader controller = new LectureLoader();
        controller.loadLecture(controller.create(), Integer.parseInt(rawData[1]));

    }

    private void tryToDisplayData(){
        if (moduleReadyFlag && dataReadyFlag){
            displayData();
        }
    }

    public void displayData()
    {
        mCurrentSubject.setText(currentSubject);
        mCurrentHall.setText(currentHall);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof AttendanceObservable)
        {
            DataResponse dataResponse = (DataResponse) arg;
            Toast.makeText(getActivity(), dataResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }
        else
        {
            LectureResponse lectureResponse = (LectureResponse) arg;
            List<Lecture> list;
            if(!lectureResponse.getData().isEmpty())
            {
                list = lectureResponse.getData();
                currentSubject = list.get(0).getKolegij();
                currentHall = list.get(0).getDvorana();
                kolegij = list.get(0).getIdKolegij();
                this.dataReadyFlag = true;
                tryToDisplayData();
            }
            else {
                Toast.makeText(getActivity(), lectureResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
            DataObservable.getInstance().deleteObserver(this);
        }
    }
}

