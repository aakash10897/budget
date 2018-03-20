package com.example.manukanu.expenditure;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {
    EditText u,p,recovery;
    database d;
    static String rec_mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        u = (EditText)findViewById(R.id.u);
        p = (EditText)findViewById(R.id.p);
        recovery = (EditText)findViewById(R.id.rec);
        d = new database(this);
        SharedPreferences preferences = getSharedPreferences("pref",MODE_PRIVATE);
        boolean isFirstTime = preferences.getBoolean("isFirst",true);
        if (!isFirstTime){
            startActivity(new Intent(signup.this,login.class));
        }


    }


    public  void reg(View view) {
        rec_mail = recovery.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (u.getText().toString().equals("") || p.getText().toString().equals("") || recovery.getText().toString().equals("")) {
            Toast.makeText(this, "Fields cannot be empty!!!", Toast.LENGTH_LONG).show();
        }

        else if (!(rec_mail.matches(emailPattern))) {
            Toast.makeText(getApplicationContext(), "Invalid email address!!!", Toast.LENGTH_LONG).show();
        } else {
            long l = d.addUser(u.getText().toString(), p.getText().toString(), rec_mail);







                if (l > 0) {
                    SharedPreferences preferences = getSharedPreferences("pref", MODE_PRIVATE);
                    SharedPreferences.Editor edit = preferences.edit();
                    edit.putBoolean("isFirst", false);
                    edit.commit();
                    Intent intent = new Intent(signup.this, login.class);
                    startActivity(intent);
                }
            }

        }
    }


