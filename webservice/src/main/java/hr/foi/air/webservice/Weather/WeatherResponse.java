package hr.foi.air.webservice.Weather;

import java.util.List;

import hr.foi.air.webservice.Data.DataResponse;

public class WeatherResponse extends DataResponse {
    List<Weather> data;

    public List<Weather> getData() {
        return data;
    }

    public void setData(List<Weather> data) {
        this.data = data;
    }
}
