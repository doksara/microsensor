package hr.foi.air.microsensor.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.core.NavigationItem;
import hr.foi.air.microsensor.R;
import hr.foi.air.microsensor.adapters.MeasurementRecyclerAdapter;
import hr.foi.air.webservice.Data.DataObservable;
import hr.foi.air.webservice.Weather.Weather;
import hr.foi.air.webservice.Weather.WeatherLoader;
import hr.foi.air.webservice.Weather.WeatherResponse;


public class StatisticsViewFragment extends Fragment implements NavigationItem, Observer {
    MeasurementRecyclerAdapter mAdapter;
    private List<Weather> weatherList;
    private boolean moduleReadyFlag;
    private boolean dataReadyFlag;
    FragmentTransaction fragmentTransaction;
    ListModuleFragment listModule;
    GraphModuleFragment graphModule;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistics_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listModule = new ListModuleFragment();
        graphModule = new GraphModuleFragment();
        this.moduleReadyFlag = true;

        ButterKnife.bind(this, view);
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
        // trenutno ovaj dio radi preko imena dvorane a ne idDvorane
        String[] rawData = optionalData.split(";");
        DataObservable.getInstance().addObserver(this);
        WeatherLoader controller = new WeatherLoader();
        controller.loadWeather(controller.create(), rawData[0], rawData[1]);
    }

    public void tryToDisplayData() {
        if (moduleReadyFlag && dataReadyFlag) {
            switchToListModule();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        WeatherResponse weatherResponse = (WeatherResponse) arg;

        if(weatherResponse.getData() != null)
        {
            weatherList = new ArrayList<>();
            weatherList = weatherResponse.getData();
            listModule.setData(weatherList);
            this.dataReadyFlag = true;
            tryToDisplayData();
        }
        else {
            Toast.makeText(getActivity(), weatherResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }
        DataObservable.getInstance().deleteObserver(this);
    }

    void switchToListModule() {
        fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.statistics_module_container, listModule, "");
        fragmentTransaction.commit();
    }

    void switchToGraphModule() {
        fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.statistics_module_container, graphModule, "");
        fragmentTransaction.commit();
    }

    @OnClick({R.id.mSelectListModule, R.id.mSelectGraphModule})
    public void onRadioButtonClicked(RadioButton button) {
        boolean checked = button.isChecked();

        switch (button.getId()) {
            case R.id.mSelectListModule:
                if (checked){
                    switchToListModule();
                }
                break;
            case R.id.mSelectGraphModule:
                if (checked){
                    switchToGraphModule();
                }
                break;
        }
    }
}
