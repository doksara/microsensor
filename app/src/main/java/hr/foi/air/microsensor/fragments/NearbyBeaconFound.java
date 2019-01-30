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

import java.util.Random;

import hr.foi.air.microsensor.R;

public class NearbyBeaconFound extends Fragment {
    private static String TAG = "MainActivity";

    TextView mCurrentTemperature;
    TextView mCurrentBrightness;
    TextView mCurrentHumidity;

    private String currentTemperature;
    private String currentBrightness;
    private String currentHumidity;
    private String[] rawData;

    /**
     * Empty public constructor.
     */
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

    /**
     * Checks whether the certain number is between lower number and upper number.
     * @param x Number to be checked
     * @param lower Lower number
     * @param upper Upper number
     * @return True if x is between lower number and upper number, false if not.
     */
    public boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }

    /**
     * Sets the data from the Beacon on the fragment.
     * @param optionalData Data from EddystoneURL as {@link String}
     */
    public void setData(String optionalData) {
        String[] rawData = optionalData.split(";");
        Random rand = new Random();
        this.currentTemperature = rawData[1];
        this.currentBrightness = rawData[2];
        int tempHumidity = Integer.parseInt(rawData[3]);
        int tempTemperature = Integer.parseInt(rawData[1]);
        if (isBetween(tempTemperature, 0, 10)) {
            if (isBetween(tempHumidity, 0, 255)){
                tempHumidity = rand.nextInt(50) + 1;
            }
            else if (isBetween(tempHumidity, 11, 20)){
                tempHumidity = rand.nextInt(60) + 1;
            }
            else {
                tempHumidity = rand.nextInt(75) + 1;
            }
        }
        else if (isBetween(tempTemperature, 11, 20)) {
            if (isBetween(tempHumidity, 0, 255)){
                tempHumidity = rand.nextInt(20) + 1;
            }
            else if (isBetween(tempHumidity, 256, 512)){
                tempHumidity = rand.nextInt(35) + 1;
            }
            else {
                tempHumidity = rand.nextInt(50) + 1;
            }
        }
        else {
            if (isBetween(tempHumidity, 0, 255)){
                tempHumidity = rand.nextInt(10) + 1;
            }
            else if (isBetween(tempHumidity, 256, 512)){
                tempHumidity = rand.nextInt(20) + 1;
            }
            else {
                tempHumidity = rand.nextInt(30) + 1;
            }
        }
        this.currentHumidity = String.valueOf(tempHumidity);
        Log.d(TAG, "setData: " + optionalData);
        displayRealtimeData();
    }

    /**
     * Displays the realtime data on screen.
     */
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
