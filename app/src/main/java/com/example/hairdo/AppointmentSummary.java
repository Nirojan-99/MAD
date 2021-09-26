
package com.example.hairdo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;


import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hairdo.model.Appointment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AppointmentSummary extends AppCompatActivity {

       List<Appointment> appointment;
      AppointmentSummaryAdapter Adapter;


//      ProgressBar  pgs1 ;
      TextView nothing;

    String id ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ProgressBar pgs1 = (ProgressBar)findViewById(R.id.cardProgress);
        nothing = findViewById(R.id.nothing);


        id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        setContentView(R.layout.activity_appointment_summary);
        RecyclerView recyclerView = findViewById(R.id.appointment_summary_recycleview2);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        appointment = new ArrayList<>();

        Query query = FirebaseDatabase.getInstance().getReference("Appointment").orderByChild("cid").equalTo(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Appointment data = dataSnapshot.getValue(Appointment.class);
                        data.set_id(dataSnapshot.getKey());
                        appointment.add(data);
                    }
                    Adapter = new AppointmentSummaryAdapter(appointment);
                    recyclerView.setAdapter(Adapter);
                    Adapter.notifyDataSetChanged();

                }else{

                    Toast.makeText(AppointmentSummary.this, "no data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });




    }
}