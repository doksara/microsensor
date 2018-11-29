package hr.foi.air.webservice.Interface;

import hr.foi.air.webservice.Weather.WeatherResponse;
import hr.foi.air.webservice.Student.StudentResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface WebserviceInterface {

    @FormUrlEncoded
    @POST("provjeraPrijave.php")
    Call<StudentResponse> checkLogin(
            @Field("opcija") String opcija,
            @Field("email") String email,
            @Field("lozinka") String lozinka
    );

    @FormUrlEncoded
    @POST("provjeraPrijave.php")
    Call<WeatherResponse> sendData(
            @Field("opcija") String opcija,
            @Field("temp") int temp,
            @Field("dvorana") String dvorana,
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("provjeraPrijave.php")
    Call<WeatherResponse> getData(
            @Field("opcija") String opcija
    );
}
