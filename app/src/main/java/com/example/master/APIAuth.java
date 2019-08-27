package com.example.master;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIAuth {
    @POST("/auth/login")
    Call<PostAuth> postData(@Body RegistrationBody data);
}
