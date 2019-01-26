package hr.foi.air.microsensor.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hr.foi.air.microsensor.R;

public class NearbyBeaconFound extends Fragment {
    private static String TAG = "MainActivity";

    TextView mCurrentTemperature;
    TextView mCurrentBrightness;
    TextView mCurrentHumidity;

    private String currentTemperature;
    private String currentBrightness;
    private String currentHumidity;

    public NearbyBeaconFound() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_nearby_beacon_found, container, false);
        mCurrentTemperature = rootView.findViewById(R.id.mCurrentTemperature);
        mCurrentBrightness = rootView.findViewById(R.id.mCurrentBrightness);
        mCurrentHumidity = rootView.findViewById(R.id.mCurrentHumidity);
        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setData(String optionalData) {
        String[] rawData = optionalData.split(";");
        this.currentTemperature = rawData[1];
        this.currentBrightness = rawData[2];
        this.currentHumidity = rawData[3];
        Log.d(TAG, "setData: " + optionalData);
        displayRealtimeData();
    }

    public void displayRealtimeData() {
        if (getActivity() != null)
        {
             mCurrentTemperature.setText(getString(R.string.currentTemperature, currentTemperature));
             mCurrentTemperature.invalidate();

             mCurrentBrightness.setText(getString(R.string.currentBrightness, currentBrightness));
             mCurrentBrightness.invalidate();

             mCurrentHumidity.setText(getString(R.string.currentHumidity, currentHumidity));
             mCurrentHumidity.invalidate();
        }
    }
}
