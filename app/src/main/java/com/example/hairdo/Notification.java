package com.example.hairdo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.hairdo.model.Appointment;
import com.example.hairdo.model.Follow;
import com.example.hairdo.model.Holiday;
import com.example.hairdo.model.NotificationM;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;

public class Notification extends AppCompatActivity {
    ProgressBar pg;
    RecyclerView recyclerView;
    NotificationRvAd notificationRvAd;
    ArrayList<NotificationM> notifList = new ArrayList<>();


    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    String today;
    String cid;
    String sid = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        pg = findViewById(R.id.nfPG);
        today = getTodayDate().trim();
        cid = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        cid = "wNRfHTezSmNNdBO4qBlFxCftWc63";
        getSalonID(cid);
        pg.setVisibility(View.VISIBLE);

        setDataforRecyclerView();
        setAdapter();
    }

    private void getSalonID(String cid) {


        FirebaseDatabase.getInstance().getReference("Follow").orderByChild("cid").equalTo(cid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        Follow f = data.getValue(Follow.class);
                        sid = f.sid;
                        setHolidayDataforRecyclerView(sid);

//                       setDataforRecyclerView(sid);
//                       setAdapter();
                    }

                } else {
                    pg.setVisibility(View.GONE);
                    Toast.makeText(Notification.this, "No Holiday", Toast.LENGTH_SHORT).show();
                }

            }

            private void setHolidayDataforRecyclerView(String sid) {
                FirebaseDatabase.getInstance().getReference(Holiday.class.getSimpleName()).orderByChild("sid").equalTo(sid).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        for (DataSnapshot data : snapshot.getChildren()) {
                            Holiday h = data.getValue(Holiday.class);
                            if (today.equals(h.getDate())) {
                                NotificationM n = new NotificationM();
                                n.setTitle("Today is Holiday in "+ h.sname +"!!");
                                n.setDate(h.getDate());
                                n.setSub(h.getRemark());
                                notifList.add(n);
                            }

                        }
                        pg.setVisibility(View.GONE);
                        notificationRvAd.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
        });

    }

    private void setDataforRecyclerView() {

        FirebaseDatabase.getInstance().getReference("Appointment").orderByChild("cid").equalTo(cid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot data : snapshot.getChildren()) {
                        Appointment a = data.getValue(Appointment.class);

                        if (today.equals(a.getDate()) || (!(a.getStatus().equals("completed")))) {
                            if (a.getCid().equals(cid)) {
                                NotificationM nA = new NotificationM();
                                nA.setTitle("You have Appointment in "+ a.getSname());
                                nA.setDate(a.getDate() + " | " + a.getTime());
                                nA.setSub("Status: " + a.getStatus());
                                notifList.add(nA);
                            }
                        }
                    }
                    pg.setVisibility(View.GONE);
                    notificationRvAd.notifyDataSetChanged();
                    
                }
                else {
                    pg.setVisibility(View.GONE);
                    Toast.makeText(Notification.this, "No Appointments Today", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }

    private void setAdapter() {
        recyclerView = findViewById(R.id.notification_RecycleView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        notificationRvAd = new NotificationRvAd(this, notifList);
        recyclerView.setAdapter(notificationRvAd);
    }

    public String getTodayDate() {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        String today = dayOfMonth + "/" + (month + 1) + "/" + year;
        return today;

    }
}