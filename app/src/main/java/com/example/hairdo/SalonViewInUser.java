package com.example.hairdo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hairdo.Helper.CircleTransform;
import com.example.hairdo.model.Customer;
import com.example.hairdo.model.Gallery;
import com.example.hairdo.model.Review;
import com.example.hairdo.model.Salon;
import com.example.hairdo.model.Service;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class SalonViewInUser extends AppCompatActivity {

    ImageView img;
    TextView name, address, ratingCount, contact,userName;
    RatingBar rtb,userRtb;
    EditText rating;
    Button btn;
    String id;
    String cusid;
    String cusName;

    //gallery adapter
    ArrayList<Gallery> myListData = new ArrayList<Gallery>();
    UserViewgalleryAdapter adapter;

    //review adapter
    ArrayList<Review>  reviews1 = new ArrayList<Review>();
    UserViewReviewAdapter adapter1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon_view_in_user);

        //intents
        Intent intent = getIntent();
//        id = intent.getStringExtra("id");
        id ="ejHLtEYSByaRAt0p7zp5yMaD9Na2";
        cusid = "oJO7CwPSZjXFTJIungGeaQZQvo33";

        //initialize
        img = findViewById(R.id.imageView2);
        name = findViewById(R.id.name);
        address = findViewById(R.id.location);
        rtb = findViewById(R.id.salonRating);
        ratingCount = findViewById(R.id.count);
        contact = findViewById(R.id.contact);
        userName = findViewById(R.id.reviewUsername);

        btn = findViewById(R.id.addReview);
        userRtb = findViewById(R.id.userRating);
        rating = findViewById(R.id.userReview);

        //recycle review
        RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.reviewrecycler1);
        adapter1 = new UserViewReviewAdapter(reviews1);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView1.setAdapter(adapter1);

        //getting reviews
        Query query5 = FirebaseDatabase.getInstance().getReference("Review").orderByChild("salonid").equalTo(id);
        query5.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Review ser = dataSnapshot.getValue(Review.class);
                        ser.set_id(dataSnapshot.getKey());
                        reviews1.add(ser);
                    }
                    adapter1.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SalonViewInUser.this, "no servies available", Toast.LENGTH_SHORT).show();
            }
        });


        //add rating
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredRating = rating.getText().toString().trim();
                Float star = userRtb.getRating();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                String currentDate = formatter.format(date).toString();

                if(enteredRating.isEmpty()){
                    rating.setError("Require valid feedback");
                    rating.requestFocus();
                    return;
                }

                Review review = new Review(enteredRating,currentDate,star,cusid,cusName,id);

                //add review
                FirebaseDatabase.getInstance().getReference("Review").push().setValue(review).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(SalonViewInUser.this, "review is updated", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(getIntent());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SalonViewInUser.this, "review is not updated", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


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


        //fetch customer data
        FirebaseDatabase.getInstance().getReference("Customer").child(cusid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Customer cus = snapshot.getValue(Customer.class);
                    userName.setText(cus.name);
                    cusName = cus.name;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
        Intent intent = new Intent(this, AppointmentC.class);
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