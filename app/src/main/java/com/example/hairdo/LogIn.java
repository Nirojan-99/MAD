package com.example.hairdo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hairdo.DB.DBHelper;
import com.example.hairdo.model.Customer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LogIn extends AppCompatActivity {

    EditText email, password;
    Button btn;
    FirebaseAuth auth;
    DBHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        email = findViewById(R.id.LogInUsername);
        password = findViewById(R.id.LogInPassword);
        btn = findViewById(R.id.loginBtn);
        auth = FirebaseAuth.getInstance();
        dbh =  new DBHelper(this);




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredEmail = email.getText().toString().trim();
                String enteredPassword = password.getText().toString().trim();

                if (!enteredEmail.contains("@") || !enteredEmail.contains(".com")) {
                    email.setError("Valid email is required");
                    email.requestFocus();
                    return;
                } else if (enteredPassword.length() < 6) {
                    password.setError("Valid password is required");
                    password.requestFocus();
                    return;
                }


                auth.signInWithEmailAndPassword(enteredEmail, enteredPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                            boolean res = dbh.insertuserdata();
                           Intent intent = new Intent(LogIn.this,UserProfile.class);
                           intent.putExtra("userMail",enteredEmail);
                           startActivity(intent);
                           finish();

                        } else {
                            Toast.makeText(LogIn.this, "Failed to login! please check your credentials.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    public void onClickFogetPassword(View view) {

    }


    public void onClickSignUp(View view) {
        Intent intSign = new Intent(this, BothSignUp.class);
        startActivity(intSign);
        finish();
    }
    public void salonLogin(View view){
        Intent intSign = new Intent(this, SalonLogin.class);
        startActivity(intSign);
        finish();
    }

}