package com.jsy.example.myapplication;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.jsy.greendao.DaoMaster;
import com.jsy.greendao.DaoSession;
import com.jsy.greendao.NoteDao;

/**
 * Created by Administrator on 2016/9/1.
 */
public class DataProvider extends ContentProvider {
    private DaoSession daoSession;
    static UriMatcher sUriMacher = new UriMatcher(UriMatcher.NO_MATCH);
    static String URI_AUTHORITY = "com.jsy.example.myapplication";
    static {
        sUriMacher.addURI(URI_AUTHORITY,"note",1);
    }
    @Override
    public boolean onCreate() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getContext(), "notes-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        DaoMaster  daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        if(sUriMacher.match(uri) == 1){
            return daoSession.getNoteDao().getDatabase().rawQuery("select * from "+ NoteDao.TABLENAME,null);
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
