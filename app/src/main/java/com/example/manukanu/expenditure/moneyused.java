package com.example.manukanu.expenditure;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class moneyused extends AppCompatActivity {
    add_database ad;

    Button ok;
    EditText e1 ;
    TextView t1;
    static TextView balance_text,total_text;

    public static AlertDialog d;

    public static String budget;
    Button details_add,cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moneyused);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ad = new add_database(moneyused.this);
        balance_text = (TextView) findViewById(R.id.balance_text);
        total_text = (TextView) findViewById(R.id.total);
        balance_text.setText("Remaining Balance \n" + ad.getRemainingBudget());
        total_text.setText("Total Budget \n " + ad.getTotalBudget());
    }
            public void add(View view){
                Intent intent = new Intent(moneyused.this, add_money.class);
                startActivity(intent);
            }

            public void show(View v){
                Intent intent = new Intent(moneyused.this, listview.class);
                startActivity(intent);
            }

    public void alarm(View v){
        Intent intent = new Intent(moneyused.this, AlarmActivity.class);
        startActivity(intent);
    }

    public void total(View v){
                total_text.setText("Total Budget \n "+ad.getTotalBudget());
            }

            public void balance(View v){
                balance_text.setText("Remaining Balance \n "+ad.getRemainingBudget());
            }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_moneyused, menu);
        return true;
    }
            @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_username) {
            startActivity(new Intent(moneyused.this,username_change.class));

        }
        if(id==R.id.action_logout){
            startActivity(new Intent(moneyused.this,login.class));
        }

        if(id==R.id.action_password){
            startActivity(new Intent(moneyused.this,password_change.class));
        }


                if(id==R.id.action_budget){
                    startActivity(new Intent(moneyused.this,edit_budget.class));
                }
//                if(id==R.id.action_backup){
//
//                }

                if(id==R.id.action_mail){
                    startActivity(new Intent(moneyused.this,edit_mail.class));
                }


        return super.onOptionsItemSelected(item);
    }

            @Override
            public void onBackPressed() {
                moveTaskToBack(true);
            }


        }






