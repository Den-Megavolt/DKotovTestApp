package com.example.dkotov.ximtestapp.client;

import com.example.dkotov.ximtestapp.model.DataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by dkotov on 26-Sep-17.
 */

public class API {

    public static final String BASE_PATH = "http://kot3.com/xim/api.php";

    public interface DataService {

        @GET
        Call<DataResponse> getData(@Url String path);
    }


}
