package com.example.master;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface API {
    @POST("/auth/login")
    Call<Post> postData(@Body RegistrationBody data);
}
