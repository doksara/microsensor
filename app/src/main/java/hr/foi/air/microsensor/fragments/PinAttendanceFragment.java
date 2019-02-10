package hr.foi.air.microsensor.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goodiebag.pinview.Pinview;
import hr.foi.air.microsensor.R;
import butterknife.ButterKnife;
import hr.foi.air.microsensor.AttendanceModule;

public class PinAttendanceFragment extends Fragment implements AttendanceModule {
    private Pinview pinview;
    private FormAttendanceSubmission form;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_attendance_pin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pinview = (Pinview) view.findViewById(R.id.mypinview);

        pinview.setPinViewEventListener(new Pinview.PinViewEventListener() {
            @Override
            public void onDataEntered(Pinview pinview, boolean b) {
                //Toast.makeText(getContext(),pinview.getValue(), Toast.LENGTH_SHORT).show();
                form.setPin(pinview.getValue());
            }
        });
        ButterKnife.bind(this, view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setData(FormAttendanceSubmission f){
        form = f;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Fragment getFragment(){
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getModuleID(){
        return "mPinModule";
    }

}
