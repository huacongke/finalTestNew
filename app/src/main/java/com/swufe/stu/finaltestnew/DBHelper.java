package com.swufe.stu.finaltestnew;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "myAccount.db";
    public static final String TB_NAME = "tb_account";


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
    }
    public DBHelper(Context context) {
        super(context,DB_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TB_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,CURTYPE TEXT,CURCATE TEXT,CURTIME TEXT,CURRMB TEXT,CURNOTE TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
    }
}
