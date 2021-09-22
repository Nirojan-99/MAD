package com.example.hairdo.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hairdo.ServicesAdapter;
import com.example.hairdo.R;
import com.example.hairdo.UpcomingAppointments;
import com.example.hairdo.upcommingAppointmentsSalon;

public class DashboardFragment extends Fragment {

    RecyclerView recylerView;
    ServicesAdapter appointmentAdapter;

    String names[] = {"nirojan", "kamal", "karan", "kumar"};


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

//        recylerView = recylerView.findViewById(R.id.upcomingAppointments);
//        appointmentAdapter = new AppointmentAdapter(this,names);
//
//        //recylerView.setLayoutManager(new LinearLayoutManager(Context.this));
//
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        recylerView.setLayoutManager(layoutManager);
//
//        recylerView.setAdapter(appointmentAdapter);


//        View view  = LayoutInflater.from(getContext()).inflate(R.layout.upcoming_appointments,container,false);
//        RecyclerView recyclerView = view.findViewById(R.id.upcomingAppointments);
//        recylerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
//        recylerView.setAdapter(new AppointmentAdapter(names));
        Fragment fragment;

        fragment = new upcommingAppointmentsSalon();
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.appointents,fragment);
        ft.commit();

        setHasOptionsMenu(true);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);


        return root;
    }
}