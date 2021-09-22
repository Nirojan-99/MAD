package com.example.hairdo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class SalonSignUp extends AppCompatActivity {

    //    private FirebaseAuth auth;
    EditText salonname,email,address,contactNum,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon_sign_up);

//        auth = FirebaseAuth.getInstance();

        salonname = findViewById(R.id.SignUpSalonName);
        email = findViewById(R.id.SignUpSalonEmail);
        address = findViewById(R.id.SignUpSalonAddress);
        contactNum = findViewById(R.id.SignUpSalonContact);
        password = findViewById(R.id.SignUpSalonPassword);

    }
    public void OnSignUpClick(View view){
//        Intent intentotp = new Intent(this,OTP.class);
//        startActivity(intentotp);

        String enteredName = salonname.getText().toString().trim();
        String enteredEmail = email.getText().toString().trim();
        String enteredAddress = address.getText().toString().trim();
        String  enteredContact= contactNum.getText().toString().trim();
        String enteredPassword = password.getText().toString().trim();

        if(enteredName.isEmpty()){
            salonname.setError("Salon name is required");
            salonname.requestFocus();
            return;
        }else  if(enteredEmail.isEmpty()){
            email.setError("Salon name is required");
            email.requestFocus();
            return;
        }else  if(enteredAddress.isEmpty()){
            address.setError("Salon name is required");
            address.requestFocus();
            return;
        }else  if(enteredContact.isEmpty()){
            contactNum.setError("Salon name is required");
            contactNum.requestFocus();
            return;
        }else  if(enteredPassword.isEmpty()){
            password.setError("Salon name is required");
            password.requestFocus();
            return;
        }

    }
}