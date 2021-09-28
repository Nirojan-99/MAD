package com.example.hairdo.Helper;

import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.hairdo.model.Review;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GetRating {
    int loop = 0;
    Float ratings = 0.0f;
    Float rate = 0.0f;
    String id;

    public  void getRating(String id , RatingBar rtb , TextView ratingCount) {
        FirebaseDatabase.getInstance().getReference("Review").orderByChild("salonid").equalTo(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Review ser = dataSnapshot.getValue(Review.class);
                        loop++;
                        ratings += ser.star;
                    }
                    rate = calRating(ratings,loop);
                    rtb.setRating(rate);
                    ratingCount.setText("("+loop+")");
                } }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    public float calRating(Float total,int loop){
        return total/loop;
    }

    int looplikes = 0;
    public void getLikes(String id, TextView txt){
        FirebaseDatabase.getInstance().getReference("Follow").orderByChild("sid").equalTo(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Review ser = dataSnapshot.getValue(Review.class);
                        looplikes++;
                    }
                    txt.setText("("+looplikes+")");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

}
