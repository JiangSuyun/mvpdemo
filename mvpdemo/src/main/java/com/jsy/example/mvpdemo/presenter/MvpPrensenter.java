package com.jsy.example.mvpdemo.presenter;

import android.content.Context;

import com.jsy.example.mvpdemo.model.MvpModel;
import com.jsy.example.mvpdemo.network.CallBack;
import com.jsy.example.mvpdemo.network.RequestManager;
import com.jsy.example.mvpdemo.view.MvpView;

/**
 * Created by Administrator on 2016/9/5.
 */
public class MvpPrensenter extends  BasePrsenter<MvpView> {
    private MvpModel mMvpModel;
    private Context mContext;

    public  MvpPrensenter(Context context){
        mContext = context;
        mMvpModel = new MvpModel();
    }

    public void clear(){
        mView.clear();
    }

    public void onResume(){
        loadData();
    }

    public void loadData(){
        RequestManager.getInstance().requestGet("http://www.baidu.com", null, new CallBack() {
            @Override
            public void onSuccess(String data) {
                mView.showMesssge(data);
            }

            @Override
            public void onFail(int code, String errorMsg) {

            }
        });
    }
}
