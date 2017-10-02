package com.example.dkotov.ximtestapp;

import android.app.Application;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.Arrays;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;

/**
 * Created by dkotov on 27-Sep-17.
 */

public class XimTestApp extends Application {

    private static final String TAG = "XimTestApp: " + XimTestApp.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        setupPicasso();
    }

    private void setupPicasso() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .protocols(Arrays.asList(Protocol.HTTP_1_1, Protocol.HTTP_2));
        OkHttp3Downloader downloader = new OkHttp3Downloader(clientBuilder.build());
        Picasso picasso = new Picasso.Builder(this)
                .downloader(downloader)
                .build();

        Picasso.setSingletonInstance(picasso);
    }

}
