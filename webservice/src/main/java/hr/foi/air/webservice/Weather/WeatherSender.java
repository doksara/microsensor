package hr.foi.air.webservice.Weather;

import hr.foi.air.webservice.Data.DataLoader;
import hr.foi.air.webservice.Data.DataObservable;
import hr.foi.air.webservice.Interface.WebserviceInterface;
import retrofit2.Call;

public class WeatherSender extends DataLoader {

    /**
     * Uses the Webservice interface to make a HTTP call to the server script "zapisiMjerenje".
     * @param webserviceInterface Webservice interface used to make a HTTP call
     * @param idDvorana ID of the hall passed to the HTTP call
     * @param temp Temperature passed to the HTTP call
     * @param svjetlo Light intensity passed to the HTTP call
     * @param vlaznost Air humidity passed to the HTTP call
     */
    public void sendWeather(WebserviceInterface webserviceInterface, int idDvorana, int temp, int svjetlo, int vlaznost)
    {
        Call<WeatherResponse> call = webserviceInterface.sendData(idDvorana, temp, svjetlo, vlaznost);
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
            String message = stanjeDataResponse.getMessage();
            DataObservable.getInstance(). notifyObserverWithResponse(message);
        }
        else
        {
            System.out.println(response.errorBody());
        }
    }
}
