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

public class StudentDataLoader implements Callback<StudentResponse> {

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

        Call<StudentResponse> call = studentDataLoaderLIstener.checkLogin("provjeriPrijavu", email, lozinka);
        call.enqueue(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onResponse(Call<StudentResponse> call, Response<StudentResponse> response)
    {
        if(response.isSuccessful())
        {
            Student student;
            StudentResponse studentResponse = (StudentResponse) response.body();
            studentList = studentResponse.getData();
            StudentObservable.getInstance(). notifyObserverWithResponse(studentList);
        }
        else
        {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<StudentResponse> call, Throwable t)
    {
        t.printStackTrace();
    }
}
