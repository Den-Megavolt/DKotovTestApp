package com.example.dkotov.ximtestapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dkotov on 26-Sep-17.
 */

public class DataResponse {

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<DataItem> data;

    public String getMessage() {
        return message;
    }

    public List<DataItem> getData() {
        return data;
    }
}
