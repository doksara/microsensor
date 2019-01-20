package hr.foi.air.webservice.Weather;

import android.util.Log;

import hr.foi.air.webservice.Data.DataLoader;
import hr.foi.air.webservice.Data.DataObservable;
import hr.foi.air.webservice.Interface.WebserviceInterface;
import retrofit2.Call;

public class WeatherLoader extends DataLoader {
    public void loadWeather(WebserviceInterface webserviceInterface, String idDvorana)
    {
        Call<WeatherResponse> call = webserviceInterface.getData(idDvorana);
        call.enqueue(this);
    }

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
