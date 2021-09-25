package com.example.hairdo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hairdo.model.Appointment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class upcommingAppointmentsSalon extends Fragment {

    RecyclerView recyclerView;
    ProgressBar pgs;
    TextView nothing;
    String id ;

    public upcommingAppointmentsSalon() {

    }


    public static upcommingAppointmentsSalon newInstance() {
        upcommingAppointmentsSalon fragment = new upcommingAppointmentsSalon();

        return fragment;
    }
    ArrayList<Appointment> myListData = new ArrayList<Appointment>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcomming_appointments_salon, container, false);



        recyclerView =  view.findViewById(R.id.upcommingAppointmentsSalonRecycle);
        UpcomingAppointmentAdapter adapter = new UpcomingAppointmentAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);



        pgs = view.findViewById(R.id.loadAppontment);
        nothing = view.findViewById(R.id.nothing1);

        //fetch data
        Query query = FirebaseDatabase.getInstance().getReference("Appointment").orderByChild("id").equalTo(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Appointment ser = dataSnapshot.getValue(Appointment.class);
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
                    Toast.makeText(view.getContext(), "no appointments available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                pgs.setVisibility(View.GONE);
                nothing.setVisibility(View.VISIBLE);
                Toast.makeText(view.getContext(), "no appointments available", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}