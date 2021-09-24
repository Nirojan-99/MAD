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
import android.widget.ProgressBar;
import android.widget.TextView;
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
import java.util.Calendar;
import java.util.HashMap;

public class offer extends AppCompatActivity {

    //calendar things
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;

    Button btn;
    Button btnAdd;
    EditText name, des, date;
    ProgressBar pgs;
    TextView nothing;

    //        String id = auth.getCurrentUser().getUid();
    String id = "oJO7CwPSZjXFTJIungGeaQZQvo33";
    ArrayList<Offer> myListData = new ArrayList<Offer>();
    OffersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);

        name = findViewById(R.id.textView8);
        des = findViewById(R.id.textView10);
        date = findViewById(R.id.textView101);
        pgs = findViewById(R.id.offerID);
        nothing = findViewById(R.id.nothing);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.offerRecycle);
        adapter = new OffersAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        //fetch data
        Query query = FirebaseDatabase.getInstance().getReference("Offer").orderByChild("id").equalTo(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Offer ser = dataSnapshot.getValue(Offer.class);
                        ser.set_id(dataSnapshot.getKey());
                        myListData.add(ser);
                    }
                    pgs.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();

                } else {
                    pgs.setVisibility(View.GONE);
                    nothing.setVisibility(View.VISIBLE);
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
                calendar = java.util.Calendar.getInstance();
                year = calendar.get(java.util.Calendar.YEAR);
                month = calendar.get(java.util.Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(offer.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        date.setText(day + "/" + (month + 1) + "/" + year);

                    }
                }, year, month, dayOfMonth);

                datePickerDialog.show();
            }
        });

        //add new
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredname = name.getText().toString().trim();
                String enteredDes = des.getText().toString().trim();

                if (enteredname.isEmpty()) {
                    name.setError("Require valid offer name");
                    name.requestFocus();
                    return;
                } else if (enteredDes.isEmpty()) {
                    des.setError("Require valid description name");
                    des.requestFocus();
                    return;
                } else if (date.getText().toString().trim().isEmpty()) {
                    date.setError("Require valid date");
                    date.requestFocus();
                    return;
                }

                Offer offer = new Offer(enteredname, enteredDes, date.getText().toString(), id);
                FirebaseDatabase.getInstance().getReference("Offer").push().setValue(offer).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(offer.this, "offer is updated", Toast.LENGTH_SHORT).show();
                        finish();
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