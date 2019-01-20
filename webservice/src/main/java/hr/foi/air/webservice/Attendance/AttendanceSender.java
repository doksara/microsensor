package hr.foi.air.webservice.Attendance;

import android.util.Log;

import hr.foi.air.webservice.Data.DataLoader;
import hr.foi.air.webservice.Data.DataObservable;
import hr.foi.air.webservice.Data.DataResponse;
import hr.foi.air.webservice.Interface.WebserviceInterface;
import retrofit2.Call;

public class AttendanceSender extends DataLoader {
    public void sendAttendance(WebserviceInterface webserviceInterface, int kolegij, int korisnik)
    {
        Call<AttendanceResponse> call = webserviceInterface.setAttendance(kolegij, korisnik);
        call.enqueue(this);
    }

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
