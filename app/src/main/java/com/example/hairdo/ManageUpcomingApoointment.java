package com.example.hairdo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hairdo.Helper.CircleTransform;
import com.example.hairdo.model.Appointment;
import com.example.hairdo.model.Customer;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class ManageUpcomingApoointment extends AppCompatActivity {

    TextView txt,cancel,currentStatus;
    TextView txt2;
    ImageView dp;
    EditText status;
    Button update;
    Appointment ser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_upcoming_apoointment);

        txt = findViewById(R.id.appointmentUsername);
        txt2 = findViewById(R.id.time);
        dp = findViewById(R.id.imageView);
        status = findViewById(R.id.status);
        cancel = findViewById(R.id.cancel);
        update = findViewById(R.id.update);
        currentStatus = findViewById(R.id.currentStatus);

        Intent receive = getIntent();
        String id = receive.getStringExtra("id");

        //cancel appointment
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("status", "cancel");

                FirebaseDatabase.getInstance().getReference("Appointment").child(id).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ManageUpcomingApoointment.this, "Appointment canceled", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ManageUpcomingApoointment.this, "Unable to cancel the appointment", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        //update status
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String enteredStatus = status.getText().toString().trim();
                if(enteredStatus.isEmpty()){
                    status.setError("Required valid status");
                    status.requestFocus();
                    return;
                }

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("status", enteredStatus);

                FirebaseDatabase.getInstance().getReference("Appointment").child(id).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ManageUpcomingApoointment.this, "Appointment canceled", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ManageUpcomingApoointment.this, "Unable to cancel the appointment", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        //fetch data
        FirebaseDatabase.getInstance().getReference("Appointment").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    ser = snapshot.getValue(Appointment.class);
                    txt.setText(ser.cname);
                    txt2.setText(ser.date+" | "+ser.time);
                    if(ser.status != null){
                        currentStatus.setText("Current Status : "+ser.status );
                    }else {
                        currentStatus.setText("Current Status : pending" );
                    }

                    FirebaseDatabase.getInstance().getReference("Customer").child(ser.cid).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                Customer cus = snapshot.getValue(Customer.class);
                                if (cus.url != null) {
                                    dp.setPadding(0, 0, 0, 0);
                                    Picasso.with(ManageUpcomingApoointment.this).load(Uri.parse(cus.url)).transform(new CircleTransform()).into(dp);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }else {
                    Toast.makeText(ManageUpcomingApoointment.this, "no appointments available", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ManageUpcomingApoointment.this, "no appointments available", Toast.LENGTH_SHORT).show();
            }
        });

    }
}