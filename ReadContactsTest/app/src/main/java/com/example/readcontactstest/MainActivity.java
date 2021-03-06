package com.example.readcontactstest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private static ContentResolver ContRes;
    private ListView lvContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvContacts = (ListView)findViewById(R.id.lvIdContacts);

        ContRes = getContentResolver();
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String[] projection = null;
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;
        Cursor cur = ContRes.query(uri,projection,selection,selectionArgs,sortOrder);
        //deprecation
        SimpleCursorAdapter simpleCursorAdapter =
                new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,cur,new String[]
                        {ContactsContract.Contacts.DISPLAY_NAME},new int[]{android.R.id.text1});

        lvContacts.setAdapter(simpleCursorAdapter);
    }
}