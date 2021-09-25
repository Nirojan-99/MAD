package com.example.hairdo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hairdo.model.PaymentModel;
import com.example.hairdo.model.Service;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class PaymentSummary extends AppCompatActivity {
    ArrayList<PaymentModel> data = new ArrayList<PaymentModel>();

    ProgressBar pgs, pgs1;
    TextView remove, nothing, holderName, cardNumber;

    String id ;
    PaymentModel paymentdisplay = new PaymentModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_summary);

        id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //initialize
        pgs = findViewById(R.id.paymentProgress);
        pgs1 = findViewById(R.id.cardProgress);
        remove = findViewById(R.id.removePayment);
        nothing = findViewById(R.id.nothing);
        holderName = findViewById(R.id.holder);
        cardNumber = findViewById(R.id.number);

        //remove
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("cardNumber", null);
                hashMap.put("month", null);
                hashMap.put("year", null);
                hashMap.put("cvc", null);
                Query query = FirebaseDatabase.getInstance().getReference("Payment").orderByChild("userId").equalTo(id);

//                Query query1 = FirebaseDatabase.getInstance().getReference("Payment").orderByChild("userId").equalTo(id);
//                query1.addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
////                        startActivity(getContext().getIntent());
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        //failure code
//                    }
//                });
            }
        });


        RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.paymentSummaryRecycle);
        PaymentSummaryAdapter adapter1 = new PaymentSummaryAdapter(data);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView1.setAdapter(adapter1);

        //fetch payment data
        Query query = FirebaseDatabase.getInstance().getReference("Payment").orderByChild("userId").equalTo(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        PaymentModel ser1 = dataSnapshot.getValue(PaymentModel.class);
                        ser1.set_id(dataSnapshot.getKey());
                        data.add(ser1);
                    }
                    adapter1.notifyDataSetChanged();
                    if (data.isEmpty()) {
                        nothing.setVisibility(View.VISIBLE);
                    }
                    pgs.setVisibility(View.GONE);

                } else {
                    nothing.setVisibility(View.VISIBLE);
                    pgs.setVisibility(View.GONE);
                    Toast.makeText(PaymentSummary.this, "no data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(PaymentSummary.this, "no payment details available", Toast.LENGTH_SHORT).show();
            }
        });


        //fetch card details
        Query query1 = FirebaseDatabase.getInstance().getReference("Payment").orderByChild("userId").equalTo(id).limitToFirst(1);
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        PaymentModel ser = dataSnapshot.getValue(PaymentModel.class);
                        holderName.setText(ser.nameOnCard);
                        holderName.setVisibility(View.VISIBLE);
                        cardNumber.setVisibility(View.VISIBLE);
                        remove.setVisibility(View.VISIBLE);
                        cardNumber.setText(ser.cardNumber.substring(0, 4) + "*********");
                        paymentdisplay.set_id(dataSnapshot.getKey());
                    }
                    pgs1.setVisibility(View.GONE);
                } else {
                    pgs1.setVisibility(View.GONE);
                    holderName.setVisibility(View.VISIBLE);
                    holderName.setText("No data available!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(PaymentSummary.this, "no payment details available", Toast.LENGTH_SHORT).show();
            }
        });


    }
}