package com.example.hairdo.model;

public class Appointment {
    public String id;
    public String cid;
    public String cname;
    public String sname;
    public String date;
    public String time;
    public String advancepayment;
    public String status;
    public String _id;


    public Appointment() {
    }

    public Appointment(String id, String cid, String cname, String sname, String date, String time, String advancepayment, String status) {
        this.id = id;
        this.cid = cid;
        this.cname = cname;
        this.sname = sname;
        this.date = date;
        this.time = time;
        this.advancepayment = advancepayment;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String sid) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAdvancepayment() {
        return advancepayment;
    }

    public void setAdvancepayment(String advancepayment) {
        this.advancepayment = advancepayment;
    }

    public String getStatus() {
        return status;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }



    public void setStatus(String status) {
        this.status = status;
    }


}
