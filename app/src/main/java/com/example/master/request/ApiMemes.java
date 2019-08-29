package com.example.master.request;

import com.example.master.structure.MemeInfo;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.ArrayList;

public interface ApiMemes {
    @GET("/memes")
    Call<ArrayList<MemeInfo>> getPostDataMemes();
}
