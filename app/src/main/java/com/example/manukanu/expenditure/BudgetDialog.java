package com.example.manukanu.expenditure;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;

public class BudgetDialog extends AppCompatActivity {


    EditText e1;
    Button ok;
    TextView t1, balance_text;
    add_database ad;
    public static String budget;
    SharedPreferences preferences;
    int current_month;
    int current_year;
    int cmin ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.etext);
        Calendar calendar = Calendar.getInstance();
        current_month = calendar.get(Calendar.MONTH);
        current_year = calendar.YEAR;

        preferences = getSharedPreferences("pref1", MODE_PRIVATE);
        boolean isFirstTime = preferences.getBoolean("isFTime", true);
        int old_month = preferences.getInt("month",0);
        int old_year = preferences.getInt("year",0);

        if (!isFirstTime) {
                if(current_month != old_month){
                    Toast.makeText(this,"hi!!!!!"+current_month+"  old"+old_month,Toast.LENGTH_LONG).show();
                    preferences = getSharedPreferences("pref1", MODE_PRIVATE);
                    SharedPreferences.Editor edit = preferences.edit();
                    edit.putInt("month", current_month);
                    edit.putInt("year", current_year);
                    edit.commit();
                    //show add budget dialog
                    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View view1 = inflater.inflate(R.layout.activity_budget_dialog, null);
                    e1 = (EditText) view1.findViewById(R.id.etext);
                    ok = (Button) view1.findViewById(R.id.ok);
                    t1 = (TextView) view1.findViewById(R.id.budget_text);
                    balance_text = (TextView)view1.findViewById(R.id.balance_text);

                    final AlertDialog.Builder builder = new AlertDialog.Builder(BudgetDialog.this);
                    builder.setView(view1);

                    final AlertDialog d = builder.create();
                    ad=new add_database(BudgetDialog.this);

                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            budget = e1.getText().toString();

                            if (budget.equals("")) {
                                t1.setText("Budget is null. Enter a valid budget");
                            } else {
                               t1.setText("");
                                float balance = Float.parseFloat(budget);
                                long l = ad.updateRemainingBudget_add(balance);

                                if (l > 0) {
                                    ad.updateTotalBudget_add(balance);
                                    startActivity(new Intent(BudgetDialog.this,moneyused.class));
                                    finish();
                                }
                                d.dismiss();

                            }
                        }
                    });
                    d.setCancelable(false);
                    d.show();

                }else {
                    startActivity(new Intent(BudgetDialog.this, moneyused.class));
                    finish();
                }
        } else {
            preferences = getSharedPreferences("pref1", MODE_PRIVATE);
            SharedPreferences.Editor edit = preferences.edit();
            edit.putBoolean("isFTime", false);
            edit.putInt("month", current_month);
            edit.putInt("year", current_year);
            edit.commit();
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View view1 = inflater.inflate(R.layout.activity_budget_dialog, null);
            e1 = (EditText) view1.findViewById(R.id.etext);
            ok = (Button) view1.findViewById(R.id.ok);
            t1 = (TextView) view1.findViewById(R.id.budget_text);
            balance_text = (TextView)view1.findViewById(R.id.balance_text);

            final AlertDialog.Builder builder = new AlertDialog.Builder(BudgetDialog.this);
            builder.setView(view1);

            final AlertDialog d = builder.create();
             ad=new add_database(BudgetDialog.this);

            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    budget = e1.getText().toString();

                    if (budget.equals("")) {
                        t1.setText("Budget is null. Enter a valid budget");
                    } else {
                        t1.setText("");
                        int balance = Integer.parseInt(budget);
                        long l = ad.addBudget(balance);


                        if (l > 0) {
                            startActivity(new Intent(BudgetDialog.this,moneyused.class));

                            finish();
                        }
                        d.dismiss();

                    }
                }
            });
            d.setCancelable(false);
            d.show();
        }
    }
}

