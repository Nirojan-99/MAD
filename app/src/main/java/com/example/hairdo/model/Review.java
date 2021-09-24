package com.example.hairdo.model;

public class Review {

    public String feedback;
    public String date;
    public Float star;
    public String reply;
    public String userid;
    public String userName;
    public String _id;
    public String salonid;

    public  Review (){}

    public Review(String feedback, String date, Float star, String userid,String userName,String salonid) {
        this.feedback = feedback;
        this.date = date;
        this.star = star;
        this.userid = userid;
        this.userName = userName;
        this.salonid = salonid;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
