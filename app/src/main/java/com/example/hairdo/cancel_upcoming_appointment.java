package com.example.hairdo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class cancel_upcoming_appointment extends AppCompatActivity {
    ArrayList<String> salonName = new ArrayList<>();
    ArrayList<String> date = new ArrayList<>();
    ArrayList<String> time = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_upcoming_appointment);
        salonName.add("salonName");
        salonName.add("salonName1");
        salonName.add("salonName2");

        date.add("12.08.2021");
        date.add("14.08.2021");
        date.add("17.08.2021");

        time.add("8.30AM");
        time.add("3.30AM");
        time.add("5.30AM");


        RecyclerView recyclerView = findViewById(R.id.cancel_upcoming_appointment_recylerview);
        cancel_upcoming_appointmentAdpter Adaper4 = new cancel_upcoming_appointmentAdpter(salonName,date,time);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(Adaper4);
    }
}