package com.swufe.stu.finaltestnew;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private DBHelper dbHelper;
    private String TBNAME;

    public DBManager(Context context) {
        dbHelper = new DBHelper(context);
        TBNAME = DBHelper.TB_NAME;
    }

    public void add(AccItem item) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("curtype", item.getCurType());
        values.put("curcate", item.getCurCate());
        values.put("curtime", item.getCurTime());
        values.put("currmb", item.getCurRmb());
        values.put("curnote", item.getCurNote());

        db.insert(TBNAME, null, values);
        db.close();
    }

    public List<AccItem> listAll() {
        List<AccItem> rateList = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null, null, null, null, null, null);
        if (cursor != null) {
            rateList = new ArrayList<AccItem>();
            while (cursor.moveToNext()) {
                AccItem item = new AccItem();
                item.setId(cursor.getInt(0));
                item.setCurType(cursor.getString(1));
                item.setCurCate(cursor.getString(2));
                item.setCurTime(cursor.getString(3));
                item.setCurRmb(cursor.getString(4));
                item.setCurNote(cursor.getString(5));

                rateList.add(item);
            }
            cursor.close();
        }
        db.close();
        return rateList;
    }

    public void deleteAll(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME,null,null);
        db.close();
    }

    public List<AccItem> findByDate(String date){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor=db.query(TBNAME, null, "CURTIME=?", new String[]{date}, null, null, null);

        List<AccItem> rateList = null;
        if(cursor!=null){
            rateList = new ArrayList<AccItem>();
            while (cursor.moveToNext()){
                AccItem item=new AccItem();
                item.setId(cursor.getInt(0));
                item.setCurType(cursor.getString(1));
                item.setCurCate(cursor.getString(2));
                item.setCurTime(cursor.getString(3));
                item.setCurRmb(cursor.getString(4));
                item.setCurNote(cursor.getString(5));

                rateList.add(item);
            }
            cursor.close();

        }
        db.close();
        return rateList;
    }
}