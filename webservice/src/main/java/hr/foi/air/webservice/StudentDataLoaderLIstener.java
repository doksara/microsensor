package hr.foi.air.webservice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface StudentDataLoaderLIstener {

    @FormUrlEncoded
    @POST("provjeraPrijave.php")
    Call<List<Student>> checkLogin(
            @Field("opcija") String opcija,
            @Field("email") String email,
            @Field("lozinka") String lozinka
    );
}
