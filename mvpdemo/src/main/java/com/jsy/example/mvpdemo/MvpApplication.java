package com.jsy.example.mvpdemo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Created by Administrator on 2016/9/5.
 */
public class MvpApplication extends Application {

    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("jsy"," demo MvpApplication onCreate");
        mContext = this;
    }

   public static Context getInstance(){
        return mContext;
    }
}
