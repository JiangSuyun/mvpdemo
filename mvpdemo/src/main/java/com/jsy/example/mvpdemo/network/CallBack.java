package com.jsy.example.mvpdemo.network;

/**
 * Created by Administrator on 2016/9/5.
 */
public interface CallBack {

    void onSuccess(String data);
    void onFail(int code,String errorMsg);
}
