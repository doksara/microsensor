package hr.foi.air.webservice.Data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import hr.foi.air.webservice.Interface.WebserviceInterface;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class DataLoader implements retrofit2.Callback {
    //Promijeniti URL webservisa
    static final String BASE_URL = "http://airmicrosensor.000webhostapp.com/";

    public WebserviceInterface create()
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

        WebserviceInterface webserviceInterface = retrofit.create(WebserviceInterface.class);

        return webserviceInterface;
    }

    @Override
    public void onFailure(Call call, Throwable t)
    {
        t.printStackTrace();
    }
}
