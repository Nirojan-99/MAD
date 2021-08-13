package com.example.hairdo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserSignUp extends AppCompatActivity {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);

        btn = findViewById(R.id.userSignUp);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),UserProfile.class);
                startActivity(intent);
            }
        });

        //getSupportActionBar().hide();
    }

    public void OnUserSignUp(View v){
//        Intent intent = new Intent(this,OTP.class);
        Intent intent = new Intent(this,UserProfile.class);
        startActivity(intent);
    }
}