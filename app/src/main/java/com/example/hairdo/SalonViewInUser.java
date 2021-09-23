package com.example.hairdo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hairdo.Helper.CircleTransform;
import com.example.hairdo.model.Customer;
import com.example.hairdo.model.Gallery;
import com.example.hairdo.model.Salon;
import com.example.hairdo.model.Service;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class SalonViewInUser extends AppCompatActivity {

    ImageView img;
    TextView name, address, ratingCount, contact;
    RatingBar rtb;


    String id;
    ArrayList<Gallery> myListData = new ArrayList<Gallery>();
    HashMap<String, Object> hashMap = new HashMap<>();
    UserViewgalleryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon_view_in_user);

        //intents
        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        //initialize
        img = findViewById(R.id.imageView2);
        name = findViewById(R.id.name);
        address = findViewById(R.id.location);
        rtb = findViewById(R.id.salonRating);
        ratingCount = findViewById(R.id.count);
        contact = findViewById(R.id.contact);

        //another
        RecyclerView recyclerView = findViewById(R.id.galleryRecycler);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(SalonViewInUser.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        adapter = new UserViewgalleryAdapter(myListData,SalonViewInUser.this);
        recyclerView.setAdapter(adapter);

//        //fetch gallery data
        Query query1 = FirebaseDatabase.getInstance().getReference("Gallery").orderByChild("id").equalTo(id);
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Gallery ser = dataSnapshot.getValue(Gallery.class);
                        ser.set_id(snapshot.getKey());
                        myListData.add(ser);
                    }
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SalonViewInUser.this, "no servies available", Toast.LENGTH_SHORT).show();
            }
        });


        //fetch data
        FirebaseDatabase.getInstance().getReference("Salon").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Salon cus = snapshot.getValue(Salon.class);
                    name.setText(cus.name);
                    rtb.setRating(3.5f);
                    ratingCount.setText("102");
                    address.setText(cus.address);
                    contact.setText(cus.contact);
                    if (cus.url != null) {
                        img.setPadding(0, 0, 0, 0);
                        Picasso.with(SalonViewInUser.this).load(Uri.parse(cus.url)).into(img);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void OnReserveClick(View v) {
        Intent intent = new Intent(this, appointment.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void onViewLocationClick(View v) {
        Intent intent = new Intent(this, SalonLocation.class);
//        intent.putExtra("lat",);
//        intent.putExtra("long",);
        startActivity(intent);
    }
}