package com.example.hairdo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ManageUpcomingApoointment extends AppCompatActivity {

    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_upcoming_apoointment);

        txt = findViewById(R.id.UserAppointmentName);
        txt.setText("Nirojan\'s Appointment");
    }
}