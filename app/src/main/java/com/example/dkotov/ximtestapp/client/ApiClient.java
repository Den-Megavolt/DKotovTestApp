package com.example.dkotov.ximtestapp.client;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dkotov on 26-Sep-17.
 */

public class ApiClient {

    private static final String BASE_URL = "http://kot3.com/xim/";

    private static final int TIMEOUT_SECONDS = 30;

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .build();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }

        return retrofit;
    }

}
