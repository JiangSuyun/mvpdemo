package com.jsy.example.mvpdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.jsy.example.mvpdemo.presenter.MvpPrensenter;
import com.jsy.example.mvpdemo.view.BaseMvpActivity;
import com.jsy.example.mvpdemo.view.MvpView;

public class MainActivity extends BaseMvpActivity<MvpView,MvpPrensenter> implements MvpView{

    private TextView mTextView;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private  MvpPrensenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTextView = (TextView)findViewById(R.id.text);
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.refresh_layout);
        mSwipeRefreshLayout.setEnabled(true);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.onResume();
                mPresenter.clear();
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public MvpPrensenter initPresenter() {
        mPresenter =  new MvpPrensenter(getApplicationContext());
        return mPresenter;
    }



    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void showMesssge(String data) {
        mTextView.setText(data);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void clear() {
        mTextView.setText("");
    }
}
