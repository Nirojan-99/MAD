package com.example.hairdo.model;
public class Holiday {
    public String fbKey;
    public String selected_Date;
    public String remark;
    public String date;
//    public String year;
//    public String month;
//    public String day;

    public Holiday() {

    }

    public Holiday(String selected_Date, String remark,String formatDate) {
        this.selected_Date = selected_Date;
        this.remark = remark;
        this.date=formatDate;
//        this.year = year;
//        this.month = month;
//        this.day = day;
    }

    public String getSelected_Date() {
        return selected_Date;
    }

    public String getRemark() {
        return remark;
    }

//    public String getYear() {
//        return year;
//    }
//
//    public String getMonth() {
//        return month;
//    }
//
//    public String getDay() {
//        return day;
//    }

    public void setSelected_Date(String selected_Date) {
        this.selected_Date = selected_Date;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

//    public void setYear(String year) {
//        this.year = year;
//    }
//
//    public void setMonth(String month) {
//        this.month = month;
//    }
//
//    public void setDay(String day) {
//        this.day = day;
//    }

    public String getFbKey() {
        return fbKey;
    }

    public void setFbKey(String fbKey) {
        this.fbKey = fbKey;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
