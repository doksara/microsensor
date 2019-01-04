package hr.foi.air.microsensor.adapters;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.microsensor.R;
import hr.foi.air.microsensor.Subject;

public class SubjectAttendanceViewHolder extends ParentViewHolder {
    @BindView(R.id.mSubjectName) TextView mSubjectName;
    @BindView(R.id.mSubjectAttendanceCount) TextView mSubjectAttendanceCount;

    View mSubjectItemView;

    public SubjectAttendanceViewHolder(@NonNull View itemView) {
        super(itemView);
        mSubjectItemView = itemView;
        ButterKnife.bind(this, itemView);
    }

    public void bind(Subject subject){
        mSubjectName.setText(subject.getName());
        int numberOfAttendances = subject.getAttendanceList().size();
        mSubjectAttendanceCount.setText(mSubjectItemView.getContext().getResources().getString(R.string.textNumberOfAttendances, numberOfAttendances));
    }
}
