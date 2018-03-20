package com.example.manukanu.expenditure;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class add_money extends AppCompatActivity{
    EditText i, p, d, u_name, pass;
    add_database ad;
    database db;
    int mYear, mMonth, mDay;
    Button details_add,addnew,cancel;
    static  TextView balance_text;
    moneyused m;
    private DatePicker datePicker;
    private java.util.Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    public static int current_year;
    static float remaining_balance;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ad=new add_database(add_money.this);
        balance_text=(TextView)findViewById(R.id.balance_text);
        balance_text.setText("Remaining Balance="+ad.getRemainingBudget());
        i = (EditText) findViewById(R.id.in);
        p = (EditText) findViewById(R.id.price);
        u_name = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.password);
        details_add=(Button)findViewById(R.id.details_add);
        balance_text=(TextView)findViewById(R.id.balance_text) ;
        ad = new add_database(this);
        db = new database(this);
        current_year = calendar.getInstance().get(calendar.YEAR);



        dateView = (TextView) findViewById(R.id.textView3);
        calendar = java.util.Calendar.getInstance();
        year = calendar.get(java.util.Calendar.YEAR);

        month = calendar.get(java.util.Calendar.MONTH);
        day = calendar.get(java.util.Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);
    }
    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        calendar = java.util.Calendar.getInstance();
        year = calendar.get(java.util.Calendar.YEAR);

        month = calendar.get(java.util.Calendar.MONTH);
        day = calendar.get(java.util.Calendar.DAY_OF_MONTH);
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

                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }


    public void addnew(View v) {

        if (i.getText().toString().equals("") || p.getText().toString().equals("")) {
            Toast.makeText(this, "Fields cannot be empty!!!", Toast.LENGTH_SHORT).show();
        } else {
            String s = p.getText().toString();
            float pr = Float.parseFloat(s);
            remaining_balance = ad.getRemainingBudget();
            if (pr > remaining_balance) {

                Toast.makeText(this, "Less balance in account", Toast.LENGTH_SHORT).show();
            } else {


                long l = ad.addItem(i.getText().toString(), pr, dateView.getText().toString());


                if (l > 0) {

                    final AlertDialog.Builder build = new AlertDialog.Builder(add_money.this);
                    build.setTitle("Do you want to enter more?")
                            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(add_money.this, moneyused.class);
                                    startActivity(intent);
                                    build.setCancelable(false);
                                }
                            }).setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            p.setText("");
                            i.setText("");
                            calendar = java.util.Calendar.getInstance();
                            year = calendar.get(java.util.Calendar.YEAR);

                            month = calendar.get(java.util.Calendar.MONTH);
                            day = calendar.get(java.util.Calendar.DAY_OF_MONTH);
                            showDate(year, month + 1, day);

                        }
                    }).setCancelable(false);
                    build.create().show();
                    balance_text.setText("Remaining Balance=" + ad.getRemainingBudget());
                    ad.updateRemainingBudget(pr);
                }

            }
        }
    }




    public void balance(View v){

        balance_text.setText("Remaining Balance = "+ad.getRemainingBudget());


    }
}

