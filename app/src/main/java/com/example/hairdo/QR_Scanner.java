package com.example.hairdo;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hairdo.model.Appointment;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.HashMap;

public class QR_Scanner extends AppCompatActivity {

    Button btScan;
    String SaApId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scanner);
        btScan = findViewById(R.id.bt_scan);

        Intent intent = getIntent();
        SaApId = intent.getStringExtra("SaApId");

        btScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(QR_Scanner.this);

                intentIntegrator.setPrompt("For Flash use Volume up Key");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setCaptureActivity(QR_Scan_Capture.class);
                intentIntegrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult intentResult = IntentIntegrator.parseActivityResult(
                requestCode, resultCode, data);

        if (intentResult.getContents() != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    QR_Scanner.this
            );


            String appointmentID = intentResult.getContents();
            builder.setTitle("Result");

            if (appointmentID.equals(SaApId)) {
                builder.setMessage("Appointment Conformed and Completed ");
                updateStatus(appointmentID);
            } else {
                builder.setMessage("Un Conformed Appointment");
                Toast.makeText(getApplicationContext(), "Un Conformed Appointment", Toast.LENGTH_SHORT).show();

            }
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                    Intent intent=new Intent(QR_Scanner.this,SalonProfile.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            });

            builder.show();

        } else {
            Toast.makeText(getApplicationContext(), "You did not Scan", Toast.LENGTH_SHORT).show();
        }

    }

    private void updateStatus(String appointmentID) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Appointment qrA = new Appointment();
        qrA.setStatus("completed");
        hashMap.put("status", qrA.getStatus());
        FirebaseDatabase.getInstance().getReference("Appointment").child(appointmentID).updateChildren(hashMap).addOnSuccessListener(suc -> {
            Toast.makeText(this, "Appointment Conformed and Completed ", Toast.LENGTH_SHORT).show();

        }).addOnFailureListener(er -> {
            Toast.makeText(this, "Not Completed. Error is: " + er.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }
}