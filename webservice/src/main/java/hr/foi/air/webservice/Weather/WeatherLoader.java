package hr.foi.air.webservice.Weather;

import java.util.ArrayList;
import java.util.List;

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
            /*List<Object> list = new ArrayList<>();
            list.add(stanjeDataResponse.getData());
            list.add(stanjeDataResponse.getMessage());*/
            DataObservable.getInstance(). notifyObserverWithResponse(stanjeDataResponse);
        }
        else
        {
            System.out.println(response.errorBody());
        }
    }
}
