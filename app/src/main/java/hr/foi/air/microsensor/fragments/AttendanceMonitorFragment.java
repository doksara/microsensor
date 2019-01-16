package hr.foi.air.microsensor.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import hr.foi.air.core.NavigationItem;
import hr.foi.air.webservice.Attendance.Attendance;
import hr.foi.air.microsensor.R;
import hr.foi.air.webservice.Attendance.Subject;
import hr.foi.air.microsensor.adapters.ExpandableSubjectItem;
import hr.foi.air.microsensor.adapters.SubjectAttendanceRecyclerAdapter;
import hr.foi.air.webservice.Attendance.AttendanceLoader;
import hr.foi.air.webservice.Attendance.AttendanceResponse;
import hr.foi.air.webservice.Attendance.tempAttendance;
import hr.foi.air.webservice.Data.DataObservable;


public class AttendanceMonitorFragment extends Fragment implements NavigationItem, Observer {
    SubjectAttendanceRecyclerAdapter mAdapter;
    List<tempAttendance> tempAttendanceList;
    List<ExpandableSubjectItem> subjectItems;
    private boolean moduleReadyFlag;
    private boolean dataReadyFlag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_attendance_monitor, container, false);
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
        return context.getString(R.string.attendance_monitor_module_name);
    }

    @Override
    public Drawable getIcon(Context context) {
        return context.getResources().getDrawable(R.drawable.ic_event_note, context.getTheme());
    }

    private void tryToDisplayData(){
        if (moduleReadyFlag && dataReadyFlag){
            displayData();
        }
    }

    @Override
    public void setData(String optionalData) {
        String[] rawData = optionalData.split(";");
        Log.d("MainActivity", String.valueOf(rawData.length));
        DataObservable.getInstance().addObserver(this);
        AttendanceLoader controller = new AttendanceLoader();
        controller.getAttendance(controller.create(), Integer.parseInt(rawData[4]));
}

    @Override
    public void update(Observable o, Object arg) {
        AttendanceResponse attendanceResponse = (AttendanceResponse) arg;

        if(attendanceResponse.getData() != null)
        {
            tempAttendanceList = new ArrayList<>();
            tempAttendanceList = attendanceResponse.getData();
            this.dataReadyFlag = true;
            tryToDisplayData();
        }
        else {
            Toast.makeText(getActivity(), attendanceResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }
        DataObservable.getInstance().deleteObserver(this);
    }

    public void displayData(){
        subjectItems = new ArrayList<>();

        List<Subject> subjectList = new ArrayList<>();
        for (tempAttendance temp : tempAttendanceList)
        {
            List<Attendance> attendanceList = new ArrayList<>();
            for (String datum : temp.getDatum())
            {
                Attendance newAttendance = new Attendance(datum);
                attendanceList.add(newAttendance);
            }
            Subject subject = new Subject(temp.getNaziv(), attendanceList);
            subjectList.add(subject);
        }

        for (Subject subject : subjectList)
            subjectItems.add(new ExpandableSubjectItem(subject));

        RecyclerView mExpandableRecycler = (RecyclerView) getActivity().findViewById(R.id.mSubjectsRecycler);
        if (mExpandableRecycler != null) {
            mAdapter = new SubjectAttendanceRecyclerAdapter(getActivity(), subjectItems);
            LinearLayoutManager manager = new LinearLayoutManager(getActivity());
            mExpandableRecycler.setLayoutManager(manager);
            mExpandableRecycler.setHasFixedSize(true);
            mExpandableRecycler.setAdapter(mAdapter);
        }
    }
}
