package com.example.hairdo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hairdo.model.PaymentModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment extends AppCompatActivity {

    EditText number, name, month, year, cvc;
    Button btn;

    String salonId ;
    int amount ;
    String id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        salonId = getIntent().getStringExtra("id");
        amount = Integer.parseInt(getIntent().getStringExtra("advance"));

        //initialize
        number = findViewById(R.id.cardNumber);
        name = findViewById(R.id.holdername);
        month = findViewById(R.id.month);
        year = findViewById(R.id.year);
        cvc = findViewById(R.id.cvc);
        btn = findViewById(R.id.submit);

        //button click
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String enteredNumber = number.getText().toString().trim();
                String enteredName = name.getText().toString().trim();
                String enteredYear = year.getText().toString().trim();
                String enteredMonth = month.getText().toString().trim();
                String enteredCvc = cvc.getText().toString().trim();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                String currentDate = formatter.format(date).toString();

                //validation
                if (enteredNumber.length() != 16) {
                    number.setError("Require valid card number");
                    number.requestFocus();
                    return;
                } else if (enteredName.isEmpty()) {
                    name.setError("Require valid card holder name");
                    name.requestFocus();
                    return;
                } else if (enteredYear.length() != 4) {
                    year.setError("Require valid year");
                    year.requestFocus();
                    return;
                } else if (enteredMonth.length() > 2 ) {
                    month.setError("Require valid month");
                    month.requestFocus();
                    return;
                } else if (enteredCvc.length() != 3) {
                    cvc.setError("Require valid cvc number");
                    cvc.requestFocus();
                    return;
                }

                PaymentModel paymentModel = new PaymentModel(enteredName,enteredNumber,enteredCvc,enteredMonth,enteredYear,currentDate,salonId,amount,id);

                //add to database
                FirebaseDatabase.getInstance().getReference("Payment").push().setValue(paymentModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Payment.this, "payment completed", Toast.LENGTH_SHORT).show();
                        finish();
                        Intent intent = new Intent(Payment.this,UserProfile.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Payment.this, "payment is not completed", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}