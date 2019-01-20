package hr.foi.air.microsensor;

import android.support.v4.app.Fragment;

import java.util.List;

import hr.foi.air.webservice.Weather.Weather;

public interface StatisticsViewModule {
    public void setData(List<Weather> weatherList);
    public void displayModule();
    public Fragment getFragment();
    public String getModuleID();
}
