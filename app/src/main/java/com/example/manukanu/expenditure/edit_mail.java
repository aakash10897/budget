package com.example.manukanu.expenditure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class edit_mail extends AppCompatActivity {
    EditText old_mail,new_mail;
    database data=new database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_mail);
        old_mail=(EditText)findViewById(R.id.old_rec_mail);
        new_mail=(EditText)findViewById(R.id.new_rec_mail);
    }

    public void update_recmail(View view){


        String rec_mail_old=old_mail.getText().toString().trim();
        String rec_mail_new=new_mail.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(rec_mail_old.equals("")||rec_mail_new.equals("")){
            Toast.makeText(this, "Fields cannot be empty!!", Toast.LENGTH_LONG).show();
        }
        else if (!(rec_mail_old.matches(emailPattern)&& rec_mail_new.matches(emailPattern)))
        {
            Toast.makeText(this,"Invalid email address",Toast.LENGTH_LONG).show();
        }




           else if (!(data.getMail().equals(rec_mail_old)))
        {
                Toast.makeText(this,"Old recovery mail does not match",Toast.LENGTH_LONG).show();

            }
            else{
            data.updatemail(rec_mail_new);
            Toast.makeText(this,"Recovery mail updated successfully",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(edit_mail.this,moneyused.class));
            }
        }




}
