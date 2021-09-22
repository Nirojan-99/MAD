package com.example.hairdo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class upcommingAppointmentsSalon extends Fragment {

    RecyclerView recyclerView;

    public upcommingAppointmentsSalon() {

    }


    public static upcommingAppointmentsSalon newInstance() {
        upcommingAppointmentsSalon fragment = new upcommingAppointmentsSalon();

        return fragment;
    }
    ArrayList<String> myListData = new ArrayList<String>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_upcomming_appointments_salon, container, false);
// Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcomming_appointments_salon, container, false);
        //recycler view
        myListData.add("Nirojan");
        myListData.add("Sayanthan");
        myListData.add("Arivaran");
        myListData.add("Lavaniyah");
        myListData.add("kama");


        recyclerView =  view.findViewById(R.id.upcommingAppointmentsSalonRecycle);
        UpcomingAppointmentAdapter adapter = new UpcomingAppointmentAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }
}