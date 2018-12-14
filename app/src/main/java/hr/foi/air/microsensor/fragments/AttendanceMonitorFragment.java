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

import java.util.ArrayList;
import java.util.List;

import hr.foi.air.core.NavigationItem;
import hr.foi.air.microsensor.Attendance;
import hr.foi.air.microsensor.R;
import hr.foi.air.microsensor.Subject;
import hr.foi.air.microsensor.adapters.ExpandableSubjectItem;
import hr.foi.air.microsensor.adapters.SubjectAttendanceRecyclerAdapter;


public class AttendanceMonitorFragment extends Fragment implements NavigationItem {
    SubjectAttendanceRecyclerAdapter mAdapter;
    List<ExpandableSubjectItem> subjectItems;
    List<Subject> subjectList;
    private boolean moduleReadyFlag;
    private boolean dataReadyFlag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fillList();
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

    public void fillList(){
    }

    @Override
    public void setData(String optionalData) {
       dataReadyFlag = true;
       tryToDisplayData();
    }

    public void displayData(){
        subjectItems = new ArrayList<>();

        List<Attendance> attendanceList = new ArrayList<>();
        List<Subject> subjectList = new ArrayList<>();
        Attendance newAttendance = new Attendance("1.12.2018");
        attendanceList.add(newAttendance);
        newAttendance = new Attendance("1.12.2018");
        attendanceList.add(newAttendance);
        newAttendance = new Attendance("5.12.2018");
        attendanceList.add(newAttendance);
        newAttendance = new Attendance("7.12.2018");
        attendanceList.add(newAttendance);
        newAttendance = new Attendance("13.12.2018");
        attendanceList.add(newAttendance);
        Subject OS2 = new Subject("Operacijski sustavi", attendanceList);

        attendanceList.clear();
        newAttendance = new Attendance("15.11.2018");
        attendanceList.add(newAttendance);
        newAttendance = new Attendance("18.11.2018");
        attendanceList.add(newAttendance);
        newAttendance = new Attendance("21.11.2018");
        attendanceList.add(newAttendance);
        newAttendance = new Attendance("23.11.2018");
        attendanceList.add(newAttendance);
        Subject AIR = new Subject("Analiza i razvoj programa", attendanceList);

        subjectList.add(OS2);
        subjectList.add(AIR);

        if (subjectList != null){
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
}
