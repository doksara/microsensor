package hr.foi.air.microsensor.adapters;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.webservice.Attendance.Attendance;
import hr.foi.air.microsensor.R;

public class AttendanceViewHolder extends ChildViewHolder {
    @BindView(R.id.mAttendanceDate) TextView mAttendanceDate;

    Attendance mAttendance;
    View mAttendanceItemView;
    SubjectAttendanceRecyclerAdapter mAdapter;

    /**
     * Default constructor.
     * @param itemView The {@link View} being hosted in this ViewHolder
     * @param adapter The {@link SubjectAttendanceRecyclerAdapter} responsible for recycling views
     */
    public AttendanceViewHolder(@NonNull View itemView, SubjectAttendanceRecyclerAdapter adapter) {
        super(itemView);
        mAttendanceItemView = itemView;
        mAdapter = adapter;
        ButterKnife.bind(this, itemView);
    }

    /**
     * Binds the {@link View} with data provided in {@link Attendance}.
     * @param attendance Instance of {@link Attendance} which the view will be bind with
     */
    public void bind(Attendance attendance){
        mAttendance = attendance;
        mAttendanceDate.setText(attendance.getDate());
    }
}
