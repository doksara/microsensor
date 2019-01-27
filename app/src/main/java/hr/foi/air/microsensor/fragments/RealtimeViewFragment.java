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

import hr.foi.air.core.NavigationItem;
import hr.foi.air.microsensor.R;

public class RealtimeViewFragment extends Fragment implements NavigationItem {
    NearbyBeaconFound beaconFoundScreen;
    NearbyBeaconNotFound beaconNotFoundScreen;
    FragmentTransaction fragmentTransaction;
    private boolean beaconActiveState = false;
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

        this.moduleReadyFlag = true;
    }

    @Override
    public void setBeaconState(boolean state){
        this.beaconActiveState = state;
        if (moduleReadyFlag){
            if (state){
                switchScreen(beaconFoundScreen);
            }
            else {
                switchScreen(beaconNotFoundScreen);
            }
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

    public void switchScreen(Fragment f) {
        if (isAdded()){
            fragmentTransaction = getChildFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.realtime_container, f, "");
            fragmentTransaction.commit();
        }
    }

    @Override
    public void setData(String optionalData) {
        if (moduleReadyFlag)
        {
            beaconFoundScreen.setData(optionalData);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
