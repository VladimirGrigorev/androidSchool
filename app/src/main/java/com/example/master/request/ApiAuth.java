package com.example.master.request;

import com.example.master.structure.RegistrationBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiAuth {
    @POST("/auth/login")
    Call<PostAuth> postData(@Body RegistrationBody data);
}
