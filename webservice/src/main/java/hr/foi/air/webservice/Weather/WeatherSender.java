package hr.foi.air.webservice.Weather;

import hr.foi.air.webservice.Data.DataLoader;
import hr.foi.air.webservice.Student.StudentObservable;
import hr.foi.air.webservice.Interface.WebserviceInterface;
import retrofit2.Call;

public class WeatherSender extends DataLoader {
    public void sendWeather(WebserviceInterface webserviceInterface, int temp, int svjetlo, int vlaznost, String zgrada, String dvorana)
    {
        Call<WeatherResponse> call = webserviceInterface.sendData(temp, svjetlo, vlaznost, zgrada, dvorana);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call call, retrofit2.Response response)
    {
        if(response.isSuccessful())
        {
            WeatherResponse stanjeDataResponse = (WeatherResponse) response.body();
            String message = stanjeDataResponse.getMessage();
            WeatherSenderObservable.getInstance(). notifyObserverWithResponse(message);
        }
        else
        {
            System.out.println(response.errorBody());
        }
    }
}
