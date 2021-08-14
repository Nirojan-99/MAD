package com.example.hairdo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class UpcomingAppointments extends AppCompatActivity {

    ArrayList<String> myListData = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_appointments);

        myListData.add("Nirojan");
        myListData.add("Sayanthan");
        myListData.add("Arivaran");
        myListData.add("Lavaniyah");


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.upcomingAppointmentRecycle);
        UpcomingAppointmentAdapter adapter = new UpcomingAppointmentAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


    }
    public void onAppointmentClick(View v){
        Intent intent = new Intent(this,ManageUpcomingApoointment.class);
        startActivity(intent);
    }
}