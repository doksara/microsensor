package hr.foi.air.microsensor.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import hr.foi.air.microsensor.FragmentIntentIntegrator;
import butterknife.ButterKnife;
import hr.foi.air.microsensor.AttendanceModule;
import hr.foi.air.microsensor.R;

public class QRCodeModuleFragment extends Fragment implements AttendanceModule {

    Button btnScan;
    TextView tv_qr_readTxt;

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
        this.btnScan = view.findViewById(R.id.btnScan);
        this.tv_qr_readTxt = view.findViewById(R.id.tv_qr_readTxt);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IntentIntegrator integrator = new FragmentIntentIntegrator(QRCodeModuleFragment.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();

            }
        });

        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents() == null){
                Log.d("MainActivity", "Cancelled scan");

            } else {
               Log.d("MainActivity", "Scanned: " + result.getContents());
                Toast.makeText(getActivity(), "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                this.tv_qr_readTxt.setText(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void setData(Integer idUser, Integer idSchedule, FormAttendanceSubmission f) {

    }

    @Override
    public Fragment getFragment() { return this; }

    @Override
    public String getModuleID() { return "mQRModule"; }
}
