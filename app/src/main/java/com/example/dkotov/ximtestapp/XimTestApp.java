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
    private static int startCounter = 0;

    private static int catsPosition = 0;
    private static int dogsPosition = 0;

    public static void setStartCounter(int startCounter) {
        XimTestApp.startCounter = startCounter;
    }

    public static int getStartCounter() {
        return startCounter;
    }

    public static int getCatsPosition() {
        return catsPosition;
    }

    public static void setCatsPosition(int catsPosition) {
        XimTestApp.catsPosition = catsPosition;
    }

    public static int getDogsPosition() {
        return dogsPosition;
    }

    public static void setDogsPosition(int dogsPosition) {
        XimTestApp.dogsPosition = dogsPosition;
    }

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
