package hr.foi.air.webservice.Weather;

import java.util.ArrayList;
import java.util.List;

import hr.foi.air.webservice.Data.DataLoader;
import hr.foi.air.webservice.Student.StudentObservable;
import hr.foi.air.webservice.Interface.WebserviceInterface;
import retrofit2.Call;
import retrofit2.Response;

public class WeatherLoader extends DataLoader {
    public void loadWeather(WebserviceInterface webserviceInterface, String zgrada, String dvorana)
    {
        Call<WeatherResponse> call = webserviceInterface.getData(zgrada, dvorana);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call call, retrofit2.Response response)
    {
        if(response.isSuccessful())
        {
            WeatherResponse stanjeDataResponse = (WeatherResponse) response.body();
            List<Object> list = new ArrayList<>();
            list.add(stanjeDataResponse.getData());
            list.add(stanjeDataResponse.getMessage());
            WeatherDataObservable.getInstance(). notifyObserverWithResponse(list);
        }
        else
        {
            System.out.println(response.errorBody());
        }
    }
}
