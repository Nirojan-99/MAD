package com.example.hairdo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BothSignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_both_sign_up);

        //getSupportActionBar().hide();


    }

    public void OnLogInClick(View view){
        Intent intent1 = new Intent(this,LogIn.class);
        startActivity(intent1);
    }

    public void OnSalonSignUpClick(View view){
        Intent intent2 = new Intent(this,SalonSignUp.class);
        startActivity(intent2);
    }

    public void OnCustomerSignUpClick(View view){
        Intent intent3 = new Intent(this,UserSignUp.class);
        startActivity(intent3);
    }
}