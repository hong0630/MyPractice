package com.example.contprovidtest.providers;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CompContentProvider extends ContentProvider {
    private static final String AUTHORITY =
            "andbas.ContProvidTest.providers.CompContentProvider";
    private static final String DBname = "先進公司.db",TBname = "CUSOM";
    private static final int URI_ROOT = 0,DB_TABLE_CUSOM = 1;
    public static final Uri CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/"+TBname);
    private static final UriMatcher uriMatcher = new UriMatcher(URI_ROOT);
    static {
        uriMatcher.addURI(AUTHORITY,TBname,DB_TABLE_CUSOM);
    }
    private SQLiteDatabase CompDB;

    @Override
    public boolean onCreate() {
        CompDBHper dbHper = new CompDBHper(
                getContext(),DBname,null,1);
        CompDB=dbHper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        if (uriMatcher.match(uri)!=DB_TABLE_CUSOM){
            throw new IllegalArgumentException("未知的 URI!" + uri);
        }
        Cursor cur = CompDB.query(true,TBname,projection,selection,
                null,null,null,null, null);
        cur.setNotificationUri(getContext().getContentResolver(),uri);

        return cur;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        if (uriMatcher.match(uri)!=DB_TABLE_CUSOM){
            throw new IllegalArgumentException("未知的 URI！"+uri);
        }

        long rowId = CompDB.insert(TBname,null,values);
        Uri uriAfterIns = ContentUris.withAppendedId(CONTENT_URI,rowId);
        getContext().getContentResolver().notifyChange(uriAfterIns,null);
        return uriAfterIns;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        if (uriMatcher.match(uri)!=DB_TABLE_CUSOM){
            throw new IllegalArgumentException("未知的 URI！"+uri);
        }
        int rowAffected = CompDB.delete(TBname,selection,null);
        return rowAffected;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values,
                      @Nullable String selection, @Nullable String[] selectionArgs) {
        if (uriMatcher.match(uri)!=DB_TABLE_CUSOM){
            throw new IllegalArgumentException("未知的 URI！"+uri);
        }
        int rowsAffected = CompDB.update(TBname,values,selection,null);
        return rowsAffected;
    }
}
