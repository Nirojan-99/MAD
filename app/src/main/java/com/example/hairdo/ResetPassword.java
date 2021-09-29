package com.example.hairdo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hairdo.Helper.ValidEmail;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {
    FirebaseAuth auth;
    EditText enteredResetEmail;
    Button resetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        enteredResetEmail=findViewById(R.id.resetEmail);
        resetBtn=findViewById(R.id.resetBtn);

        auth=FirebaseAuth.getInstance();

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }

    private void resetPassword(){
        String email=enteredResetEmail.getText().toString().trim();
        Boolean isValid= ValidEmail.isValidEmail(email);

        if (!isValid){
            enteredResetEmail.setError("Email is required");
            enteredResetEmail.requestFocus();
            return;
        }
        
        auth.sendPasswordResetEmail(email).addOnSuccessListener(suc->{
            Toast.makeText(this, "Please Check your Email.", Toast.LENGTH_LONG).show();
            Intent intent=new Intent(this,LogIn.class);
            startActivity(intent);
            finish();
            
        }).addOnFailureListener(er->{
            Toast.makeText(this, "Please Try Again.Something went Wrong", Toast.LENGTH_LONG).show();


        });


    }
}