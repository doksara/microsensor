package hr.foi.air.webservice.Interface;

import hr.foi.air.webservice.Attendance.AttendanceResponse;
import hr.foi.air.webservice.Attendance.LectureResponse;
import hr.foi.air.webservice.Data.DataResponse;
import hr.foi.air.webservice.Weather.WeatherResponse;
import hr.foi.air.webservice.Student.StudentResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface WebserviceInterface {

    /**
     * POST call towards the server script "provjeraPrijave.php".
     * @param opcija Option that needs to have value "provjeriPrijavu" to return student data
     * @param email Email of the student
     * @param lozinka Password of the student
     * @return Retrofit response as {@link StudentResponse}
     */
    @FormUrlEncoded
    @POST("provjeraPrijave.php")
    Call<StudentResponse> checkLogin(
            @Field("opcija") String opcija,
            @Field("email") String email,
            @Field("lozinka") String lozinka
    );

    /**
     * POST call towards the server script "zapisiMjerenje.php".
     * @param idDvorana ID of the current hall
     * @param temperatura Temperature of the current reading
     * @param jacinaSvjetlosti Light intensity of the current reading
     * @param vlaznostZraka Air moisture of the current reading
     * @return Retrofit response as {@link WeatherResponse}
     */
    @FormUrlEncoded
    @POST("zapisiMjerenje.php")
    Call<WeatherResponse> sendData(
            @Field("idDvorana") int idDvorana,
            @Field("temperatura") int temperatura,
            @Field("jacinaSvjetlosti") int jacinaSvjetlosti,
            @Field("vlaznostZraka") int vlaznostZraka
    );

    /**
     * POST call towards the server script "dohvatiStatistickePodatke.php".
     * @param idDvorana ID of the current hall
     * @return Retrofit response as {@link WeatherResponse}
     */
    @FormUrlEncoded
    @POST("dohvatiStatistickePodatke.php")
    Call<WeatherResponse> getData(
            @Field("idDvorana") String idDvorana
    );

    /**
     * POST call towards the server script "prijaviPrisustvo.php".
     * @param idRaspored ID of the schedule
     * @param idKorisnik ID of the current user
     * @return Retrofit response as {@link AttendanceResponse}
     */
    @FormUrlEncoded
    @POST("prijaviPrisustvo.php")
    Call<AttendanceResponse> setAttendance(
            @Field("idRaspored") int idRaspored,
            @Field("idKorisnik") int idKorisnik
    );

    /**
     * POST call towards the server script "dohvatiEvidencijuPrisustva.php".
     * @param idKorisnik ID of the current user
     * @return Retrofit response as {@link AttendanceResponse}
     */
    @FormUrlEncoded
    @POST("dohvatiEvidencijuPrisustva.php")
    Call<AttendanceResponse> getAttendance(
            @Field("idKorisnik") int idKorisnik
    );

    /**
     * POST call towards the server script "dohvatiEvidencijuPrisustva.php".
     * @param idDvorana ID of the current hall
     * @return Retrofit response as {@link LectureResponse}
     */
    @FormUrlEncoded
    @POST("dohvatiPredavanje.php")
    Call<LectureResponse> getLecture(
            @Field("idDvorana") int idDvorana
    );
}
