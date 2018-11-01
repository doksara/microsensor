package hr.foi.air.webservice;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StudentDataLoader implements Callback<List<Student>> {

    //Promijeniti URL webservisa
    static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private ArrayList<Student> reponseList;

    public void start(Activity activity)
    {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        StudentDataLoaderLIstener studentDataLoaderLIstener = retrofit.create(StudentDataLoaderLIstener.class);

        Call<List<Student>> call = studentDataLoaderLIstener.getUsers();
        call.enqueue(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onResponse(Call<List<Student>> call, Response<List<Student>> response)
    {
        if(response.isSuccessful())
        {
            reponseList = (ArrayList<Student>) response.body();
            StudentObservable.getInstance(). notifyObserverWithResponse(reponseList);
        }
        else
        {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<Student>> call, Throwable t)
    {
        t.printStackTrace();
    }
}
