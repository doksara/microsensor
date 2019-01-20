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
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.core.NavigationItem;
import hr.foi.air.microsensor.R;
import hr.foi.air.microsensor.StatisticsViewModule;
import hr.foi.air.webservice.Data.DataObservable;
import hr.foi.air.webservice.Weather.Weather;
import hr.foi.air.webservice.Weather.WeatherLoader;
import hr.foi.air.webservice.Weather.WeatherResponse;

public class StatisticsViewFragment extends Fragment implements NavigationItem, Observer {
    List<Weather> weatherList;
    private boolean moduleReadyFlag;
    private boolean dataReadyFlag;
    FragmentTransaction fragmentTransaction;
    List<StatisticsViewModule> moduleContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistics_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        moduleContainer = new ArrayList<>();
        moduleContainer.add(new ListModuleFragment());
        moduleContainer.add(new GraphModuleFragment());
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
        String[] rawData = optionalData.split(";");
        DataObservable.getInstance().addObserver(this);
        WeatherLoader controller = new WeatherLoader();
        controller.loadWeather(controller.create(), rawData[0]);
    }

    public void tryToDisplayData() {
        if (moduleReadyFlag && dataReadyFlag) {
            switchModule(moduleContainer.get(0));
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        WeatherResponse weatherResponse = (WeatherResponse) arg;

        if(weatherResponse.getData() != null)
        {
            weatherList = new ArrayList<>();
            weatherList = weatherResponse.getData();
            for (StatisticsViewModule m : moduleContainer){
                m.setData(weatherList);
            }
            this.dataReadyFlag = true;
            tryToDisplayData();
        }
        else {
            Toast.makeText(getActivity(), weatherResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }
        DataObservable.getInstance().deleteObserver(this);
    }

    void switchModule(StatisticsViewModule module) {
        fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.statistics_module_container, module.getFragment(), "");
        fragmentTransaction.commit();
    }

    @OnClick({R.id.mSelectListModule, R.id.mSelectGraphModule})
    public void onRadioButtonClicked(RadioButton button) {
        boolean checked = button.isChecked();
        String moduleName = getActivity().getResources().getResourceEntryName(button.getId());

        for (StatisticsViewModule m : moduleContainer){
            if (m.getModuleID().equals(moduleName)){
                if (checked){
                    switchModule(m);
                }
            }
        }
    }
}
