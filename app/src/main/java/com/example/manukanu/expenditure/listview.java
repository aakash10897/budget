package com.example.manukanu.expenditure;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.VersionInfo;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class listview extends AppCompatActivity {

    ListView listView;
    public static TextView balance_text;
    public static String iname, price, date;
    public static Integer id;
    public Integer id_delete;
    public List<ItemBean> ar1 = new ArrayList<ItemBean>();
    add_database ad;
    public static CustomeAdapter customeAdapter;
    private Calendar calendar;
    private int year, month, day;
    TextView date_text;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_listview);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setSubtitleTextColor(android.graphics.Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        date_text =(TextView)findViewById(R.id.date_text);

        calendar = Calendar.getInstance();

        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        showDate(year, month);



        ad = new add_database(listview.this);


        balance_text = (TextView) findViewById(R.id.balance_text);
        balance_text.setText("Balance = " + ad.getRemainingBudget());


        listView = (ListView) findViewById(R.id.list);
        ad = new add_database(this);
       float remaining_balance = ad.getRemainingBudget();

        ar1 = ad.getBudgetByMonthAndYear(String.valueOf(year), String.valueOf(month + 1));

        for (ItemBean i : ar1) {

            int pr = Integer.parseInt(i.getPrice());

            customeAdapter = new CustomeAdapter(this, ar1);
            customeAdapter.notifyDataSetChanged();

            listView.setAdapter(customeAdapter);

            registerForContextMenu(listView);


        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        menu.add("Edit");
        menu.add("Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Log.d("Menu Item ", ar1.get(info.position).getIname() + "  " + item.getTitle().toString());
        iname = ar1.get(info.position).getIname();
        price = ar1.get(info.position).getPrice();
        date = ar1.get(info.position).getDate();
        id = ar1.get(info.position).getId();

        if (item.getTitle().equals("Edit")) {
            Intent intent = new Intent(listview.this, edit_fields.class);
            startActivity(intent);
        } else if (item.getTitle().equals("Delete")) {
            iname = ar1.get(info.position).getIname();
            price = ar1.get(info.position).getPrice();
            date = ar1.get(info.position).getDate();
            Integer pr = Integer.parseInt(price);
            id_delete = ar1.get(info.position).getId();
            //listview.customeAdapter.notifyDataSetChanged();
            //listview.balance_text.notify
            long l = ad.deleteItem(id_delete);
            // listview.customeAdapter.notifyDataSetChanged();
            if (l > 0) {
                listview.customeAdapter.remove(ar1.get(info.position));
                listview.customeAdapter.notifyDataSetChanged();
                ad.updateRemainingBudget_add(pr);
                float bal = ad.getRemainingBudget();
                balance_text.setText("Balance= " + ad.getRemainingBudget());
                Log.e("!!!!", " " + bal);
            }
        }
        return true;
    }

    public void balance(View v) {
        balance_text.setText("Balance = " + ad.getRemainingBudget());
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(listview.this, moneyused.class));
    }



    //Date View-------------------------

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
                                      int year, int month, int day) {

                    ar1 = ad.getBudgetByMonthAndYear(String.valueOf(year), String.valueOf(month + 1));
                    Log.d("budget data", ar1.toString() + " " + year + " " + month);
                    if (ar1.size()!=0) {
                        for (ItemBean i : ar1) {

                            int pr = Integer.parseInt(i.getPrice());

                            customeAdapter = new CustomeAdapter(listview.this, ar1);
                            customeAdapter.notifyDataSetChanged();
                            listview.customeAdapter.notifyDataSetChanged();

                            listView.setAdapter(customeAdapter);

                            registerForContextMenu(listView);
                        }

                    }

                    else{
                       Toast.makeText(listview.this,"Empty List",Toast.LENGTH_SHORT).show();;
                        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        final View view1 = inflater.inflate(R.layout.empty_list_view, null);
                        TextView t1=(TextView)view1.findViewById(R.id.EmptyTextMsg);
                        listView.setEmptyView(t1);

                        customeAdapter = new CustomeAdapter(listview.this, ar1);

                        listview.customeAdapter.notifyDataSetChanged();
                        listView.setAdapter(customeAdapter);
                    }
                    
                    date_text.setText(new StringBuilder()
                            .append(getMonthName(month)).append("/").append(year));
                    toolbar.setTitle("Budget of "+getMonthName(month)+" "+year);

                }

            };

    private void showDate(int year, int month) {
        date_text.setText(new StringBuilder()
                .append(getMonthName(month)).append("/").append(year));
        toolbar.setTitle("Budget of "+getMonthName(month)+" "+year);

    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);

        showDialog(999);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public String getMonthName(int m){
        String month="";
        switch(m){
            case 0:
                month = "Jan";
                break;
            case 1:
                month = "Feb";
                break;
            case 2:
                month = "Mar";
                break;
            case 3:
                month = "Apr";
                break;
            case 4:
                month = "May";
                break;
            case 5:
                month = "June";
                break;

            case 6:
                month = "July";
                break;
            case 7:
                month = "Aug";
                break;
            case 8:
                month = "Sep";
                break;
            case 9:
                month = "Oct";
                break;
            case 10:
                month = "Nov";
                break;
            case 11:
                month = "Dec";
                break;


        }
        return month;

    }

}








