package hr.foi.air.webservice.Attendance;

import android.util.Log;

import hr.foi.air.webservice.Data.DataLoader;
import hr.foi.air.webservice.Data.DataObservable;
import hr.foi.air.webservice.Data.DataResponse;
import hr.foi.air.webservice.Interface.WebserviceInterface;
import retrofit2.Call;

public class AttendanceSender extends DataLoader {
    /**
     * Uses the Webservice interface to make a HTTP call to the server script "prijaviPrisustvo".
     * @param webserviceInterface Webservice interface used to make a HTTP call
     * @param kolegij class ID passed to the HTTP call
     * @param korisnik ID of the user passed to the HTTP call
     */
    public void sendAttendance(WebserviceInterface webserviceInterface, int kolegij, int korisnik)
    {
        Call<AttendanceResponse> call = webserviceInterface.setAttendance(kolegij, korisnik);
        call.enqueue(this);
    }

    /**
     * Function that parses data from the JSON response and notifies Observer class with the AttendanceObservable class.
     * @param call
     * @param response Response in JSON format received from the server
     */
    @Override
    public void onResponse(Call call, retrofit2.Response response)
    {
        if(response.isSuccessful())
        {
            AttendanceResponse stanjeDataResponse = (AttendanceResponse) response.body();
            String message = stanjeDataResponse.getMessage();
            AttendanceObservable.getInstance(). notifyObserverWithResponse(message);
        }
        else
        {
            Log.d("MainActivity", response.errorBody().toString());
        }
    }
}
