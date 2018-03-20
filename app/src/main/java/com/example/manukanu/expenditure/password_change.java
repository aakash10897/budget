package com.example.manukanu.expenditure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class password_change extends AppCompatActivity {
    EditText oldpass,newpass,confirmpass;
    public static String password;
    database data=new database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);
        oldpass=(EditText)findViewById(R.id.oldpass);
        newpass=(EditText)findViewById(R.id.editpass1);
        confirmpass=(EditText)findViewById(R.id.editpass2);



    }

    public void updatepass(View view){
        String oldpas=oldpass.getText().toString();
        String newpas=newpass.getText().toString();
        String confirmpas=confirmpass.getText().toString();
        password = data.getPassword();
        if(oldpas.equals("")||newpas.equals("")||confirmpas.equals("")){
            Toast.makeText(this,"Fields cannot be empty!!!",Toast.LENGTH_LONG).show();
        }
        else if(!(oldpas.equals(password))){
            Toast.makeText(this, "Old password does not match", Toast.LENGTH_SHORT).show();
        }

        else if(!(newpas.equals(confirmpas))){
            Toast.makeText(this, "Confirm Password does not matches with new Password", Toast.LENGTH_SHORT).show();
        }
        else{
            data.updatepass(newpas);
            Toast.makeText(this,"Password updated successfully",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(password_change.this,moneyused.class));
        }
    }

}
