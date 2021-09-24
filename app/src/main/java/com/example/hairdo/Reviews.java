package com.example.hairdo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.hairdo.model.Review;
import com.example.hairdo.model.Service;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Reviews extends AppCompatActivity {

    ArrayList<Review> data = new ArrayList<>();
    //        String id = auth.getCurrentUser().getUid();
    String id = "ejHLtEYSByaRAt0p7zp5yMaD9Na2";
    ProgressBar pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        pg = findViewById(R.id.progress);

        RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.reviewsRecycleLayout);
        ReviewsAdapter adapter1 = new ReviewsAdapter(data);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView1.setAdapter(adapter1);

        //fetch data
        Query query5 = FirebaseDatabase.getInstance().getReference("Review").orderByChild("salonid").equalTo(id);
        query5.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Review ser = dataSnapshot.getValue(Review.class);
                        ser.set_id(dataSnapshot.getKey());
                        data.add(ser);
                    }
                    pg.setVisibility(View.GONE);
                    adapter1.notifyDataSetChanged();

                }else {
                    pg.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                pg.setVisibility(View.GONE);
                Toast.makeText(Reviews.this, "no servies available", Toast.LENGTH_SHORT).show();
            }
        });


    }
}