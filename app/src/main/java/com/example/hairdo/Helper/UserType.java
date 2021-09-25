package com.example.hairdo.Helper;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.example.hairdo.MainActivity;
import com.example.hairdo.SalonProfile;
import com.example.hairdo.UserProfile;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserType {
    public String type;

    public UserType() {
        this.type = "nothing";
    }

    public void getUserType(String id,String text) {
        type="dddddddd";

        FirebaseDatabase.getInstance().getReference("Customer").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                } else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}
