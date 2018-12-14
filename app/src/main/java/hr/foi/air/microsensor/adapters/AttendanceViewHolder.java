package hr.foi.air.microsensor.adapters;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.microsensor.Attendance;
import hr.foi.air.microsensor.R;

public class AttendanceViewHolder extends ChildViewHolder {
    @BindView(R.id.mAttendanceDate) TextView mAttendanceDate;

    Attendance mAttendance;
    View mAttendanceItemView;
    SubjectAttendanceRecyclerAdapter mAdapter;

    public AttendanceViewHolder(@NonNull View itemView, SubjectAttendanceRecyclerAdapter adapter) {
        super(itemView);
        mAttendanceItemView = itemView;
        mAdapter = adapter;
        ButterKnife.bind(this, itemView);
    }

    public void bind(Attendance attendance){
        mAttendance = attendance;
        mAttendanceDate.setText(attendance.getDate());
    }
}
