package iamtaxi.dmi.com.imtaxi.rest;

import java.util.List;

import iamtaxi.dmi.com.imtaxi.model.CabRequest;
import iamtaxi.dmi.com.imtaxi.model.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("employee/login")
    Call<String> getLoginAuth(@Query("email") String email, @Query("password") String password, @Query("type") String type);

    @POST("employee/request")
    Call<List<CabRequest>> createCabRequest(@Body CabRequest cabRequest);
}
