package com.example.hairdo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SalonSignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon_sign_up);

        //getSupportActionBar().hide();
    }
    public void OnSignUpClick(View view){
        Intent intentotp = new Intent(this,OTP.class);
        startActivity(intentotp);
    }
}