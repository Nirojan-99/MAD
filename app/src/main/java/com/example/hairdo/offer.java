package com.example.hairdo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hairdo.model.Offer;
import com.example.hairdo.model.Service;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class offer extends AppCompatActivity {

    Button btn;
    Button btnAdd;
    EditText name,des;
    //        String id = auth.getCurrentUser().getUid();
    String id = "oJO7CwPSZjXFTJIungGeaQZQvo33";
    ArrayList<Offer> myListData = new ArrayList<Offer>();
    OffersAdapter adapter ;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);

        name = findViewById(R.id.textView8);
        des = findViewById(R.id.textView10);

        //datepicker


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.offerRecycle);
        adapter = new OffersAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Query query = FirebaseDatabase.getInstance().getReference("Offer").orderByChild("id").equalTo(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Offer ser = dataSnapshot.getValue(Offer.class);
                        myListData.add(ser);
                    }
                    adapter.notifyDataSetChanged();

                }else {
                    Toast.makeText(offer.this, "no offer available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(offer.this, "no offer available", Toast.LENGTH_SHORT).show();
            }
        });

        btn = findViewById(R.id.button2);
        btnAdd = findViewById(R.id.button3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredname = name.getText().toString().trim();
                String enteredDes = des.getText().toString().trim();

                if(enteredname.isEmpty()){
                    name.setError("Require valid offer name");
                    name.requestFocus();
                    return;
                }else if(enteredDes.isEmpty()){
                    des.setError("Require valid description name");
                    des.requestFocus();
                    return;
                }

                Offer offer = new Offer(enteredname,enteredDes,"2021/10/10",id);
                FirebaseDatabase.getInstance().getReference("Offer").push().setValue(offer).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(offer.this, "offer is updated", Toast.LENGTH_SHORT).show();
//                finish();
                        startActivity(getIntent());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(offer.this, "offer is not updated", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });


    }
}