package hr.foi.air.webservice.Weather;

import hr.foi.air.webservice.Data.DataLoader;
import hr.foi.air.webservice.Data.DataObservable;
import hr.foi.air.webservice.Interface.WebserviceInterface;
import retrofit2.Call;

public class WeatherSender extends DataLoader {
    public void sendWeather(WebserviceInterface webserviceInterface, int temp, String dvorana, String email)
    {
        Call<WeatherResponse> call = webserviceInterface.sendData("posaljiPodatke", temp, dvorana, email);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call call, retrofit2.Response response)
    {
        if(response.isSuccessful())
        {
            WeatherResponse stanjeDataResponse = (WeatherResponse) response.body();
            String message = stanjeDataResponse.getMessage();
            DataObservable.getInstance(). notifyObserverWithResponse(message);
        }
        else
        {
            System.out.println(response.errorBody());
        }
    }
}
