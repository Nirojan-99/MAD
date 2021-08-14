package com.example.hairdo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SalonViewInUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon_view_in_user);
    }

    public void OnReserveClick(View v){
        Intent intent = new Intent(this,appointment.class);
        startActivity(intent);
    }
    public void onViewLocationClick (View v){
        Intent intent = new Intent(this,SalonLocation.class);
        startActivity(intent);
    }
}