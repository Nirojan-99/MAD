package com.example.hairdo.model;

public class Salon {
    public String name;
    public String email;
    public String address;
    public String contact;
    public String password;

    public Salon(){}

    public Salon(String name, String email, String address, String contact, String password) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.contact = contact;
        this.password = password;
    }
}
