package com.example.manukanu.expenditure;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class add_database extends SQLiteOpenHelper {
    String tname = "Items";
    String Item_name = "ItemName";
    String price = "Price";
    String date = "Date";
    String id= "ID";
    String year = "Year";
    String month = "Month";
    String day = "Day";

    String budget_table ="budget";
    String totalBudget="Total_Budget";
    String RemainingBalance ="remainingBalance";
    Context context;


    public add_database(Context context) {

        super(context, "db3", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + tname + " ("+ id  + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "" + Item_name + " text," + price + " float," + date + " text,"+year+" text,"+month+" text,"+day+")";
        db.execSQL(sql);
        String sql1= "create table " + budget_table + " (" + totalBudget + " float," + RemainingBalance + " float)";
        db.execSQL(sql1);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    public long addItem(String iname, float price, String date) {
        Log.d("date",date);
        Toast.makeText(context, date, Toast.LENGTH_SHORT).show();
        String dateArray[]= date.split("/");


        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(this.price, price);
        values.put(this.date, date);
        values.put(this.Item_name, iname);
        values.put(this.year,dateArray[2]);
        values.put(this.month,dateArray[1]);
        values.put(this.day,dateArray[0]);
        return db.insert(tname, null, values);
    }

    public long addBudget(float balance) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values1 = new ContentValues();
        values1.put(totalBudget, balance);
        values1.put(RemainingBalance, balance);

        return db.insert(budget_table, null, values1);
    }

    public float getRemainingBudget(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        float bal=0;
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+budget_table,null);
        if (cursor.moveToNext()){
            bal= cursor.getInt(1);
        }
        return bal;
    }

    // to get total price
    public float getTotalItemPrice(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        float cost=0;
        Cursor cursor = sqLiteDatabase.rawQuery("select sum(price) from "+tname,null);
        if (cursor.moveToNext()){
            cost= cursor.getInt(0);
        }
        return cost;
    }

    public float getTotalBudget(){
        SQLiteDatabase sqLiteDatabase =getWritableDatabase();
        float bud=0;
        Cursor cursor= sqLiteDatabase.rawQuery("select "+ totalBudget + " from "+ budget_table,null);
        if (cursor.moveToNext()){
            bud= cursor.getInt(0);
        }
        return bud;
    }




    public float updateRemainingBudget(float price){
        SQLiteDatabase db = getWritableDatabase();
        float remainingBudget=getRemainingBudget();
        float updatePrice = remainingBudget - price;

        ContentValues values = new ContentValues();
        values.put(RemainingBalance,updatePrice);
        return db.update(budget_table,values,null, null);
    }

    public long updateRemainingBudget_add(float price){
        SQLiteDatabase db = getWritableDatabase();
        float remainingBudget=getRemainingBudget();
        float updatePrice = remainingBudget + price;

        ContentValues values = new ContentValues();
        values.put(RemainingBalance,updatePrice);
        return db.update(budget_table,values,null, null);
    }


    public float updateTotalBudget_add(float price){
        SQLiteDatabase db = getWritableDatabase();
        float total_budget=getTotalBudget();
        float updatePrice = total_budget + price;

        ContentValues values = new ContentValues();
        values.put(totalBudget,updatePrice);
        return db.update(budget_table,values,null, null);
    }
    // edit budget when old balance is less than new balance
    public float updateRemaining_add(float price){
        SQLiteDatabase db = getWritableDatabase();
        float rem=getRemainingBudget();
        float new_rem_add=rem+price;
        ContentValues values1 = new ContentValues();
        values1.put(RemainingBalance,new_rem_add);
        return db.update(budget_table,values1,null, null);

    }
    // edit budget when new balance is less than old balance
    public float updateRemaining_diff(float price){
        SQLiteDatabase db = getWritableDatabase();
       float rem=getRemainingBudget();
        float new_rem_diff=rem-price;
        ContentValues values2 = new ContentValues();
        values2.put(RemainingBalance,new_rem_diff);
        return db.update(budget_table,values2,null, null);

    }

    public float updateTotal(float price){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(totalBudget,price);
        return db.update(budget_table,values,null, null);

    }


    public float updateBalance_edit(float price){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RemainingBalance,price);
        return db.update(budget_table,values,null, null);
    }


    public long updateItems (Integer id,String iname,String price,String date){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(this.Item_name,iname);
        values.put(this.price,price);
        values.put(this.date,date);
       // values.put(this.month,month);
       // values.put(this.day,day);
       // values.put(this.year,year);
        return db.update(tname,values,"ID =?", new String[]{id.toString()});
    }

    public long deleteItem (Integer id){
        SQLiteDatabase db=getWritableDatabase();
        return db.delete(tname,"ID=?",new String[]{id.toString()});
    }

    public List<ItemBean> getBudgetByMonthAndYear(String y,String m){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cr = db.rawQuery("select *from " + tname+" where "+month+"=? and "+year+"=?", new String[]{m,y});

        ArrayList<ItemBean> ar1=new ArrayList<ItemBean>();

        while (cr.moveToNext()) {
            ItemBean itemBean = new ItemBean(cr.getInt(0), cr.getString(1), cr.getString(2), cr.getString(3));
            ar1.add(itemBean);
        }
        return ar1;
    }




}
