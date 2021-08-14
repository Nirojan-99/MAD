package com.example.hairdo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class Holidays extends AppCompatActivity {
    ArrayList<String> holidayList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holidays);

        holidayList.add("15 November 2021");
        holidayList.add("30 November 2021");
        holidayList.add("30 August 2021");
        holidayList.add("15 October 2021");
        holidayList.add("15 September 2021");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.holidays_RecycleView);
        ServicesAdapter adapter = new ServicesAdapter(holidayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


    public void goToCalender(View view) {
        Intent intent = new Intent(this, Calendar.class);
        startActivity(intent);
    }
}