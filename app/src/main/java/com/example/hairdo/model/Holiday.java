package com.example.hairdo.model;
public class Holiday {
    public String HolidayKey;
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

    public String getSelected_Date() {
        return selected_Date;
    }

    public String getRemark() {
        return remark;
    }

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public String getHolidayKey() {
        return HolidayKey;
    }

    public void setHolidayKey(String holidayKey) {
        HolidayKey = holidayKey;
    }

    public void setSelected_Date(String selected_Date) {
        this.selected_Date = selected_Date;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setDay(String day) {
        this.day = day;
    }
}