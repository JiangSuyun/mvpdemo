package com.jsy.example.newmodule;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadData();


    }

    private void loadData(){
        new AsyncTask<String,String,List<String>>(){
            @Override
            protected List<String> doInBackground(String... strings) {

                ContentResolver resolver = getContentResolver();
                Uri uri = Uri.parse("content://com.jsy.example.myapplication/note");

                Cursor cursor = resolver.query(uri, null, null, null, "");
                if (cursor == null) return null;

                List<String> list = new ArrayList<String>();
//                int index = cursor.getColumnIndex("text");
                while (cursor.moveToNext()) {
                    list.add(cursor.getString(1));
                }

                cursor.close();
                return list;
            }

            @Override
            protected void onPostExecute(List<String> list) {
                super.onPostExecute(list);

                MyAdapter adapter = new MyAdapter();
                mRecyclerView.setAdapter(adapter);
                adapter.setData(list);
            }
        }.execute();
    }
    static class MyAdapter extends RecyclerView.Adapter<ViewHolder>{
        List<String> list;

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
            holder.getTextView().setText(list.get(position));
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
