package com.example.dkotov.ximtestapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dkotov on 26-Sep-17.
 */

public class DataItem {

    @SerializedName("url")
    private String url;

    @SerializedName("title")
    private String title;

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }
}
