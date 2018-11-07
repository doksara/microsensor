package hr.foi.air.webservice;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StudentDataLoader implements Callback<List<Student>> {

    //Promijeniti URL webservisa
    static final String BASE_URL = "http://airmicrosensor.000webhostapp.com/";

    private List<Student> studentList;

    public void start(String email, String lozinka)
    {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        StudentDataLoaderLIstener studentDataLoaderLIstener = retrofit.create(StudentDataLoaderLIstener.class);

        Call<List<Student>> call = studentDataLoaderLIstener.checkLogin("provjeriPrijavu", email, lozinka);
        call.enqueue(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onResponse(Call<List<Student>> call, Response<List<Student>> response)
    {
        if(response.isSuccessful())
        {
            studentList = (List<Student>) response.body();
            Student student = studentList.get(0);
            StudentObservable.getInstance(). notifyObserverWithResponse(student);
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
