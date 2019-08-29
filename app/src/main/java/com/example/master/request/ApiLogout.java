package com.example.master.request;

import retrofit2.http.POST;

public interface ApiLogout {
    @POST("/logout")
    void postLogout();
}
