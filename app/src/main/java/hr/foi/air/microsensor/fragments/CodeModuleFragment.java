package hr.foi.air.microsensor.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.ButterKnife;
import hr.foi.air.microsensor.AttendanceModule;
import hr.foi.air.microsensor.R;
import hr.foi.air.microsensor.StatisticsViewModule;

public class CodeModuleFragment extends Fragment implements AttendanceModule {

    FormAttendanceSubmission form;

    /**
     * Empty public constructor.
     */
    public CodeModuleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_code_module, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText unosKoda = (EditText) view.findViewById(R.id.unosKoda);
        String pin = unosKoda.getText().toString();
        form.setPin(pin);
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
    public String getModuleID() { return "mCodeModule";}

}
