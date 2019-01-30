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

    /**
     * Default constructor.
     * @param context The {@link Context} of the View instancing this class
     * @param parentList The {@link List<ExpandableSubjectItem>} with all items for recycling
     */
    public SubjectAttendanceRecyclerAdapter(Context context, @NonNull List<ExpandableSubjectItem> parentList) {
        super(parentList);
        mInflater = LayoutInflater.from(context);
    }

    /**
     *
     * @param parentViewGroup
     * @param viewType
     * @return The instance of {@link SubjectAttendanceViewHolder} holding the view
     */
    @NonNull
    @Override
    public SubjectAttendanceViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View subjectView = mInflater.inflate(R.layout.subject_list_item, parentViewGroup, false);
        return new SubjectAttendanceViewHolder(subjectView);
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
