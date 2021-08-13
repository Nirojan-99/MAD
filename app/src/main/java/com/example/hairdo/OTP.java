package com.example.hairdo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OTP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p);

        //getSupportActionBar().hide();
    }

    public void OnResendOTPClick(View view) {

    }
    public void onOTPSubmitClick(View view) {

            Intent intentHome = new Intent(this,SalonProfile.class);
            startActivity(intentHome);

    }
}