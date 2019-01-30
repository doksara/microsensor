package hr.foi.air.microsensor.adapters;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;


import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.microsensor.R;
import hr.foi.air.webservice.Attendance.Subject;

public class SubjectAttendanceViewHolder extends ParentViewHolder {
    @BindView(R.id.mSubjectName) TextView mSubjectName;
    @BindView(R.id.mSubjectAttendanceCount) TextView mSubjectAttendanceCount;
    @BindView(R.id.mSubjectTypeIcon) ImageView mSubjectTypeIcon;
    @BindView(R.id.mSubjectType) TextView mSubjectType;

    View mSubjectItemView;

    /**
     * Default constructor.
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public SubjectAttendanceViewHolder(@NonNull View itemView) {
        super(itemView);
        mSubjectItemView = itemView;
        ButterKnife.bind(this, itemView);
    }

    /**
     * Binds the {@link View} with data provided in {@link Subject}.
     * @param subject Instance of {@link Subject} which the view will be bind with
     */
    public void bind(Subject subject){
        String[] splitName = subject.getName().split("-");
        switch (splitName[1])
        {
            case "Labosi":
            {
                mSubjectTypeIcon.setBackgroundResource(R.drawable.icon_laboratorijske);
                mSubjectType.setText(mSubjectItemView.getContext().getResources().getString(R.string.textLaboratorijskeVjezbe));
                break;
            }
            case "Predavanje":
            {
                mSubjectTypeIcon.setBackgroundResource(R.drawable.icon_predavanje);
                mSubjectType.setText(mSubjectItemView.getContext().getResources().getString(R.string.textPredavanje));
                break;
            }
            case "Seminari":
            {
                mSubjectTypeIcon.setBackgroundResource(R.drawable.icon_seminari);
                mSubjectType.setText(mSubjectItemView.getContext().getResources().getString(R.string.textSeminari));
                break;
            }
            default: break;
        }
        mSubjectName.setText(splitName[0]);
        int numberOfAttendances = subject.getAttendanceList().size();
        mSubjectAttendanceCount.setText(mSubjectItemView.getContext().getResources().getString(R.string.textNumberOfAttendances, numberOfAttendances));
    }
}
