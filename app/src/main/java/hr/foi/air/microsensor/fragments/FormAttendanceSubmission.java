package hr.foi.air.microsensor.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.OnClick;
import hr.foi.air.microsensor.R;
import hr.foi.air.webservice.Attendance.AttendanceObservable;
import hr.foi.air.webservice.Attendance.AttendanceSender;

public class FormAttendanceSubmission extends Fragment implements Observer {
    private int idLecture;
    private int idUser;
    @BindView(R.id.textCurrentSubject) TextView mCurrentSubject;
    @BindView(R.id.textCurrentHall) TextView mCurrentHall;

    public FormAttendanceSubmission() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        controller.sendAttendance(controller.create(), this.idLecture, this.idUser);
    }

    public void setIdLecture(int idLecture){
        this.idLecture = idLecture;
    }

    public void setIdUser(int idUser){
        this.idUser = idUser;
    }

    public void setData(String currentSubject, String currentHall){
        this.mCurrentSubject.setText(currentSubject);
        this.mCurrentHall.setText(currentHall);
    }

    @Override
    public void update(Observable o, Object arg){

    }
}
