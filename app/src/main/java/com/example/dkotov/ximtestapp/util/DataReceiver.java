package com.example.dkotov.ximtestapp.util;

import android.net.Uri;
import android.util.Log;

import com.example.dkotov.ximtestapp.client.API;
import com.example.dkotov.ximtestapp.client.ApiClient;
import com.example.dkotov.ximtestapp.model.DataResponse;
import com.example.dkotov.ximtestapp.model.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dkotov on 26-Sep-17.
 */

public class DataReceiver {

    private static final String TAG = "XimTestApp: " + DataReceiver.class.getSimpleName();

    public static void receiveData(final String type) {

        Uri uri = Uri.parse(API.BASE_PATH).buildUpon()
                .appendQueryParameter("query", type).build();

        API.DataService dataService = ApiClient.getClient().create(API.DataService.class);
        Call<DataResponse> dataResponseCall = dataService.getData(uri.toString());
        Log.d(TAG, "Attempt to get data at address: " + uri.toString());

        dataResponseCall.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                Log.d(TAG, "Received data for type " +  type + " . " + response.body());
                DataResponse dataResponse = response.body();
                MessageEvent event = new MessageEvent(0, type, dataResponse.getData());
                EventBus.getDefault().postSticky(event);
                Log.d(TAG, "Request successful, event posted");
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable throwable) {
                Log.d(TAG, "Failed to receive data. " + throwable.getMessage());
                MessageEvent event = new MessageEvent(1, type, null);
                EventBus.getDefault().postSticky(event);
                Log.d(TAG, "Request failed, error event posted");
            }
        });
    }
}
