package com.example.hairdo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.hairdo.model.Holiday;
import com.example.hairdo.model.NotificationM;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Notification extends AppCompatActivity {
    ProgressBar pg;
    RecyclerView recyclerView;
    NotificationRvAd notificationRvAd;
    ArrayList<NotificationM> notifList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        pg=findViewById(R.id.nfPG);

        setDataforRecyclerView();
        setAdapter();
    }

    private void setDataforRecyclerView() {

        FirebaseDatabase.getInstance().getReference(Holiday.class.getSimpleName()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {

                    Holiday h = data.getValue(Holiday.class);
                    NotificationM n=new NotificationM();
                   n.setTitle("Today is Holiday");
                   n.setDate(h.getDate());
                   n.setSub(h.getRemark());
                    notifList.add(n);


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
}