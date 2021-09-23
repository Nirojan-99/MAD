package com.example.hairdo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class EditOffer extends AppCompatActivity {

    EditText name ,des,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_offer);

        name=findViewById(R.id.textView8);
        des=findViewById(R.id.textView10);
        date=findViewById(R.id.textView12);

        Intent intent = getIntent();
        String intentName = intent.getStringExtra("name");
        String intentDes = intent.getStringExtra("description");
        String intentId = intent.getStringExtra("id");
        String intentDate = intent.getStringExtra("date");

        name.setText(intentName);
        des.setText(intentDes);
        date.setText(intentDate);
    }
}