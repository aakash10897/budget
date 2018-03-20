package com.example.manukanu.expenditure;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Reminder_dialog extends AppCompatActivity {
    Button reminder,cancel;
    public static String title;
    String text;
    EditText e1;
    private ScheduleClient scheduleClient;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rtext);
        scheduleClient = new ScheduleClient(this);
        scheduleClient.doBindService();
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view1 = inflater.inflate(R.layout.activity_reminder_dialog, null);
        e1=(EditText)view1.findViewById(R.id.etext);
        reminder=(Button)view1.findViewById(R.id.reminder);
        cancel=(Button)view1.findViewById(R.id.cancel);
        final AlertDialog.Builder builder = new AlertDialog.Builder(Reminder_dialog.this);
        builder.setView(view1);

        final AlertDialog d = builder.create();
        cancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                d.dismiss();
                Intent intent = new Intent(Reminder_dialog.this, AlarmActivity.class);
                startActivity(intent);
            }
                                  });
        reminder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                text = e1.getText().toString();

                if (text.equals("")) {
                  //  t1.setText("Enter the reminder");
                    Toast.makeText(Reminder_dialog.this,"Error ",Toast.LENGTH_SHORT).show();
                  //  Toast.makeText(this,"Enter reminder",Toast.LENGTH_SHORT).show();
                } else {

                    title=e1.getText().toString();
                    scheduleClient.setAlarmForNotification(AlarmActivity.c);
                    d.dismiss();
                    startActivity(new Intent(Reminder_dialog.this,AlarmActivity.class));


                }



            }
        });

        d.setCancelable(false);
        d.show();
    }
}
