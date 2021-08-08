package com.example.updaterectest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class CompDBHper extends SQLiteOpenHelper {
    //    private static final String DBname = "先進公司.db";
    //    private static final int DBversion = 1;
    private static final String TBname = "客戶";
    private static final String crTBsql =
            "CREATE TABLE "+TBname+"(" +
            "cusNo VARCHAR(10) NOT NULL," +
            "cusNa VARCHAR(20) NOT NULL," +
            "cusPho VARCHAR(20)," +
            "cusAdd VARCHAR(50), PRIMARY KEY(cusNo));";
    public CompDBHper(Context context,String DBname,SQLiteDatabase.CursorFactory factory, int DBversion) {
//        super(context, name, factory, version);
        super(context, "先進公司.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(crTBsql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TBname);
        onCreate(db);
    }

    public void creatTB(){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues[] rec = new ContentValues[3];
        for (int i = 0;i<rec.length;i++)
            rec[i] = new ContentValues();

        rec[0].put("cusNo","A10001");
        rec[0].put("cusNa","林怡靜");
        rec[0].put("cusPho","(03)873-1234");
        rec[0].put("cusAdd","桃園縣平安村49號");

        rec[1].put("cusNo","A10002");
        rec[1].put("cusNa","吳美虹");
        rec[1].put("cusPho","(02)822-3129");
        rec[1].put("cusAdd","北市延平南路20號");

        rec[2].put("cusNo","A10003");
        rec[2].put("cusNa","許仁均");
        rec[2].put("cusPho","(02)704-1134");
        rec[2].put("cusAdd","北市健康路15號");

        for (ContentValues row:rec){
            db.insert(TBname,null,row);
        }
        db.close();
    }

    public ArrayList<String> getRecSet(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM "+TBname;
        Cursor recSet = db.rawQuery(sql,null);
        ArrayList<String> recAry = new ArrayList<String>();
        int columnCount = recSet.getColumnCount();
        while(recSet.moveToNext()){
            String fIdSet = "";
            for (int i = 0;i<columnCount;i++)
                fIdSet += recSet.getString(i)+"#";
            recAry.add(fIdSet);
        }
        recSet.close();
        db.close();
        return recAry;
    }

    public int updateRec(String CusNo ,String CusNa ,String CusPho ,String CusAdd){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM "+ TBname;
        Cursor recSet = db.rawQuery(sql,null);
        if (recSet.getCount()!=0){
            ContentValues rec = new ContentValues();
            rec.put("cusNo",CusNo);
            rec.put("cusNa",CusNa);
            rec.put("cusPho",CusPho);
            rec.put("cusAdd",CusAdd);

            String whereClause = "cusNo = '"+CusNo+"'";
            int rowsAffected = db.update(TBname,rec,whereClause,null);
            db.close();
            return rowsAffected;
        }else {
            db.close();
            return -1;
        }
    }

    public int deleteRec(String CusNo){
        SQLiteDatabase db  = getWritableDatabase();
        String sql = "SELECT * FROM "+TBname;
        Cursor recSet = db.rawQuery(sql,null);
        if (recSet.getCount() != 0){
            String whereClause = "cusNo = '"+CusNo+"'";
            int rowsAffected = db.delete(TBname,whereClause,null);
            db.close();
            return rowsAffected;
        }else {
            db.close();
            return -1;
        }
    }
}
