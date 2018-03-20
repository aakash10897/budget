package com.example.manukanu.expenditure;


public class UserBean {
    String name;
    String pass;
    String mail;


    public UserBean() {}

    public UserBean(String name, String pass,String mail) {
        this.name = name;
        this.pass = pass;
        this.mail=mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}


