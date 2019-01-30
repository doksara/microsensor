package hr.foi.air.webservice.Student;

import android.util.Log;

import java.util.List;

import hr.foi.air.webservice.Data.DataLoader;
import hr.foi.air.webservice.Data.DataObservable;
import hr.foi.air.webservice.Interface.WebserviceInterface;
import retrofit2.Call;

public class StudentLoader extends DataLoader {

    /**
     * Uses the Webservice interface to make a HTTP call to the server script "provjeriPrijavu".
     * @param webserviceInterface Webservice interface used to make a HTTP call
     * @param email Email of the Student passed to the HTTP call
     * @param lozinka Password of the Student passed to the HTTP call
     */
    public void loadStudent(WebserviceInterface webserviceInterface, String email, String lozinka)
    {
        Call<StudentResponse> call = webserviceInterface.checkLogin("provjeriPrijavu", email, lozinka);
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
            StudentResponse studentDataResponse = (StudentResponse) response.body();
            List<Student> list = studentDataResponse.getData();
            DataObservable.getInstance(). notifyObserverWithResponse(list);
        }
        else
        {
            Log.d("MainActivity", response.errorBody().toString());
        }
    }
}
