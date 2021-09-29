package com.example.hairdo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

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

    private String id;
    private String password1;
    private Salon cus;

    private EditText name, address, contact, password, advance, email;
    private Button btn, delete;
    private ProgressBar pgs;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);

        //user id
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

        //get salon data
        fetchSalonData();


        //edit details
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
                } else if (enteredEmail.isEmpty() || !enteredEmail.contains("@") || !enteredEmail.endsWith(".com")) {
                    email.setError("Valid email is required");
                    email.requestFocus();
                    return;
                } else if (enteredAddress.isEmpty() || enteredAddress.length() < 5) {
                    address.setError("Valid address is required");
                    address.requestFocus();
                    return;
                } else if (enteredContact.length() != 10 || !enteredContact.startsWith("0")) {
                    contact.setError("Valid contact number is required");
                    contact.requestFocus();
                    return;
                } else if (enteredAdvance.isEmpty()) {
                    advance.setError("Advance is required!");
                    advance.requestFocus();
                    return;
                }

                HashMap<String, Object> hashMap = new HashMap<>();

                if (!enteredAddress.equals(cus.address)) {
                    hashMap.put("address", enteredAddress);
                }
                if (!enteredName.equals(cus.name)) {
                    hashMap.put("name", enteredName);
                }
                if (!enteredAdvance.equals(cus.advance)) {
                    hashMap.put("advance", enteredAdvance);
                }
                if (!enteredContact.equals(cus.contact)) {
                    hashMap.put("contact", enteredContact);
                }
                if (!(enteredPassword.equals(cus.password)) && !(enteredPassword.isEmpty())) {
                    hashMap.put("password", enteredPassword);
                }


                FirebaseDatabase.getInstance().getReference("Salon").child(id).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //update password
                        if (!(enteredPassword.equals(cus.password)) && !(enteredPassword.isEmpty())) {
                            FirebaseAuth.getInstance().getCurrentUser().updatePassword(enteredPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    fetchSalonData();
                                    Toast.makeText(EditDetails.this,"Updated" ,Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(EditDetails.this, "not updated", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditDetails.this, "not updated", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });


        //delete account
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

    }

    //show dialog box
    private void showDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.delete_popup);

        EditText password = dialog.findViewById(R.id.passwordDelete);
        Button btn = dialog.findViewById(R.id.delete);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String enteredPassword = password.getText().toString().trim();

                if (enteredPassword.isEmpty()) {
                    password.setError("Require valid password!");
                    password.requestFocus();
                    return;
                }

                if (cus.password.equals(enteredPassword)) {
                    Toast.makeText(EditDetails.this, "password correct", Toast.LENGTH_SHORT).show();
                    FirebaseAuth.getInstance().getCurrentUser().delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            FirebaseDatabase.getInstance().getReference("Salon").child(id).removeValue();
                            FirebaseAuth.getInstance().signOut();
                            Intent intent = new Intent(EditDetails.this, LogIn.class);
                            startActivity(intent);
                        }
                    });

                } else {
                    Toast.makeText(EditDetails.this, "incorrect password", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }


    //fetch salon data
    public void fetchSalonData(){
        Query query = FirebaseDatabase.getInstance().getReference("Salon").child(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                        cus = snapshot.getValue(Salon.class);
                        name.setText(cus.name);
                        email.setText(cus.email);
                        password1 = cus.password;
                        contact.setText(cus.contact);
                        address.setText(cus.address);
                        advance.setText(cus.advance);
                    }
                    pgs.setVisibility(View.GONE);
                    ll.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}