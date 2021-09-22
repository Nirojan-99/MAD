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

import com.example.hairdo.model.Customer;
import com.example.hairdo.model.Salon;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class UserSignUp extends AppCompatActivity {
    Button btn;
    CheckBox ctd;
    EditText name, email, address, contact, password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);

        btn = findViewById(R.id.userSignUp);
        ctd = findViewById(R.id.UserTerms);
        btn.setEnabled(false);
        auth = FirebaseAuth.getInstance();

        name = findViewById(R.id.SignUpSalonName);
        email = findViewById(R.id.SignUpSalonEmail);
        address = findViewById(R.id.SignUpSalonAddress);
        contact = findViewById(R.id.SignUpSalonContact);
        password = findViewById(R.id.SignUpSalonPassword);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredName = name.getText().toString().trim();
                String enteredEmail = email.getText().toString().trim();
                String enteredAddress = address.getText().toString().trim();
                String enteredContact = contact.getText().toString().trim();
                String enteredPassword = password.getText().toString().trim();

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
                }
//                Toast.makeText(UserSignUp.this, enteredEmail+" "+enteredPassword, Toast.LENGTH_SHORT).show();

                auth.createUserWithEmailAndPassword(enteredEmail, enteredPassword).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Customer cus = new Customer(enteredName, enteredEmail, enteredAddress, enteredContact, enteredPassword);
                        FirebaseDatabase.getInstance().getReference("Customer").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(cus).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(UserSignUp.this, "User has been registered successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(UserSignUp.this, LogIn.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(UserSignUp.this, "Fail to register! try again.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(UserSignUp.this, "Fail to register! try again.", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }

    public void onCheckboxclick(View view) {
        btn.setEnabled(ctd.isChecked());
    }

}