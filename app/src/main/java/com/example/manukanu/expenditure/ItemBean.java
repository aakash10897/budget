package com.example.manukanu.expenditure;

public class ItemBean {
    String iname;
    String price;
    String date;
    int id;
    public ItemBean(){}

    public ItemBean(int id,String iname, String price, String date) {
        this.id = id;
        this.iname = iname;
        this.price = price;
        this.date = date;
    }

    public String getIname() {
        return iname;
    }
    public void setIname(String iname) {
        this.iname = iname;
    }

    public String getPrice() {
        return price;
    }

    public String setPrice(String price) {
        //this.price = price;
        return price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
