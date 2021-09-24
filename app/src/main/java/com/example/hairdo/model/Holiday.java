package com.example.hairdo.model;
public class Holiday {
    public String selected_Date;
    public String remark;
    public String year;
    public String month;
    public String day;

    public Holiday(String selected_Date, String remark, String year, String month, String day) {
        this.selected_Date = selected_Date;
        this.remark = remark;
        this.year = year;
        this.month = month;
        this.day = day;
    }
}
