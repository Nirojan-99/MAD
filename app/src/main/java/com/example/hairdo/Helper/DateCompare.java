package com.example.hairdo.Helper;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class DateCompare {

    public static boolean comparedates(String date1, String date2) throws ParseException {
        Boolean result = false;
        SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
        Date d1 = sdformat.parse(date1);
        Date d2 = sdformat.parse(date2);

        if (d1.compareTo(d2) > 0) {
            result = false;
        } else if (d1.compareTo(d2) < 0) {
            result = false;
        } else if (d1.compareTo(d2) == 0) {
            result = true;
        }
        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static boolean compareTime(String timeone, String timetwo) {
        boolean result = false;

        if (timeone.equals(timetwo))
            result = true;
        else
            result = false;

        return result;
    }
}
