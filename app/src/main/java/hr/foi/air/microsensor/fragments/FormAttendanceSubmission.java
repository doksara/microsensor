package hr.foi.air.microsensor.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.microsensor.R;
import hr.foi.air.webservice.Attendance.AttendanceObservable;
import hr.foi.air.webservice.Attendance.AttendanceSender;

public class FormAttendanceSubmission extends Fragment implements Observer {
    private int idSchedule;
    private int idUser;
    AttendanceSubmissionFragment parentFragment;
    String subjectName;
    String hallName;
    String subjectType;
    TextView mCurrentSubject;
    TextView mCurrentHall;
    TextView mSubjectType;
    FragmentTransaction fragmentTransaction;

    public FormAttendanceSubmission() {
        // Required empty public constructor
    }

    public void setParentFragment(AttendanceSubmissionFragment f)
    {
        this.parentFragment = f;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mCurrentHall = view.findViewById(R.id.textCurrentHall);
        this.mCurrentSubject = view.findViewById(R.id.textCurrentSubject);
        this.mSubjectType = view.findViewById(R.id.textSubjectType);
        ButterKnife.bind(this, view);
        displayFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form_attendance_submission, container, false);
    }

    @OnClick(R.id.mSubmitAttendance)
    public void submitAttendance()
    {
        AttendanceObservable.getInstance().addObserver(this);
        AttendanceSender controller = new AttendanceSender();
        controller.sendAttendance(controller.create(), this.idSchedule, this.idUser);
    }

    public void setIdSchedule(int idSchedule){
        this.idSchedule = idSchedule;
    }

    public void setIdUser(int idUser){
        this.idUser = idUser;
    }

    public void setData(String currentSubject, String currentHall, String subjectType){
        this.subjectName = currentSubject;
        this.hallName = currentHall;
        switch (subjectType)
        {
            case "S": this.subjectType = "Seminarska nastava"; break;
            case "P": this.subjectType = "Predavanje"; break;
            case "L": this.subjectType = "Laboratorijske vjezbe"; break;
        }
    }

    public void displayFragment()
    {
        this.mCurrentSubject.setText(this.subjectName);
        this.mCurrentHall.setText(this.hallName);
        this.mSubjectType.setText(this.subjectType);
    }

    @Override
    public void update(Observable o, Object arg){
        String message = (String) arg;
        Log.d("MainActivity", message);
        switch (message)
        {
            case "Prisustvo je vec prijavljeno!":
            {
                MessageAttendanceSubmitted messageAttendanceSubmitted = new MessageAttendanceSubmitted();
                this.parentFragment.setAttendanceStatus(true);
                this.parentFragment.switchFragment(messageAttendanceSubmitted);
                break;
            }
            case "Korisnik ne pohadja kolegij!":
            {
                MessageStudentNotInSubject messageStudentNotInSubject = new MessageStudentNotInSubject();
                this.parentFragment.setUserAttendsSubjectStatus(false);
                this.parentFragment.switchFragment(messageStudentNotInSubject);
                break;
            }
            default: break;
        }
    }
}
