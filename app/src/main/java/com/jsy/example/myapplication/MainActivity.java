package com.jsy.example.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jsy.greendao.DaoMaster;
import com.jsy.greendao.DaoSession;
import com.jsy.greendao.Note;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecycleView;
    RecyclerView.LayoutManager mLayoutManager;
    MyAdapter mAdapter;
    EditText mEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecycleView = (RecyclerView)findViewById(R.id.list);
        mLayoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter();
        mRecycleView.setAdapter(mAdapter);
        mEditText = (EditText)findViewById(R.id.text) ;
        Button fab = (Button) findViewById(R.id.fab);
        loadData();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               final String s = mEditText.getText().toString();
                if (!TextUtils.isEmpty(s)){
                    new AsyncTask<String,String,String>(){
                        @Override
                        protected String doInBackground(String... strings) {
                            daoSession.getNoteDao().insert(new Note(s, new Date()));
                            return null;
                        }

                        @Override
                        protected void onPostExecute(String s) {
                            super.onPostExecute(s);
                            loadData();
                        }
                    }.execute();


                }
            }
        });
    }
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    private void loadData(){
        new AsyncTask<String,String ,List<Note>>(){
            @Override
            protected List<Note> doInBackground(String... strings) {
                DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(MainActivity.this, "notes-db", null);
                db = helper.getWritableDatabase();
                // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
                daoMaster = new DaoMaster(db);
                daoSession = daoMaster.newSession();
                List<Note> list = daoSession.getNoteDao().loadAll();
                return list;
            }

            @Override
            protected void onPostExecute(List<Note> s) {
                super.onPostExecute(s);
                if(s == null)return;
                mAdapter.setData(s);
            }
        }.execute();

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

    static class MyAdapter extends RecyclerView.Adapter<ViewHolder>{
        List<Note> list;

        private void setData(List data){
            list = data;
            notifyDataSetChanged();
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false);

            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.getTextView().setText(list.get(position).getText());
        }

        @Override
        public int getItemCount() {
            return list==null?0:list.size();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(android.R.id.text1);
        }

        public TextView getTextView() {
            return textView;
        }
    }
}
