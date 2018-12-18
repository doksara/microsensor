package hr.foi.air.webservice.Attendance;

import hr.foi.air.webservice.Data.DataLoader;
import hr.foi.air.webservice.Data.DataObservable;
import hr.foi.air.webservice.Data.DataResponse;
import hr.foi.air.webservice.Interface.WebserviceInterface;
import retrofit2.Call;

public class AttendanceSender extends DataLoader {
    public void sendAttendance(WebserviceInterface webserviceInterface, int kolegij, int korisnik)
    {
        Call<DataResponse> call = webserviceInterface.setAttendance(kolegij, korisnik);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call call, retrofit2.Response response)
    {
        if(response.isSuccessful())
        {
            DataResponse stanjeDataResponse = (DataResponse) response.body();
            String message = stanjeDataResponse.getMessage();
            AttendanceObservable.getInstance(). notifyObserverWithResponse(message);
        }
        else
        {
            System.out.println(response.errorBody());
        }
    }
}
