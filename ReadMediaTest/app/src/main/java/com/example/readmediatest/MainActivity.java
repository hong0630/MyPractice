package com.example.readmediatest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static ContentResolver ContRes;
    private ListView lvMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMedia = (ListView)findViewById(R.id.lvIdMedia);

        ContRes= getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = null;
        String selection = null;
        String[] selectiongArgs = null;
        String sortOrder = null;
        Cursor cur = ContRes.query(uri,projection,selection,selectiongArgs,sortOrder);

        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1,cur,new String[]{MediaStore.Audio.Media.ALBUM},
                new int[]{android.R.id.text1});
        lvMedia.setAdapter(simpleCursorAdapter);

        startManagingCursor(cur);
        int albunIdx = cur.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM);
        int titleIdx = cur.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE);

        String[] result = new String[cur.getCount()];
        if (cur.moveToFirst()) {
            do {
                String title = cur.getString(titleIdx);
                String album = cur.getString(albunIdx);
                result[cur.getPosition()] = title + "(" + album + ")";
                Toast.makeText(MainActivity.this,
                        result[cur.getPosition()], Toast.LENGTH_SHORT).show();
            } while (cur.moveToNext());
                stopManagingCursor(cur);
        }
    }
}