package hr.foi.air.webservice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StudentDataLoaderLIstener {

    //Promijeniti URI za dohvaćanje sa našeg webservisa
    @GET("users/")
    Call<List<Student>> getUsers();
}
