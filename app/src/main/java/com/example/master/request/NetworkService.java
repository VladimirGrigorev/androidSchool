package com.example.master.request;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService mInstance;
    private static final String BASE_URL = "http://demo3161256.mockable.io";
    private Retrofit mRetrofit;

    private NetworkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    public ApiAuth getJSONApiAuth() {
        return mRetrofit.create(ApiAuth.class);
    }
    public ApiMemes getJSONApi() {
        return mRetrofit.create(ApiMemes.class);
    }
}