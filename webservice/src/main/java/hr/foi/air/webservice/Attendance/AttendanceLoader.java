package hr.foi.air.webservice.Attendance;

import java.util.ArrayList;
import java.util.List;

import hr.foi.air.webservice.Data.DataLoader;
import hr.foi.air.webservice.Data.DataObservable;
import hr.foi.air.webservice.Interface.WebserviceInterface;
import retrofit2.Call;

public class AttendanceLoader extends DataLoader {
    public void loadAttendance(WebserviceInterface webserviceInterface, int korisnik)
    {
        Call<AttendanceResponse> call = webserviceInterface.getAttendance(korisnik);
        call.enqueue(this);
    }

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
            System.out.println(response.errorBody());
        }
    }
}
