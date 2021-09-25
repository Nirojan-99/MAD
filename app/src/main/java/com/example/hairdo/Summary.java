package com.example.hairdo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hairdo.Helper.SummaryEvent;
import com.example.hairdo.model.Appointment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Summary extends AppCompatActivity {

    TextView complete, cancel, pending, earning;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        complete = findViewById(R.id.completion);
        cancel = findViewById(R.id.cancelation);
        pending = findViewById(R.id.pendings);
        earning = findViewById(R.id.earnings);

        SummaryEvent summaryEvent = new SummaryEvent();
        summaryEvent.getAppointmentSummary(id, complete, cancel, pending);
        summaryEvent.getEarning(id,earning);
    }



}