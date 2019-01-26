package hr.foi.air.microsensor.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Timer;
import java.util.TimerTask;

import hr.foi.air.core.NavigationItem;
import hr.foi.air.microsensor.R;

public class RealtimeViewFragment extends Fragment implements NavigationItem {
    private boolean activeState = false;
    private boolean lastActiveState = false;
    Timer timer;
    NearbyBeaconFound beaconFoundScreen;
    NearbyBeaconNotFound beaconNotFoundScreen;
    FragmentTransaction fragmentTransaction;
    OnStateChanged dataPasser;

    private boolean moduleReadyFlag;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_realtime_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        beaconFoundScreen = new NearbyBeaconFound();
        beaconNotFoundScreen = new NearbyBeaconNotFound();

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                listenStateChanges();
            }
        },0,2000);

        this.moduleReadyFlag = true;
    }

    public void listenStateChanges(){
        if (this.activeState){
            switchScreen(beaconFoundScreen);
        } else {
            switchScreen(beaconNotFoundScreen);
        }

        this.lastActiveState = this.activeState;
        resetData();
        this.activeState = false;
    }

    public void resetData() {
        dataPasser.onStateChanged();
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

    public void switchScreen(Fragment f) {
        fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.realtime_container, f, "");
        fragmentTransaction.commit();
    }

    @Override
    public void setData(String optionalData) {
        if (this.moduleReadyFlag && !optionalData.equals("-1;0;0;0;0"))
        {
            this.activeState = true;
            beaconFoundScreen.setData(optionalData);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataPasser = (OnStateChanged) context;
    }


    @Override
    public void onStop(){
        super.onStop();
        timer.cancel();
    }

    public interface OnStateChanged {
        public void onStateChanged();
    }
}
