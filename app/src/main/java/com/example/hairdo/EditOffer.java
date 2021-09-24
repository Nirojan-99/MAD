package com.example.hairdo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;

public class EditOffer extends AppCompatActivity {

    EditText name ,des,date;
    TextView adddate;
    Button btn;
    String id;

    //calendar things
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_offer);

        name=findViewById(R.id.textView8);
        des=findViewById(R.id.textView10);
        date=findViewById(R.id.textView12);
        adddate=findViewById(R.id.pickdate);
        btn=findViewById(R.id.button3);

        Intent intent = getIntent();
        String intentName = intent.getStringExtra("name");
        String intentDes = intent.getStringExtra("description");
        String intentId = intent.getStringExtra("id");
        String intentDate = intent.getStringExtra("date");
        id=intent.getStringExtra("id");

        name.setText(intentName);
        des.setText(intentDes);
        date.setText(intentDate);

        adddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = java.util.Calendar.getInstance();
                year = calendar.get(java.util.Calendar.YEAR);
                month = calendar.get(java.util.Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(EditOffer.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        date.setText(day + "/" + (month + 1) + "/" + year);

                    }
                }, year, month, dayOfMonth);

                datePickerDialog.show();
            }
        });

        //update
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredTitle = name.getText().toString().trim();
                String enteredDes = des.getText().toString().trim();
                String enteredDate = date.getText().toString().trim();

                if (enteredTitle.isEmpty() ) {
                    name.setError("Require valid title");
                    name.requestFocus();
                    return;
                } else if (enteredDes.isEmpty()) {
                    des.setError("Require valid description");
                    des.requestFocus();
                    return;
                } else if (enteredDate.isEmpty() ) {
                    date.setError("Require valid date");
                    date.requestFocus();
                    return;
                }

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("name", enteredTitle.toString());
                hashMap.put("description", enteredDes.toString());
                hashMap.put("validDate", enteredDate.toString());

                FirebaseDatabase.getInstance().getReference("Offer").child(id).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Intent intent1 = new Intent(EditOffer.this,offer.class);
                        finish();
                        startActivity(intent1);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditOffer.this, "Unable to update!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}