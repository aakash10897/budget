package com.example.manukanu.expenditure;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class edit_fields extends AppCompatActivity {
    TextView category,expense,date;
    CustomeAdapter custom;
    private java.util.Calendar calendar;
    private int year, month, day;
    public String s1,s2,s3;
    public static String c,p,d;
    ListView listView;
    float remaining_balance;

    TextView cedit,pedit,dedit,balance_text;
    Button update;
    add_database ad;
    Integer i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_fields);
        ad=new add_database(edit_fields.this);
        balance_text=(TextView)findViewById(R.id.balance_text);
        balance_text.setText("Remaining Balance="+ad.getRemainingBudget());
        s1 = listview.iname;
        s2 = listview.price;
        s3 = listview.date;
         i=listview.id;

        update=(Button)findViewById(R.id.update);
        cedit = (TextView) findViewById(R.id.category_edit);
        pedit = (TextView) findViewById(R.id.expense_edit);
        dedit = (TextView) findViewById(R.id.dedit);
        cedit.setText(s1);
        pedit.setText(s2);
        dedit.setText(s3);
        ad=new add_database(edit_fields.this);
        calendar = java.util.Calendar.getInstance();
        year = calendar.get(java.util.Calendar.YEAR);
        month = calendar.get(java.util.Calendar.MONTH);
        day = calendar.get(java.util.Calendar.DAY_OF_MONTH);
    }
    @SuppressWarnings("deprecation")
    public void setDate(View v) {
        showDialog(999);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = current_year
                    // arg2 = current_month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dedit.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    public void update(View v){
        c= cedit.getText().toString();
        p= pedit.getText().toString();
        d=dedit.getText().toString();
        int day,month,year;

        remaining_balance=ad.getRemainingBudget();
        String price=p.toString();
        float pr= Float.parseFloat(price);
        if(c.equals("")||p.equals("")||d.equals("")){
            Toast.makeText(this,"Fields cannot be empty!!!",Toast.LENGTH_LONG).show();
        }
        else if (pr> remaining_balance) {
            Toast.makeText(this,"less balance in account",Toast.LENGTH_SHORT).show();
        }
        else {

            long l=ad.updateItems(i,c,p,d);
            if (l > 0) {
                balance_text.setText("Remaining Balance=" + ad.getRemainingBudget());

                Float price_old = Float.parseFloat(listview.price);
                Float price_new = Float.parseFloat(p);
                Float price_final = price_new - price_old;
                Float new_balance = ad.getRemainingBudget() - price_final;
                ad.updateBalance_edit(new_balance);
                Log.e("!!!", " " + new_balance);
                listview.iname = c;
                listview.price = p;
                listview.date = d;
                Log.e("New details", listview.iname + " " + listview.price + " " + listview.date);
                startActivity(new Intent(edit_fields.this, listview.class));
                listview.customeAdapter.notifyDataSetChanged();
            }
        }
    }
    public void balance(View v){
        balance_text.setText("Remaining Balance="+ad.getRemainingBudget());
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

