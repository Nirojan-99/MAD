package com.example.hairdo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hairdo.model.Salon;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class SalonSignUp extends AppCompatActivity {

    private FirebaseAuth auth;
    EditText salonname, email, address, contactNum, password;
    Button btn;
    CheckBox ctd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon_sign_up);

        auth = FirebaseAuth.getInstance();

        salonname = findViewById(R.id.SignUpSalonName);
        email = findViewById(R.id.SignUpSalonEmail);
        address = findViewById(R.id.SignUpSalonAddress);
        contactNum = findViewById(R.id.SignUpSalonContact);
        password = findViewById(R.id.SignUpSalonPassword);

        btn = findViewById(R.id.signupbtn);
        ctd = findViewById(R.id.SalonTerms);
        btn.setEnabled(false);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredName = salonname.getText().toString().trim();
                String enteredEmail = email.getText().toString().trim();
                String enteredAddress = address.getText().toString().trim();
                String enteredContact = contactNum.getText().toString().trim();
                String enteredPassword = password.getText().toString().trim();

                if (enteredName.isEmpty() || enteredName.length() < 5) {
                    salonname.setError("Salon name is required");
                    salonname.requestFocus();
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
                    contactNum.setError("Valid contact number is required");
                    contactNum.requestFocus();
                    return;
                } else if (enteredPassword.length() < 6) {
                    password.setError("Password should contains uppercase and lowercase characters");
                    password.requestFocus();
                    return;
                }

                auth.createUserWithEmailAndPassword(enteredEmail, enteredPassword).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Salon salon = new Salon(enteredName, enteredEmail, enteredAddress, enteredContact, enteredPassword);
                        FirebaseDatabase.getInstance().getReference("Salon").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(salon).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(SalonSignUp.this, "User has been registered successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SalonSignUp.this, LogIn.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(SalonSignUp.this, "Fail to register! try again.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(SalonSignUp.this, "Fail to register! try again.", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }

    public void onCheckboxclick(View view) {
        btn.setEnabled(ctd.isChecked());
    }


}