package com.example.healthylifestylemobile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitAPI {

//    @POST("Users")
//    Call<UserModel> createUser(@Body ModelSendUser modelSendUser);

//    @GET("Users")
//    Call<UserModel> updateUser(@Query("id") int id, @Body Mask mask);

//    @GET("Users")
//    Call<UserModel> getUser(@Body UserModel UserModel);

    @GET("Users")
    Call<UserModel> Login(@Query("Login") String Login, @Query("Password") String Password);

//    @GET("Users")
//    Call<Boolean> examinationRegistration(@Query("Login") String Login);


}
