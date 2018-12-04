package hr.foi.air.microsensor.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.icu.util.Measure;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import hr.foi.air.core.NavigationItem;
import hr.foi.air.microsensor.Measurement;
import hr.foi.air.microsensor.R;
import hr.foi.air.microsensor.adapters.MeasurementRecyclerAdapter;


public class StatisticsViewFragment extends Fragment implements NavigationItem {
    MeasurementRecyclerAdapter mAdapter;
    private List<Measurement> measurementArrayList;
    private boolean moduleReadyFlag;
    private boolean dataReadyFlag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fillList();
        return inflater.inflate(R.layout.fragment_statistics_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.moduleReadyFlag = true;

        RecyclerView mRecycler = (RecyclerView) getActivity().findViewById(R.id.mRecycleViewStatistics);
        mAdapter = new MeasurementRecyclerAdapter(getActivity(), measurementArrayList);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecycler.setLayoutManager(manager);
        mRecycler.setHasFixedSize(true);
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public Fragment getFragment() {
        return this;
    }

    @Override
    public String getName(Context context) {
        return context.getString(R.string.statisticsview_module_name);
    }

    @Override
    public Drawable getIcon(Context context) {
        return context.getResources().getDrawable(R.drawable.ic_insert_chart, context.getTheme());
    }

    @Override
    public void setData() {
        /*
         * Na ovom mjestu je potrebno implementirati logiku za postavljanje lokalnih podataka
         * prethodno dohvaćenih iz baze podataka (temp i vlaga)
         * HINT: na kraju metode potrebno je staviti zastavicu dataReadyFlag na true kako bi dali do
         * znanja programu da su podaci spremni, a nakon toga pokusati prikazati podatke sa metodom
         * tryToDisplayData()
         **/
        tryToDisplayData();
    }

    public void displayStatistics() {
        /* Na ovom mjestu potrebno je implementirati logiku za prikaz statističkih podataka na
         * korisničkom sučelju
         **/

        fillList();
    }

    public void tryToDisplayData() {
        if (moduleReadyFlag) {
            displayStatistics();
        }
    }

    public void fillList() {
        measurementArrayList = new ArrayList<>();

        Measurement newMeasurement = new Measurement("2018-11-27", 1.00, 23.00, 23.00);
        measurementArrayList.add(newMeasurement);
        newMeasurement = new Measurement("2018-11-27", 1.00, 23.00, 20.00);
        measurementArrayList.add(newMeasurement);
        newMeasurement = new Measurement("2018-11-28", 22.00, 17.00, 4.00);
        measurementArrayList.add(newMeasurement);
        newMeasurement = new Measurement("2018-11-29", 8.00, 20.00, 17.00);
        measurementArrayList.add(newMeasurement);
        newMeasurement = new Measurement("2018-11-30", 13.00, 21.00, 22.00);
        measurementArrayList.add(newMeasurement);
        newMeasurement = new Measurement("2018-12-01", 4.00, 23.00, 11.00);
        measurementArrayList.add(newMeasurement);
    }
}
