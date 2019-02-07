package hr.foi.air.microsensor.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.microsensor.AttendanceModule;
import hr.foi.air.microsensor.R;
import hr.foi.air.webservice.Attendance.AttendanceObservable;
import hr.foi.air.webservice.Attendance.AttendanceSender;

public class FormAttendanceSubmission extends Fragment implements Observer {
    private int idSchedule;
    private int idUser;
    public String pin = "";
    private boolean moduleReadyFlag = false;
    AttendanceSubmissionFragment parentFragment;
    String subjectName;
    String hallName;
    String subjectType;
    TextView mCurrentSubject;
    TextView mCurrentHall;
    TextView mSubjectType;
    FragmentTransaction fragmentTransaction;
    List<AttendanceModule> moduleContainer;

    /**
     * Default empty constructor.
     */
    public FormAttendanceSubmission() {
        // Required empty public constructor
    }

    public void setPin(String p){
        this.pin = p;
    }

    /**
     * Sets the parent fragment.
     * @param f Parent fragment as {@link AttendanceSubmissionFragment}
     */
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
        moduleContainer = new ArrayList<>();
        moduleContainer.add(new CodeModuleFragment());
        moduleContainer.add(new QRCodeModuleFragment());
        moduleContainer.add(new PinAttendanceFragment());
        moduleContainer.get(0).setData(idUser, idSchedule, this);
        moduleContainer.get(1).setData(idUser, idSchedule, this);
        moduleContainer.get(2).setData(idUser, idSchedule, this);
        moduleReadyFlag = true;
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
        controller.sendAttendance(controller.create(), this.idSchedule, this.idUser, this.pin);
    }

    /**
     * Sets the ID of the schedule related to lecture.
     * @param idSchedule ID of the Schedule as {@link int}
     */
    public void setIdSchedule(int idSchedule){
        this.idSchedule = idSchedule;
    }

    /**
     * Sets the ID of the current logged user.
     * @param idUser ID of the current user as {@link int}
     */
    public void setIdUser(int idUser){
        this.idUser = idUser;
    }

    /**
     * Sets the data for the fragment.
     * @param currentSubject Name of the current subject being performed as {@link String}
     * @param currentHall ID of the current hall in which the subject is being performed as {@link String}
     * @param subjectType Type of the current subject (pass 'L' for Laboratorijske vjezbe, 'P' for Predavanje and 'S' for Seminar)
     */
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

    /**
     * Displays the current fragment with its data.
     */
    public void displayFragment()
    {
        this.mCurrentSubject.setText(this.subjectName);
        this.mCurrentHall.setText(this.hallName);
        this.mSubjectType.setText(this.subjectType);
    }


    void switchModule(AttendanceModule module) {
        fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.attendance_module_container, module.getFragment(), "");
        fragmentTransaction.commit();
    }

    @OnClick({R.id.mQRModule, R.id.mCodeModule, R.id.mPinModule})
    public void onRadioButtonClicked(RadioButton button) {
        boolean checked = button.isChecked();
        String moduleName = getActivity().getResources().getResourceEntryName(button.getId());

        for (AttendanceModule m : moduleContainer){
            if (m.getModuleID().equals(moduleName)){
                if (checked){
                    switchModule(m);
                }
            }
        }
    }

    /**
     * On received HTTP response, updates the fragment depending on response message.
     * @param o Observer that is subscribed to subject
     * @param arg Object that needs to be casted to {@link String} to see the message from response
     */
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
            case "Pogresan PIN!":
            {
                Toast.makeText(getContext(), "Pogrešan PIN!", Toast.LENGTH_SHORT).show();
                this.parentFragment.setUserAttendsSubjectStatus(false);
                break;
            }
            default: break;
        }
    }
}
