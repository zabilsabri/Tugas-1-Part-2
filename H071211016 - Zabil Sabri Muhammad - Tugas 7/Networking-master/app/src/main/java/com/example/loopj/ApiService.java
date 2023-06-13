package com.example.loopj;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("users")
    Call<DataResponse> getUser(@Query("per_page") String per_page);

    @GET("users/{id}")
    Call<ProfileDataResponse> getUserData(@Path("id") String id);

    @POST("users")
    Call<UserJob> postUser(@Body UserJob userJob);
}