package hr.foi.air.webservice.Weather;

import android.util.Log;

import hr.foi.air.webservice.Data.DataLoader;
import hr.foi.air.webservice.Data.DataObservable;
import hr.foi.air.webservice.Interface.WebserviceInterface;
import retrofit2.Call;

public class WeatherLoader extends DataLoader {

    /**
     * Uses the Webservice interface to make a HTTP call to the server script "dohvatiStatistickePodatke".
     * @param webserviceInterface Webservice interface used to make a HTTP call
     * @param idDvorana ID of the hall passed to the HTTP call
     */
    public void loadWeather(WebserviceInterface webserviceInterface, String idDvorana)
    {
        Call<WeatherResponse> call = webserviceInterface.getData(idDvorana);
        call.enqueue(this);
    }

    /**
     * Function that parses data from the JSON response and notifies Observer class with the DataObservable class.
     * @param call
     * @param response Response in JSON format received from the server
     */
    @Override
    public void onResponse(Call call, retrofit2.Response response)
    {
        if(response.isSuccessful())
        {
            WeatherResponse stanjeDataResponse = (WeatherResponse) response.body();
            DataObservable.getInstance(). notifyObserverWithResponse(stanjeDataResponse);
        }
        else
        {
            Log.d("MainActivity", response.errorBody().toString());
        }
    }
}
