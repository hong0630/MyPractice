package com.example.contprovidtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contprovidtest.providers.CompContentProvider;

public class MainActivity extends AppCompatActivity {
    private static ContentResolver ContRes;
    private EditText etCusNo, etCusNa, etCusPho, etCusAdd, etResult;
    private Button btIns, btCls, btList, btQry, btEdit, btDel;
    String msg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildViews();
        ContRes = getContentResolver();
    }

    private void buildViews() {
        etCusNo = (EditText) findViewById(R.id.etIdCusNo);
        etCusNa = (EditText)findViewById(R.id.etIdCusNa);
        etCusPho = (EditText)findViewById(R.id.etIdCusPho);
        etCusAdd = (EditText)findViewById(R.id.etIdCusAdd);

        etResult =(EditText) findViewById(R.id.etIdResult);

        btIns = (Button)findViewById(R.id.btIdIns);
        btCls = (Button)findViewById(R.id.btIdCls);
        btList = (Button)findViewById(R.id.btIdList);
        btQry = (Button)findViewById(R.id.btIdQry);
        btEdit = (Button)findViewById(R.id.btIdEdit);
        btDel = (Button)findViewById(R.id.btIdDel);

        btIns.setOnClickListener(btInsListener);
        btCls.setOnClickListener(btClsListener);
        btList.setOnClickListener(btListListener);
        btQry.setOnClickListener(btQryListener);
        btEdit.setOnClickListener(btEditListener);
        btDel.setOnClickListener(btDelListener);
    }

    private View.OnClickListener btInsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ContentValues contVal = new ContentValues();
            Uri uri = CompContentProvider.CONTENT_URI;
            contVal.put("cusNo", etCusNo.getText().toString());
            contVal.put("cusNa", etCusNa.getText().toString());
            contVal.put("cusPho", etCusPho.getText().toString());
            contVal.put("cusAdd", etCusAdd.getText().toString());
            ContRes.insert(uri, contVal);
        }
    };

//    private ContentValues FillRec() {
//        ContentValues contVal = new ContentValues();
//        contVal.put("cusNo", etCusNo.getText().toString());
//        contVal.put("cusNa", etCusNa.getText().toString());
//        contVal.put("cusPho", etCusPho.getText().toString());
//        contVal.put("cusAdd", etCusAdd.getText().toString());
//        return contVal;
//    }


    private View.OnClickListener btClsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            etCusNo.setText("");
            etCusNa.setText("");
            etCusPho.setText("");
            etCusAdd.setText("");
        }
    };

    private View.OnClickListener btListListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Uri uri = CompContentProvider.CONTENT_URI;
            String[] projection = new String[]{"cusNo", "cusNa", "cusPho", "cusAdd"};
            String selection = null;
            String[] selectionArgs = null;
            String sortOrder = null;
            Cursor cur = ContRes.query(uri, projection, selection, selectionArgs, sortOrder);

            if (cur == null) return;
            if (cur.getCount() == 0) {
                etResult.setText("");
                Toast.makeText(MainActivity.this, "????????????", Toast.LENGTH_SHORT).show();
            } else {
                cur.moveToFirst();
                etResult.setText(cur.getString(0) + " " +
                        cur.getString(1) + " " + cur.getString(2) +
                        "" + cur.getString(3));
            }

            while (cur.moveToNext()) {
                etResult.append("\n" + cur.getString(0) + " " +
                        cur.getString(1) + " " + cur.getString(2) +
                        "" + cur.getString(3));
            }
            Toast.makeText(MainActivity.this, "???" +
                    cur.getCount() + "?????????", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener btQryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Cursor cur = null;
            if (etCusNo.getText().toString().isEmpty() == false) {
                Uri uri = CompContentProvider.CONTENT_URI;
                String[] projection = new String[]{"cusNo", "cusNa", "cusPho", "cusAdd"};
                String selection = "cusNo=" + "\'" +
                        etCusNo.getText().toString() + "\'";
                String[] selectionArgs = null;
                String sortOrder = null;
                cur = ContRes.query(uri, projection, selection, selectionArgs, sortOrder);
            } else {
                Toast.makeText(MainActivity.this, "??????????????????????????????????????????????????????",
                        Toast.LENGTH_SHORT).show();
            }

            if (cur == null) {
                return;
            }
            if (cur.getCount() == 0) {
                etResult.setText("");
                Toast.makeText(MainActivity.this, "????????????", Toast.LENGTH_SHORT).show();
            } else {
                cur.moveToFirst();
                etCusNo.setText(cur.getString(0));
                etCusNa.setText(cur.getString(1));
                etCusPho.setText(cur.getString(2));
                etCusAdd.setText(cur.getString(3));
            }
        }
    };

    private View.OnClickListener btEditListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Uri uri = CompContentProvider.CONTENT_URI;
            ContentValues contVal = new ContentValues();
//            contVal = FillRec();
            contVal.put("cusNo", etCusNo.getText().toString());
            contVal.put("cusNa", etCusNa.getText().toString());
            contVal.put("cusPho", etCusPho.getText().toString());
            contVal.put("cusAdd", etCusAdd.getText().toString());

            String CusNo = etCusNo.getText().toString().trim();
            String whereClause = "cusNo=" + CusNo + "";
            String[] selectionArgs = null;
            int rowsAffected = ContRes.update(uri,contVal,whereClause,selectionArgs);
            if (rowsAffected ==-1){
                msg = "??????????????????????????????";
            }else if (rowsAffected ==0){
                msg = "??????????????????????????????????????????";
            }else {
                msg = "???"+rowsAffected+"????????? ????????????";
            }
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener btDelListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Uri uri = CompContentProvider.CONTENT_URI;
            String CusNo = etCusNo.getText().toString().trim();
            String whereClause = "cusNo='"+CusNo+"";
            String[] selectionArgs=null;
            int rowsAffected = ContRes.delete(uri,whereClause,selectionArgs);

            if (rowsAffected ==-1){
                msg = "??????????????????????????????";
            }else if (rowsAffected ==0){
                msg = "??????????????????????????????????????????";
            }else {
                msg = "?????????"+rowsAffected+"????????? ???????????????";
            }
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    };
}