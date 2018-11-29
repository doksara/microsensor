package hr.foi.air.webservice.Weather;

import java.util.List;

import hr.foi.air.webservice.Data.DataLoader;
import hr.foi.air.webservice.Data.DataObservable;
import hr.foi.air.webservice.Interface.WebserviceInterface;
import retrofit2.Call;

public class WeatherLoader extends DataLoader {
    public void loadWeather(WebserviceInterface webserviceInterface)
    {
        Call<WeatherResponse> call = webserviceInterface.getData("preuzmiPodatke");
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call call, retrofit2.Response response)
    {
        if(response.isSuccessful())
        {
            WeatherResponse stanjeDataResponse = (WeatherResponse) response.body();
            List<Weather> list = stanjeDataResponse.getData();
            DataObservable.getInstance(). notifyObserverWithResponse(list);
        }
        else
        {
            System.out.println(response.errorBody());
        }
    }
}
