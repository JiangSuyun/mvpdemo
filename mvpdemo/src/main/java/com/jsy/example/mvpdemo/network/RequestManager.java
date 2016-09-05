package com.jsy.example.mvpdemo.network;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jsy.example.mvpdemo.MvpApplication;

import java.util.Map;

/**
 * Created by Administrator on 2016/9/5.
 */
public class RequestManager {

    private volatile RequestQueue mQueue;

    private static RequestManager sManager;

    private RequestManager(){
        mQueue = Volley.newRequestQueue(MvpApplication.getInstance());
    }

    public static RequestManager getInstance(){
        Log.d("jsy","demo RequestManager getInstance");
        if(sManager == null){
            synchronized (RequestManager.class){
                if(sManager == null){
                    sManager = new RequestManager();
                }
            }
        }

        return sManager;
    }

    public void requestGet(String url, Map<String,String> params, final CallBack callBack){
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onFail(-1,error.getMessage());
            }
        });

        mQueue.add(request);
    }



}
