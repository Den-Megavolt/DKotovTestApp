package com.example.dkotov.ximtestapp.model;

import java.util.List;

/**
 * Created by dkotov on 26-Sep-17.
 */

public class MessageEvent {

    private final int code;
    private final String type;
    private List<DataItem> dataItems;

    public MessageEvent(int code, String type, List<DataItem> dataItems) {
        this.code = code;
        this.type = type;
        this.dataItems = dataItems;
    }

    public int getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public List<DataItem> getDataItems() {
        return dataItems;
    }
}
