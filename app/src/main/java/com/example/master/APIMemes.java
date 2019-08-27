package com.example.master;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.ArrayList;

public interface APIMemes {
    @GET("/memes")
    Call<ArrayList<MemeInfo>> getPostDataMemes();
}
