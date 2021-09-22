package com.example.hairdo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ManageUpcomingApoointment extends AppCompatActivity {

    TextView txt;
    TextView txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_upcoming_apoointment);

        txt = findViewById(R.id.UserAppointmentName);
        txt2 = findViewById(R.id.appointmentUsername);

        Intent receive = getIntent();
        String name = receive.getStringExtra("userName");
        txt.setText(name+"\'s Appointment");
        txt2.setText(name);

    }
}