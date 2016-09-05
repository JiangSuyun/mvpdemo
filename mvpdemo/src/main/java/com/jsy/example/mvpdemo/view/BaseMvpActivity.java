package com.jsy.example.mvpdemo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jsy.example.mvpdemo.presenter.BasePrsenter;

/**
 * Created by Administrator on 2016/9/5.
 */
public abstract class BaseMvpActivity<V, T extends BasePrsenter<V>> extends AppCompatActivity {

    public T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attach((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dettach();
    }

    public  abstract T initPresenter();
}
