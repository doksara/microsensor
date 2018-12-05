package hr.foi.air.microsensor.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hr.foi.air.core.NavigationItem;
import hr.foi.air.microsensor.R;


public class AttendanceMonitorFragment extends Fragment implements NavigationItem {
    private boolean moduleReadyFlag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_attendance_monitor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.moduleReadyFlag = true;
    }

    @Override
    public Fragment getFragment() {
        return this;
    }

    @Override
    public String getName(Context context) {
        return context.getString(R.string.attendance_monitor_module_name);
    }

    @Override
    public Drawable getIcon(Context context) {
        return context.getResources().getDrawable(R.drawable.ic_event_note, context.getTheme());
    }

    @Override
    public void setData(String optionalData) {
       /*
        * Na ovom mjestu je potrebno implementirati logiku za postavljanje lokalnih podataka
        * koji su potrebni za prikazivanje prethodne prisutnosti trenutnog korisnika na predavanjima
        * HINT: na kraju metode potrebno je staviti zastavicu dataReadyFlag na true kako bi dali do
        * znanja programu da su podaci spremni, a nakon toga pokusati prikazati podatke sa metodom
        * tryToDisplayData()
        **/
    }
}
