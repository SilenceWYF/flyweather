package com.example.silence.flyweather.util;

/**
 * Created by Silence on 2016/3/29.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}