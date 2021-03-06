package hr.foi.air.webservice.Attendance;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import hr.foi.air.webservice.Data.DataLoader;
import hr.foi.air.webservice.Data.DataObservable;
import hr.foi.air.webservice.Interface.WebserviceInterface;
import retrofit2.Call;

public class AttendanceLoader extends DataLoader {
    /**
     * Uses the Webservice interface to make a HTTP call to the server script "dohvatiEvidencijuPrisustva".
     * @param webserviceInterface Webservice interface used to make a HTTP call
     * @param korisnik ID of the user passed to the HTTP call
     */
    public void getAttendance(WebserviceInterface webserviceInterface, int korisnik)
    {
        Call<AttendanceResponse> call = webserviceInterface.getAttendance(korisnik);
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
            AttendanceResponse pristustvoDataResponse = (AttendanceResponse) response.body();
            DataObservable.getInstance(). notifyObserverWithResponse(pristustvoDataResponse);
        }
        else
        {
            Log.d("MainActivity", response.errorBody().toString());
        }
    }
}
