package com.example.manukanu.expenditure;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class database extends SQLiteOpenHelper{
    String tableName="User";
    String userName="Name";
    String password="Password";
    String mail="Mail";

    public List<UserBean> ar;

    public database(Context context) {

        super(context, "db1", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table "+tableName+"("+userName+" text,"+password+" text,"+mail+" )";
        db.execSQL(sql);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addUser( String name,String password,String mail){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(userName,name);
        values.put(this.password,password);
        values.put(this.mail,mail);
        return db.insert(tableName,null,values);
    }




   public int userLogin(String name, String password) {
       SQLiteDatabase db = getWritableDatabase();
       Cursor cr = db.rawQuery("select *from " +tableName + " where "+userName+"=? and "+this.password+" = ?",new String[]{name,password});
       return cr.getCount();
   }

    public String getPassword(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String pass="";
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+tableName,null);
        if (cursor.moveToFirst()){
            pass= cursor.getString(1);
        }
        return pass;
    }



    public  String getUsername(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String user="";
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+tableName,null);
        if (cursor.moveToFirst()){
            user= cursor.getString(0);
        }
        return user;
    }

    public String getMail(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String mail="";
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+tableName,null);
        if (cursor.moveToFirst()){
            mail= cursor.getString(2);
        }
        return mail;
    }

    public long updatepass (String pass){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(password,pass);
        return db.update(tableName,values,null,null);
    }

    public long updatemail (String mail){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(this.mail,mail);
        return db.update(tableName,values,null,null);
    }

    public long updateuser (String user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(userName, user);
        return db.update(tableName, values, null,null);
    }


}

