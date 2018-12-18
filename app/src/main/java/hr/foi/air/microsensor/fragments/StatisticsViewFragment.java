package hr.foi.air.microsensor.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import hr.foi.air.core.NavigationItem;
import hr.foi.air.microsensor.HomepageActivity;
import hr.foi.air.microsensor.MainActivity;
import hr.foi.air.microsensor.Measurement;
import hr.foi.air.microsensor.R;
import hr.foi.air.microsensor.adapters.MeasurementRecyclerAdapter;
import hr.foi.air.webservice.Data.DataObservable;
import hr.foi.air.webservice.Student.StudentLoader;
import hr.foi.air.webservice.Weather.Weather;
import hr.foi.air.webservice.Weather.WeatherLoader;
import hr.foi.air.webservice.Weather.WeatherResponse;


public class StatisticsViewFragment extends Fragment implements NavigationItem, Observer {
    MeasurementRecyclerAdapter mAdapter;
    private List<Weather> weathersArrayList;
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
    public void setData(String optionalData) {
        /*
         * Na ovom mjestu je potrebno implementirati logiku za postavljanje lokalnih podataka
         * prethodno dohvaćenih iz baze podataka (temp i vlaga)
         * HINT: na kraju metode potrebno je staviti zastavicu dataReadyFlag na true kako bi dali do
         * znanja programu da su podaci spremni, a nakon toga pokusati prikazati podatke sa metodom
         * tryToDisplayData()
         **/
        String[] rawData = optionalData.split(";");
        DataObservable.getInstance().addObserver(this);
        WeatherLoader controller = new WeatherLoader();
        controller.loadWeather(controller.create(), rawData[0], rawData[1]);
    }

    public void displayStatistics() {
        /* Na ovom mjestu potrebno je implementirati logiku za prikaz statističkih podataka na
         * korisničkom sučelju
         **/

        RecyclerView mRecycler = (RecyclerView) getActivity().findViewById(R.id.mRecycleViewStatistics);
        mAdapter = new MeasurementRecyclerAdapter(getActivity(), weathersArrayList);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecycler.setLayoutManager(manager);
        mRecycler.setHasFixedSize(true);
        mRecycler.setAdapter(mAdapter);
    }

    public void tryToDisplayData() {
        if (moduleReadyFlag && dataReadyFlag) {
            displayStatistics();
        }
    }

    public void fillList() {
        /*weathersArrayList = new ArrayList<>();

        Weather newWeather = new Weather("2018-11-27", 1.00, 23.00, 23.00);
        weathersArrayList.add(newWeather);
        newWeather = new Weather("2018-11-27", 1.00, 23.00, 20.00);
        weathersArrayList.add(newWeather);
        newWeather = new Weather("2018-11-28", 22.00, 17.00, 4.00);
        weathersArrayList.add(newWeather);
        newWeather = new Weather("2018-11-29", 8.00, 20.00, 17.00);
        weathersArrayList.add(newWeather);
        newWeather = new Weather("2018-11-30", 13.00, 21.00, 22.00);
        weathersArrayList.add(newWeather);
        newWeather = new Weather("2018-12-01", 4.00, 23.00, 11.00);
        weathersArrayList.add(newWeather);*/


    }

    @Override
    public void update(Observable o, Object arg) {
        WeatherResponse weatherResponse = (WeatherResponse) arg;
        /*List<Object> list = (List<Object>) arg;
        String message = (String) list.get(1);
        List<Weather> weatherList = (List<Weather>) list.get(0);*/
        if(!weatherResponse.getData().isEmpty())
        {
            weathersArrayList = new ArrayList<>();
            weathersArrayList = weatherResponse.getData();
            this.dataReadyFlag = true;
            tryToDisplayData();
        }
        else {
            Toast.makeText(getActivity(), weatherResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }
        DataObservable.getInstance().deleteObserver(this);
    }
}
