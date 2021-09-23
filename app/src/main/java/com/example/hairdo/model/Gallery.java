package com.example.hairdo.model;

public class Gallery {

    public String url;
    public String id;
    public String _id;

    public Gallery(){}

    public Gallery(String url, String id) {
        this.url = url;
        this.id = id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
