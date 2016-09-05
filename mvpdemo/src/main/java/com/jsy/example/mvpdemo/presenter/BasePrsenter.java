package com.jsy.example.mvpdemo.presenter;

/**
 * Created by Administrator on 2016/9/5.
 */
public  abstract class BasePrsenter<T> {

    public T mView;
    public void attach(T view){
        mView = view;
    }
    public void dettach(){
        mView = null;
    }
}
