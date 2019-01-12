package hr.foi.air.webservice.Attendance;

import hr.foi.air.webservice.Data.DataLoader;
import hr.foi.air.webservice.Data.DataObservable;
import hr.foi.air.webservice.Interface.WebserviceInterface;
import retrofit2.Call;

public class LectureLoader extends DataLoader {
    public void getLecture(WebserviceInterface webserviceInterface, int dvorana)
    {
        Call<LectureResponse> call = webserviceInterface.getLecture(dvorana);
        call.enqueue(this);
    }

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
