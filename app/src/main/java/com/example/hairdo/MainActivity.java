package com.example.hairdo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.hairdo.model.Offer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    String id;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            id = null;
        }else {

            id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }

        if(id != null){
            FirebaseDatabase.getInstance().getReference("Customer").child(id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        intent = new Intent(MainActivity.this,UserProfile.class);
                        finish();
                        startActivity(intent);
                    }else {
                        intent = new Intent(MainActivity.this,SalonProfile.class);

                        finish();
                        startActivity(intent);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else {

            new android.os.Handler(Looper.getMainLooper()).postDelayed(
                    new Runnable() {
                        public void run() {
                            Intent send = new Intent(MainActivity.this, LogIn.class);
                            startActivity(send);
                            finish();
                        }
                    },
                    1000);
        }

//        Intent send = new Intent(MainActivity.this, Payment.class);
//                            startActivity(send);
//                            finish();

    }
}