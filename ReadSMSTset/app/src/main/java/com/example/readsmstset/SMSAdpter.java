package com.example.readsmstset;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cursoradapter.widget.CursorAdapter;

public class SMSAdpter extends CursorAdapter {
    private LayoutInflater layoutInflater;
    private TextView tvPhoneNo,tvCont;
    public SMSAdpter(Context context, Cursor c) {
        super(context, c);
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.item,null);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        tvPhoneNo = (TextView)view.findViewById(R.id.tvIdPhoneNo);
        tvCont = (TextView)view.findViewById(R.id.tvIdCont);
        tvPhoneNo.setText(cursor.getString(cursor.getColumnIndex("address")));
        tvCont.setText(cursor.getString(cursor.getColumnIndex("body")));
    }
}
