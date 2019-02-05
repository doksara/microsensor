package hr.foi.air.microsensor.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.ButterKnife;
import hr.foi.air.microsensor.AttendanceModule;
import hr.foi.air.microsensor.R;

public class QRCodeModuleFragment extends Fragment implements AttendanceModule {

    FormAttendanceSubmission form;

    public QRCodeModuleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_qrcode_module, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button  = (Button) view.findViewById(R.id.scan_barcode_button);

        ButterKnife.bind(this, view);
    }

    @Override
    public void setData(Integer idUser, Integer idSchedule, FormAttendanceSubmission f) {
        form = f;
    }

    @Override
    public Fragment getFragment() {
        return this;
    }

    @Override
    public String getModuleID() {
        return "mQRModule";
    }


}
