
package com.example.hairdo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class AppointmentSummary extends AppCompatActivity {
    ArrayList<String> salonName = new ArrayList<>();
    ArrayList<String> date = new ArrayList<>();
    ArrayList<String> time = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_summary);

        salonName.add("salonName");
        salonName.add("salonName");
        salonName.add("salonName");

        date.add("12.08.2021");
        date.add("12.08.2021");
        date.add("12.08.2021");

        time.add("2.30AM");
        time.add("2.30AM");
        time.add("2.30AM");

        RecyclerView recyclerView = findViewById(R.id.appointment_summary_recycleview2);
        AppointmentSummaryAdapter Adapter2 = new AppointmentSummaryAdapter(salonName, date, time);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(Adapter2);


    }
}