package hr.foi.air.webservice.Student;

import java.util.List;

import hr.foi.air.webservice.Data.DataLoader;
import hr.foi.air.webservice.Interface.WebserviceInterface;
import retrofit2.Call;

public class StudentLoader extends DataLoader {

    public void loadStudent(WebserviceInterface webserviceInterface, String email, String lozinka)
    {
        Call<StudentResponse> call = webserviceInterface.checkLogin("provjeriPrijavu", email, lozinka);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call call, retrofit2.Response response)
    {
        if(response.isSuccessful())
        {
            StudentResponse studentDataResponse = (StudentResponse) response.body();
            List<Student> list = studentDataResponse.getData();
            StudentObservable.getInstance(). notifyObserverWithResponse(list);
        }
        else
        {
            System.out.println(response.errorBody());
        }
    }
}
