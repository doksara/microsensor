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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_realtime_view, container, false);
        mCurrentTemperature = rootView.findViewById(R.id.mCurrentTemperature);
        mCurrentBrightness = rootView.findViewById(R.id.mCurrentBrightness);
        mCurrentHumidity = rootView.findViewById(R.id.mCurrentHumidity);
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
        this.currentTemperature = rawData[1];
        this.currentBrightness = rawData[2];
        this.currentHumidity = rawData[3];
        Log.d(TAG, "setData: " + optionalData);
        dataReadyFlag = true;
        tryToDisplayData();
    }

    public void displayRealtimeData() {
        mCurrentTemperature.setText(getString(R.string.currentTemperature, currentTemperature));
        mCurrentTemperature.invalidate();

        mCurrentBrightness.setText(getString(R.string.currentBrightness, currentBrightness));
        mCurrentBrightness.invalidate();

        mCurrentHumidity.setText(getString(R.string.currentHumidity, currentHumidity));
        mCurrentHumidity.invalidate();
    }
}
