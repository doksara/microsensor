package hr.foi.air.webservice.Attendance;

import hr.foi.air.webservice.Data.DataLoader;
import hr.foi.air.webservice.Data.DataObservable;
import hr.foi.air.webservice.Interface.WebserviceInterface;
import retrofit2.Call;

public class LectureLoader extends DataLoader {
    /**
     * Uses the Webservice interface to make a HTTP call to the server script "dohvatiPredavanje".
     * @param webserviceInterface Webservice interface used to make a HTTP call
     * @param dvorana ID of the hall passed to the HTTP call
     */
    public void getLecture(WebserviceInterface webserviceInterface, int dvorana)
    {
        Call<LectureResponse> call = webserviceInterface.getLecture(dvorana);
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
            LectureResponse predavanjeDataResponse = (LectureResponse) response.body();
            DataObservable.getInstance(). notifyObserverWithResponse(predavanjeDataResponse);
        }
        else
        {
            System.out.println(response.errorBody());
        }
    }
}
