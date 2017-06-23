package com.a11group.app_micro_finance_v1.trait;

import com.a11group.app_micro_finance_v1.model.Login;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MicrofinanceLoginInterface {

    @GET("login.php")
    Call<Login> login(@Query("email") String email, @Query("password") String password);

    /*
    //POST: Need to define the Simulator Model
    @POST("user/{userId}/simulator")
    Call<Income> simulator(@Path("userId") Long userId, @Body Simulator simulator);
    */
}
