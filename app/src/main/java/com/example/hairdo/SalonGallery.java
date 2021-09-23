package com.example.hairdo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hairdo.model.Gallery;
import com.example.hairdo.model.Salon;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SalonGallery extends AppCompatActivity {

    //        String id = auth.getCurrentUser().getUid();
    String id = "ejHLtEYSByaRAt0p7zp5yMaD9Na2";
    ArrayList<Gallery> myListData = new ArrayList<Gallery>();
    GalleryAdapter adapter ;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon_gallery);

        //set adapter
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.galleryrecycler);
        adapter = new GalleryAdapter(myListData,SalonGallery.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(SalonGallery.this));
        recyclerView.setAdapter(adapter);

        //initialize
        btn=findViewById(R.id.addnew);

        //press button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SalonGallery.this,DpUpload.class);
                intent.putExtra("type","Gallery");
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        //fetch salons
        Query query = FirebaseDatabase.getInstance().getReference("Gallery").orderByChild("id").equalTo(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Gallery ser = dataSnapshot.getValue(Gallery.class);
                        myListData.add(ser);
                    }
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SalonGallery.this, "no servies available", Toast.LENGTH_SHORT).show();
            }
        });
    }
}