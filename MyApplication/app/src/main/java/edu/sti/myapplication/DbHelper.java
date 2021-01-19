package edu.sti.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "db";
    public static final String TABLE_NAME1 = "tblStudents";
    public static final String COL1 = "email";
    public static final String COL2 = "fname";
    public static final String COL3 = "mname";
    public static final String COL4 = "lname";
    public static final String COL5 = "uname";
    public static final String COL6 = "password";
    public static final String COL7 = "friends";
    public static final String COL8 = "points";
    public static final String COL9 = "section";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME1 + "(" + COL1 +" TEXT PRIMARY KEY, "+ COL2 +" TEXT, " + COL3+" TEXT, " + COL4+" TEXT, " + COL5+" TEXT, " + COL6+" TEXT, " + COL7+" TEXT, "
                + COL8+" TEXT, " + COL9 +" TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public boolean insertData(String email, String fname, String mname, String lname,String uname,String password,String friends,String points,String section){

        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,email);
        contentValues.put(COL2,fname);
        contentValues.put(COL3,mname);
        contentValues.put(COL4,lname);
        contentValues.put(COL5,uname);
        contentValues.put(COL6,password);
        contentValues.put(COL7,friends);
        contentValues.put(COL8,points);
        contentValues.put(COL9,section);
        long result = db.insert(TABLE_NAME1,null ,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * from "+TABLE_NAME1,null);
        return  res;
    }

    public Cursor getUserData(String uname){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * from "+TABLE_NAME1 + " WHERE " + COL5 + " = '"+ uname + "'",null);
        return  res;
    }

    public boolean updatePoints(String uname,  String points){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL8, points);
        db.update(TABLE_NAME1, contentValues, COL5 + " = ?",new String[]{uname});
        return true;
    }

    public Cursor getLeaderBoard(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * from "+TABLE_NAME1+" ORDER BY "+COL8+" DESC",null);
        return  res;
    }



}


