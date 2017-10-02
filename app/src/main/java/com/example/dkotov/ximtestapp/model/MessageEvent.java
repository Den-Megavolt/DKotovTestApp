package com.example.dkotov.ximtestapp.model;

import java.util.List;

/**
 * Created by dkotov on 26-Sep-17.
 */

public class MessageEvent {

    private final int mCode;
    private final String mType;
    private List<DataItem> mDataItems;

    public MessageEvent(int code, String type, List<DataItem> dataItems) {
        this.mCode = code;
        this.mType = type;
        this.mDataItems = dataItems;
    }

    public int getmCode() {
        return mCode;
    }

    public String getmType() {
        return mType;
    }

    public List<DataItem> getmDataItems() {
        return mDataItems;
    }
}
