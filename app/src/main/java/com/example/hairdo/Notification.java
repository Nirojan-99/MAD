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
import com.example.hairdo.model.Holiday;
import com.example.hairdo.model.NotificationM;
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
    ArrayList<NotificationM> notifList=new ArrayList<>();


    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    String today;
    String cid="oJO7CwPSZjXFTJIungGeaQZQvo33";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        pg=findViewById(R.id.nfPG);
        today=getTodayDate().trim();



        setDataforRecyclerView();
        setAdapter();
    }

    private void setDataforRecyclerView() {

        FirebaseDatabase.getInstance().getReference(Holiday.class.getSimpleName()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {

                    Holiday h = data.getValue(Holiday.class);

                   if(today.equals(h.getDate())){
                       NotificationM n=new NotificationM();
                       n.setTitle("Today is Holiday!!!");
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

        FirebaseDatabase.getInstance().getReference("Appointment").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                for (DataSnapshot data : snapshot.getChildren()) {
                    Appointment a=data.getValue(Appointment.class);

                    if(today.equals(a.getDate()) || (!(a.getStatus().equals("complete"))) ){
                        if (a.getCid().equals(cid)){
                            NotificationM nA=new NotificationM();
                            nA.setTitle("You have Appointment on");
                            nA.setDate(a.getDate());
                            nA.setSub(a.getTime());
                            notifList.add(nA);
                        }

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

    private void setAdapter() {
        recyclerView=findViewById(R.id.notification_RecycleView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        notificationRvAd=new NotificationRvAd(this,notifList);
        recyclerView.setAdapter(notificationRvAd);
    }

    public String getTodayDate() {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        String today=dayOfMonth+"/"+(month+1)+"/"+year;
        return today;

    }
}