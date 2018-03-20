package com.example.manukanu.expenditure;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class login extends AppCompatActivity {
    database db;
    EditText u_name,pass,e;
    TextView sign,user;
    Button login,ok;
    EditText e1 ;
    public static String password_recover;
    public static String budget;
    database data=new database(this);

    public List<String> ar=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        u_name=(EditText)findViewById(R.id.user);
        pass=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.login) ;
        user=(TextView)findViewById(R.id.username_show);
        e=(EditText)findViewById(R.id.etext)  ;


        db=new database(this);
        SharedPreferences preferences = getSharedPreferences("pref",MODE_PRIVATE);

        String name= preferences.getString("name",u_name.getText().toString());
        String pass = preferences.getString("pass",this.pass.getText().toString());
        Log.e("name",name);
        Log.e("pass",pass);
        password_recover=data.getPassword();


    }

public void login(View v) {
    int c = db.userLogin(u_name.getText().toString(),pass.getText().toString());
    if(u_name.getText().toString().equals("")||pass.getText().toString().equals("")){
        Toast.makeText(this,"Fields cannot be empty!!!",Toast.LENGTH_LONG).show();
    }
    else {
        if (c > 0) {
            Intent intent = new Intent(login.this, BudgetDialog.class);
            startActivity(intent);


        }
        else{
            Toast.makeText(login.this,"Incorrect Username or Password",Toast.LENGTH_LONG).show();
        }
    }

}

      public void username(View view){
          user.setBackgroundColor(Color.parseColor("#FF0AA7E1"));
          user.setText(data.getUsername());
      }
      public void username_type(View view){
          u_name.setText(user.getText().toString());
      }

    private void sendEmail() {


        //Creating SendMail object
        SendMail sm = new SendMail(this,data.getMail() );

        //Executing sendmail to send email
        sm.execute();
    }

    public void pass_field(View view){


        sendEmail();


    }
    public void onPause() {
        super.onPause();
        if (SendMail.progressDialog != null) {
            SendMail.progressDialog.dismiss();
            SendMail.progressDialog = null;
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (SendMail.progressDialog != null) {
            SendMail.progressDialog.dismiss();
            SendMail.progressDialog = null;
        }
    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
