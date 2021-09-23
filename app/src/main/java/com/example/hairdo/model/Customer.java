package com.example.hairdo.model;

import android.net.Uri;

public class Customer {
    public String name;
    public String email;
    public String address;
    public String contact;
    public String password;
    public String gender;
    public String url;
    public String _id;

    public  Customer(){}

    public Customer(String name, String email, String address, String contact, String password) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.contact = contact;
        this.password = password;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
