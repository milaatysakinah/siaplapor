package com.app.siaplapor.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConnection {
    //sesuaikan dengan IP computer
    public static final String BASE_URL = "http://192.168.2.2:8000/api/";
    private static Retrofit retrofit = null;
    public static  Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
