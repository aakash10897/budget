package com.example.manukanu.expenditure;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomeAdapter extends ArrayAdapter<ItemBean> {
    List <ItemBean> alist;
    Context context;
    TextView category,expense,date;
    public static String c1,e1,d1;
    float price;


    public CustomeAdapter(Context context,List<ItemBean> alist) {
        super(context, R.layout.view_of_list,alist);
        this.context = context;
        this.alist = alist;
    }
   @Override
   public View getView(int position, View convertView, ViewGroup parent) {
       LayoutInflater inflater = (LayoutInflater) context.getSystemService
               (Context.LAYOUT_INFLATER_SERVICE);
       View view = inflater.inflate(R.layout.view_of_list, parent, false);
       category = (TextView) view.findViewById(R.id.category_text);
       expense = (TextView) view.findViewById(R.id.expense_text);
       date = (TextView) view.findViewById(R.id.date_text);
       ItemBean item = alist.get(position);

       price = Float.parseFloat(item.getPrice());
       float remaining_balance = add_money.remaining_balance;

           category.setText(item.getIname());
           expense.setText(item.getPrice());
           date.setText(item.getDate());
           c1 = category.getText().toString();
           e1 = expense.getText().toString();
           d1 = date.getText().toString();
           Log.e("position", alist.get(position).getPrice());



       return view;
   }
}
