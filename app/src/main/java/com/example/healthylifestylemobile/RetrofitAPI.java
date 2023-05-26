package com.example.healthylifestylemobile;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface RetrofitAPI {

    @GET("Users")
    Call<Boolean> examinationRegistration(@Query("Login") String Login);

    @GET("Users")
    Call<UserModel> Login(@Query("Login") String Login, @Query("Password") String Password);

    @POST("Users")
    Call<UserModel> createUser(@Body UserModel userModel);
    @POST("DailyRations")
    Call<DailyRationsModel> createDaily(@Body DailyRationsModel DailyRationsModel);

    @GET("Users/{id}")
    Call<UserModel> getDATAUser(@Query("id") int id);


    @PUT("DailyRations")
    Call<DailyRationsModel> updateDaily(@Query("id") int id);
    @GET("DailyRations")
    Call<DailyRationsModel> getCaloriesUser(@Query("id") int id,@Query("idd") int idd);

    @PUT("Users")
    Call<UserModel> updateUser(@Query("id") int id, @Body UserModel userModel);

    @PUT("Users")
    Call<UserModel> updateLogin(@Query("id") int id, @Body UserModel userModel, @Query("idd") int idd);

    @GET("Recipes/{id}")
    Call<RecepesModel> getDATARecepes(@Query("id") int id);

    @PUT("Recipes")
    Call<RecepesModel> updateRecepis(@Query("id") int id, @Body RecepesModel recepesModel);

    @GET("Steps/{index}")
    Call<StepsModel> getDATASteps(@Query("id") int id);

    @PUT("Steps")
    Call<StepsModel> updateSteps(@Query("id") int id, @Body StepsModel stepsModel);

    @GET("IngredientForRecipes")
    Call<IngredientForRecipeModel> getDATAIng(@Query("index") int index);

    @GET("Steps")
    Call<StepsModel> getDATAStep(@Query("id") int id);

    @GET("Recipes/{id}")
    Call<RecepesModel> getDATARecip(@Query("id") int id);
}
