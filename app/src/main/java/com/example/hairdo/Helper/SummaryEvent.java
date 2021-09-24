package com.example.hairdo.Helper;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.hairdo.Payment;
import com.example.hairdo.Summary;
import com.example.hairdo.UpcomingAppointments;
import com.example.hairdo.model.Appointment;
import com.example.hairdo.model.PaymentModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SummaryEvent {

    public  int Complete;
    public int cancel;
    public int Pending;
    public int earning;

    public SummaryEvent(){
        this.Complete = 0;
        this.cancel = 0;
        this.Pending = 0;
        this.earning = 0;
    }

    public void getAppointmentSummary(String id, TextView txt1, TextView txt2, TextView txt3) {

        Query query = FirebaseDatabase.getInstance().getReference("Appointment").orderByChild("id").equalTo(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Appointment ser = dataSnapshot.getValue(Appointment.class);
                        ser.set_id(dataSnapshot.getKey());

                        if(ser.status != null){
                            if (ser.status.equals("cancel")) {
                                cancel++;
                            }
                            else if (ser.status.contains("complete")) {
                                Complete++;
                            }
                            else {
                                Pending++;
                            }

                        }else {
                            Pending++;
                        }

                    }
                    txt1.setText(Complete+"");
                    txt2.setText(cancel+"");
                    txt3.setText(Pending+"");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    //earning
    public  void getEarning(String id,TextView txt){
        Query query = FirebaseDatabase.getInstance().getReference("Payment").orderByChild("salonId").equalTo(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        PaymentModel ser = dataSnapshot.getValue(PaymentModel.class);
                        earning += ser.amount;
                    }
                    txt.setText(earning+"/-");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
