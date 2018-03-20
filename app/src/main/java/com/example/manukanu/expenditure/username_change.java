package com.example.manukanu.expenditure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class username_change extends AppCompatActivity {
EditText current,new_username,confirm;
    database db= new database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username_change);
        current=(EditText)findViewById(R.id.current_text);
        new_username=(EditText)findViewById(R.id.new_text);
        confirm=(EditText)findViewById(R.id.confirm_text);



    }

    public void chng_username(View view){
        String current_user=current.getText().toString();
        String new_user=new_username.getText().toString();
        String confirm_user=confirm.getText().toString();
        String user = db.getUsername();
        if(current_user.equals("")||new_user.equals("")||confirm_user.equals("")){
            Toast.makeText(this,"Fields cannot be empty!!!",Toast.LENGTH_LONG).show();
        }
        else if(!(current_user.equals(user))){
            Toast.makeText(this, "Old username does not match", Toast.LENGTH_LONG).show();
        }

        else if(!(new_user.equals(confirm_user))){
            Toast.makeText(this, "Confirm Username does not matches with new Username", Toast.LENGTH_LONG).show();
        }
        else{
            db.updateuser(new_user);
            Toast.makeText(this,"Username updated successfully",Toast.LENGTH_LONG).show();
            startActivity(new Intent(username_change.this,moneyused.class));

        }
    }

}

