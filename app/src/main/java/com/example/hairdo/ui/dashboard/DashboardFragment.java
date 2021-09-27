package com.example.hairdo.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hairdo.Helper.SummaryEvent;
import com.example.hairdo.R;

import com.example.hairdo.UpcomingAppointmentAdapter;
import com.example.hairdo.upcommingAppointmentsSalon;
import com.google.firebase.auth.FirebaseAuth;

public class DashboardFragment extends Fragment {

    RecyclerView recylerView;
    ProgressBar pgs;
    TextView total, completed, todayPayout, previousPayout;
    //        String id = auth.getCurrentUser().getUid();
    String id ;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Fragment fragment;
        fragment = new upcommingAppointmentsSalon();
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.appointents, fragment);
        ft.commit();

        id= FirebaseAuth.getInstance().getCurrentUser().getUid();

        setHasOptionsMenu(true);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        total = root.findViewById(R.id.total);
        completed = root.findViewById(R.id.completed);
        pgs = root.findViewById(R.id.todaySummary);
        todayPayout = root.findViewById(R.id.todaypayout);
        previousPayout = root.findViewById(R.id.previousPayout);

        SummaryEvent summaryEvent = new SummaryEvent();
        summaryEvent.getTodayEvents(id, total, completed, pgs);

        SummaryEvent summaryEvent1 = new SummaryEvent();
        summaryEvent1.getPaymentSummary(id,todayPayout,previousPayout);

        return root;
    }
}