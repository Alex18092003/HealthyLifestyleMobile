package com.example.healthylifestylemobile;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitAPI {

    @GET("Users")
    Call<Boolean> examinationRegistration(@Query("Login") String Login);

    @GET("Users")
    Call<UserModel> Login(@Query("Login") String Login, @Query("Password") String Password);

    @POST("Users")
    Call<UserModel> createUser(@Body UserModel userModel);
}
