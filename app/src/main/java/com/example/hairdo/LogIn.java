package com.example.hairdo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        getSupportActionBar().hide();


    }

    public void onClickFogetPassword(View view){

    }

    public void onClickLogIn(View view){

    }

    public void onClickSignUp(View view){
        Intent intSign = new Intent(this,BothSignUp.class);
        startActivity(intSign);
        finish();
    }

}