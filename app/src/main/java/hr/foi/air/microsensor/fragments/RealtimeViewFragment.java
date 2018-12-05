package hr.foi.air.microsensor.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import butterknife.BindView;
import hr.foi.air.core.NavigationItem;
import hr.foi.air.microsensor.R;


public class RealtimeViewFragment extends Fragment implements NavigationItem {
    private static String TAG = "MainActivity";

    TextView mCurrentTemperature;
    TextView mCurrentBrightness;
    TextView mCurrentHumidity;

    private boolean moduleReadyFlag;
    private boolean dataReadyFlag;

    private String currentTemperature;
    private String currentBrightness;
    private String currentHumidity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_realtime_view, container, false);
        mCurrentTemperature = (TextView) rootView.findViewById(R.id.mCurrentTemperature);
        mCurrentBrightness = (TextView) rootView.findViewById(R.id.mCurrentBrightness);
        mCurrentHumidity = (TextView) rootView.findViewById(R.id.mCurrentHumidity);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.moduleReadyFlag = true;
    }

    private void tryToDisplayData(){
        if (moduleReadyFlag && dataReadyFlag){
            displayRealtimeData();
        }
    }

    @Override
    public Fragment getFragment() {
        return this;
    }

    @Override
    public String getName(Context context) {
        return context.getString(R.string.realtimeview_module_name);
    }

    @Override
    public Drawable getIcon(Context context) {
        return context.getResources().getDrawable(R.drawable.ic_import_export, context.getTheme());
    }

    @Override
    public void setData(String optionalData) {
        /*
         * Na ovom mjestu je potrebno implementirati logiku za postavljanje lokalnih podataka
         * kao što je npr trenutna ucionica, trenutna temperatura, vlaga zraka i razina svijetlosti
         * HINT: na kraju metode potrebno je staviti zastavicu dataReadyFlag na true kako bi dali do
         * znanja programu da su podaci spremni, a nakon toga pokusati prikazati podatke sa metodom
         * tryToDisplayData()
         **/
        String[] rawData = optionalData.split(";");
        this.currentTemperature = rawData[2];
        this.currentBrightness = rawData[3];
        this.currentHumidity = rawData[4];
        Log.d(TAG, "setData: " + optionalData);
        dataReadyFlag = true;
        tryToDisplayData();
    }

    public void displayRealtimeData() {
        /* Na ovom mjestu potrebno je implementirati logiku za prikaz trenutnih podataka na
         * korisničkom sučelju
         **/
        mCurrentTemperature.setText(currentTemperature);
        Log.d(TAG, currentTemperature);
        mCurrentBrightness.setText(currentBrightness);
        Log.d(TAG, currentBrightness);
        mCurrentHumidity.setText(currentHumidity);
        Log.d(TAG, currentHumidity);
    }
}
