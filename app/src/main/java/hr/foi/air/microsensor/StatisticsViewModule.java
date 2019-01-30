package hr.foi.air.microsensor;

import android.support.v4.app.Fragment;

import java.util.List;

import hr.foi.air.webservice.Weather.Weather;

public interface StatisticsViewModule {

    /**
     * Sets the data for the fragment.
     * @param weatherList The data as {@link List&lt;Weather&gt;} to be shown in module.
     */
    public void setData(List<Weather> weatherList);

    /**
     * Displays the current module in parent fragment.
     */
    public void displayModule();

    /**
     * Gets the current fragment.
     * @return Current fragment as {@link Fragment}
     */
    public Fragment getFragment();

    /**
     * Gets the unique module ID.
     * @return Unique module ID as {@link String}.
     */
    public String getModuleID();
}
