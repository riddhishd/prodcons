package com.messaging.dao;

/**
 * Created by riddhish on 19/01/17.
 */
public class Payload {
    String data;

    public Payload(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
