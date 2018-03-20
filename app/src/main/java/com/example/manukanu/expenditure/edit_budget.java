
 package com.example.manukanu.expenditure;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import static com.example.manukanu.expenditure.R.id.oldpass;

public class edit_budget extends AppCompatActivity {

    EditText newbudget;
    add_database ad=new add_database(this);
    float o_bud,n_bud,rem,total_price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_budget);
        newbudget=(EditText)findViewById(R.id.editbudget);

    }
    public void updatebudget(View v) {
        rem = ad.getRemainingBudget();
        String s2 = newbudget.getText().toString();
        total_price = ad.getTotalItemPrice();

        if (newbudget.getText().toString().equals("")) {
            Toast.makeText(this, "Field cannot be empty!!!", Toast.LENGTH_LONG).show();
        } else {


            if (s2 != null) {
                o_bud = ad.getTotalBudget();
                n_bud = Float.parseFloat(s2);

                if (o_bud < n_bud) {
                    ad.updateTotal(n_bud);
                    float diff = n_bud - o_bud;
                    ad.updateRemaining_add(diff);
                    startActivity(new Intent(edit_budget.this, moneyused.class));
                } else if (n_bud < o_bud) {

                    float diff1 = o_bud - n_bud;
                    float new_rem = rem - diff1;

                    if ((new_rem < 0) || (n_bud < total_price)) {

                        Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show();

                    } else {
                        ad.updateTotal(n_bud);
                        ad.updateRemaining_diff(diff1);
                        startActivity(new Intent(edit_budget.this, moneyused.class));
                    }


                } else if (o_bud == n_bud) {
                    ad.updateTotal(n_bud);
                    startActivity(new Intent(edit_budget.this, moneyused.class));
                } else if (n_bud == rem) {
                    float bal = n_bud - rem;
                    ad.updateBalance_edit(bal);
                    ad.updateTotal(n_bud);
                    startActivity(new Intent(edit_budget.this, moneyused.class));
                }


            }


        }


    }
}
