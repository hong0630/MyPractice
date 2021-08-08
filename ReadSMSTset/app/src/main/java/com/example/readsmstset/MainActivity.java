package com.example.readsmstset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;

import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity {
    private static ContentResolver ContRes;
    private ListView lvSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvSMS = (ListView)findViewById(R.id.lvIdSMS);

        ContRes = getContentResolver();
        Uri uri = Uri.parse("content://sms/inbox");
        String[] projection = null;
        String selection = "address like?";
        String[] selectionArgs = new String[]{"0%"};
        String sortOrder = null;
        Cursor cur = ContRes.query(uri,projection,selection,selectionArgs,sortOrder);

        SMSAdpter smsAdpter = new SMSAdpter(this,cur);
        lvSMS.setAdapter(smsAdpter);
    }
}