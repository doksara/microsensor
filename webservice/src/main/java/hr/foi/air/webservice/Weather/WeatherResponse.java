package hr.foi.air.webservice.Weather;

import java.util.List;

import hr.foi.air.webservice.Data.DataResponse;

public class WeatherResponse extends DataResponse {
    List<Weather> data;

    /**
     * Gets list of the class Weather.
     * @return List of Weather as {@link List<Weather>}
     */
    public List<Weather> getData() {
        return data;
    }

    /**
     * Sets list of the class Weather.
     * @param data List of Weathers
     */
    public void setData(List<Weather> data) {
        this.data = data;
    }
}
