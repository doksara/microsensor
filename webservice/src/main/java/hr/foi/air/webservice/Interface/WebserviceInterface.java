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
    @POST("zapisiMjerenje.php")
    Call<WeatherResponse> sendData(
            @Field("idDvorana") int idDvorana,
            @Field("temperatura") int temperatura,
            @Field("jacinaSvjetlosti") int jacinaSvjetlosti,
            @Field("vlaznostZraka") int vlaznostZraka
    );

    @FormUrlEncoded
    @POST("dohvatiStatistickePodatke.php")
    Call<WeatherResponse> getData(
            @Field("zgrada") String zgrada,
            @Field("dvorana") String dvorana
    );
}
