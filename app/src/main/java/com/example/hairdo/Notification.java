package com.example.hairdo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Notification extends AppCompatActivity {

    ArrayList<String> notificationsList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        notificationsList.add("Notification 1");
        notificationsList.add("Notification 2");
        notificationsList.add("Notification 3");
        notificationsList.add("Notification 4");
        notificationsList.add("Notification 5");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.notification_RecycleView);
        ServicesAdapter adapter = new ServicesAdapter(notificationsList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}