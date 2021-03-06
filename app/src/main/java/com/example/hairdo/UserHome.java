package com.example.hairdo;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hairdo.model.Appointment;
import com.example.hairdo.model.Salon;
import com.example.hairdo.model.Service;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


public class UserHome extends Fragment {

    //        String id = auth.getCurrentUser().getUid();
    String id ;
    ArrayList<Salon> myListData = new ArrayList<Salon>();
    UserViewSalonList adapter ;
    ProgressBar pgs;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_home, container, false);

        id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //fetch recent salons
        FirebaseDatabase.getInstance().getReference("Appointment").orderByChild("cid").equalTo(id).limitToFirst(3).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Appointment ser = dataSnapshot.getValue(Appointment.class);
                    }}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //set adapter
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.salons);
        adapter = new UserViewSalonList(myListData,view.getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        //initialize
        pgs = view.findViewById(R.id.salonsProgress);

        //fetch salons
        Query query = FirebaseDatabase.getInstance().getReference("Salon");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Salon ser = dataSnapshot.getValue(Salon.class);
                        ser.set_id(dataSnapshot.getKey());
                        myListData.add(ser);
                    }
                    pgs.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(view.getContext(), "no servies available", Toast.LENGTH_SHORT).show();
            }
        });

        return  view;
    }


}