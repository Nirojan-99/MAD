package com.example.hairdo.Helper;

import android.util.Patterns;
import java.text.ParseException;
import android.os.Build;
import androidx.annotation.RequiresApi;


public class ValidEmail {
    public static boolean isValidEmail(String email){
        Boolean result=false;

        if (email.isEmpty()){
            result=false;
        }

        if (email.contains("@") && email.contains(".com") && email.length()>=8){
            result=true;
        }
        return result;
    }
}
