package com.example.hairdo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.hairdo.model.Customer;
import com.example.hairdo.model.Salon;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class EditDetails extends AppCompatActivity {

    String id ;


    EditText name, address, contact, password, advance, email;
    Button btn, delete;
    ProgressBar pgs;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);

        id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        name = findViewById(R.id.salonNewName);
        address = findViewById(R.id.salonNewAddress);
        contact = findViewById(R.id.salonNewContactNumber);
        advance = findViewById(R.id.salonNewAdvancePayment);
        password = findViewById(R.id.salonNewPassword);
        email = findViewById(R.id.salonEmail);
        btn = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        pgs = findViewById(R.id.detailsProgress);
        ll = findViewById(R.id.bg);

        //get data
        Query query = FirebaseDatabase.getInstance().getReference("Salon").child(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                        Salon cus = snapshot.getValue(Salon.class);
                        name.setText(cus.name);
                        email.setText(cus.email);
                        contact.setText(cus.contact);
                        address.setText(cus.address);
                        password.setText(cus.password);
                        advance.setText(cus.advance);
                    }
                    pgs.setVisibility(View.GONE);
                    ll.setVisibility(View.GONE);
                } }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //edit
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredEmail = email.getText().toString().trim();
                String enteredContact = contact.getText().toString().trim();
                String enteredAddress = address.getText().toString().trim();
                String enteredName = name.getText().toString().trim();
                String enteredPassword = password.getText().toString().trim();
                String enteredAdvance = advance.getText().toString().trim();

                if (enteredName.isEmpty() || enteredName.length() < 5) {
                    name.setError("Salon name is required");
                    name.requestFocus();
                    return;
                } else if (enteredEmail.isEmpty() || !enteredEmail.contains("@") || !enteredEmail.contains(".com")) {
                    email.setError("Valid email is required");
                    email.requestFocus();
                    return;
                } else if (enteredAddress.isEmpty() || enteredAddress.length() < 5) {
                    address.setError("Valid address is required");
                    address.requestFocus();
                    return;
                } else if (enteredContact.length() != 10) {
                    contact.setError("Valid contact number is required");
                    contact.requestFocus();
                    return;
                } else if (enteredPassword.length() < 6) {
                    password.setError("Password should contains uppercase and lowercase characters");
                    password.requestFocus();
                    return;
                } else if (enteredAdvance.isEmpty()) {
                    advance.setError("Advance is required!");
                    advance.requestFocus();
                    return;
                }

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("name", enteredName.toString());
                hashMap.put("advance", enteredAdvance.toString());
                hashMap.put("contact", enteredContact.toString());
                hashMap.put("password", enteredPassword.toString());
                hashMap.put("address", enteredAddress.toString());

                FirebaseDatabase.getInstance().getReference("Salon").child(id).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        startActivity(getIntent());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //failure code
                    }
                });


            }
        });


        //delete account
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("Salon").child(id).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //log out
                    }
                });
            }
        });

    }
}