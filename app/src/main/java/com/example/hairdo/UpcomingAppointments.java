package com.example.hairdo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hairdo.model.Appointment;
import com.example.hairdo.model.Service;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UpcomingAppointments extends AppCompatActivity {

    ArrayList<Appointment> myListData = new ArrayList<Appointment>();
    //        String id = auth.getCurrentUser().getUid();
    String id ;
    ProgressBar pgs;
    TextView nothing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_appointments);

        id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        pgs = findViewById(R.id.appointmentProgress);
        nothing = findViewById(R.id.nothing);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.upcomingAppointmentRecycle);
        UpcomingAppointmentAdapter adapter = new UpcomingAppointmentAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        //fetch data
        Query query = FirebaseDatabase.getInstance().getReference("Appointment").orderByChild("id").equalTo(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Appointment ser = dataSnapshot.getValue(Appointment.class);
                        ser.set_id(dataSnapshot.getKey());
                        ser.set_id(dataSnapshot.getKey());
                        if(ser.status != null){
                            if(ser.status.contains("cancel") || ser.status.contains("completed")){

                            }else {
                                myListData.add(ser);
                            }
                        }else {
                            myListData.add(ser);
                        }
                    }
                    if (myListData.isEmpty()) {
                        nothing.setVisibility(View.VISIBLE);
                    }
                    pgs.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();

                } else {
                    nothing.setVisibility(View.VISIBLE);
                    pgs.setVisibility(View.GONE);
                    Toast.makeText(UpcomingAppointments.this, "no appointments available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                pgs.setVisibility(View.GONE);
                nothing.setVisibility(View.VISIBLE);
                Toast.makeText(UpcomingAppointments.this, "no appointments available", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void onAppointmentClick(View v) {
        Intent intent = new Intent(this, ManageUpcomingApoointment.class);
        startActivity(intent);
    }
}