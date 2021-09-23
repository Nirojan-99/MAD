package com.example.hairdo.model;

public class Offer {
    public String name;
    public String description;
    public String validDate;
    public String id;
    public String _id;

    public Offer(){}

    public Offer(String name, String description, String validDate,String id) {
        this.name = name;
        this.description = description;
        this.validDate = validDate;
        this.id = id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
