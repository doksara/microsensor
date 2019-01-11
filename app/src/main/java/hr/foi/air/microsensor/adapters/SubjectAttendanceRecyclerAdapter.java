package hr.foi.air.microsensor.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;

import java.util.List;

import hr.foi.air.webservice.Attendance.Attendance;
import hr.foi.air.microsensor.R;

public class SubjectAttendanceRecyclerAdapter extends ExpandableRecyclerAdapter<ExpandableSubjectItem, Attendance, SubjectAttendanceViewHolder, AttendanceViewHolder> {
    private LayoutInflater mInflater;

    public SubjectAttendanceRecyclerAdapter(Context context, @NonNull List<ExpandableSubjectItem> parentList) {
        super(parentList);
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SubjectAttendanceViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View storeView = mInflater.inflate(R.layout.subject_list_item, parentViewGroup, false);
        return new SubjectAttendanceViewHolder(storeView);
    }

    @NonNull
    @Override
    public AttendanceViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View attendanceView = mInflater.inflate(R.layout.attendance_list_item, childViewGroup, false);
        return new AttendanceViewHolder(attendanceView, this);
    }

    @Override
    public void onBindParentViewHolder(@NonNull SubjectAttendanceViewHolder parentViewHolder, int parentPosition, @NonNull ExpandableSubjectItem parent) {
        parentViewHolder.bind((ExpandableSubjectItem) parent);
    }

    @Override
    public void onBindChildViewHolder(@NonNull AttendanceViewHolder childViewHolder, int parentPosition, int childPosition, @NonNull Attendance child) {
        childViewHolder.bind(child);
    }
}
