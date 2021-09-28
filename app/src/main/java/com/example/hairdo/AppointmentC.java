package com.example.hairdo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import java.util.Calendar;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.hairdo.model.Appointment;
import com.example.hairdo.model.Customer;
import com.example.hairdo.model.Holiday;
import com.example.hairdo.model.Review;
import com.example.hairdo.model.Salon;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class AppointmentC extends AppCompatActivity {
    // inistailze
    Button selectDate;
    EditText date ;
    TextView Advancepayment;
    EditText taketime;
    Button timebtn;
    FirebaseAuth auth;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    int mHour,mMinute;
    String Sname ;
    String advancepayment ;
//    Appointment data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        selectDate = findViewById(R.id.btnDate);
        date = findViewById(R.id.tvSelectedDate);
        Advancepayment = findViewById(R.id.Advancepayment);
        taketime = findViewById(R.id.in_time);
        timebtn = findViewById(R.id.btn_time);


    }
    public void onClickCompletePayment(View v){

          Intent intent = getIntent();
          String sid = intent.getStringExtra("id").toString();
          String cname = intent.getStringExtra("cusName").toString();

         String enterdate = date.getText().toString().trim();
         String entertime = taketime.getText().toString().trim();
         //String cid = "oJO7CwPSZjXFTJIungGeaQZQvo33";
         //String sid = "ejHLtEYSByaRAt0p7zp5yMaD9Na2";
       String cid = FirebaseAuth.getInstance().getCurrentUser().getUid();
       // validasion
        if(enterdate.isEmpty()){
            date.setError("select the date");
            date.requestFocus();
            return;

        } else if(entertime.isEmpty()){
            taketime.setError("select the time");
            taketime.requestFocus();
            return;

        }



//        // get Sname and advance
//        Query query8 = FirebaseDatabase.getInstance().getReference("Salon").orderByChild("id").equalTo(sid);
//        query8.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                        Salon sal = dataSnapshot.getValue(Salon.class);
//                        Appointment data = null;
//                        if(sal != null){
//                        //Toast.makeText(AppointmentC.this, sal.name, Toast.LENGTH_SHORT).show();
//                         data.setSname(sal.name);
//                         data.setAdvancepayment(sal.advance);
//                         Advancepayment.setText(data.advancepayment);
//                        }else {
//                            Toast.makeText(AppointmentC.this, "is null sname", Toast.LENGTH_SHORT).show();
//                        }
//                        //Toast.makeText(AppointmentC.this, Sname, Toast.LENGTH_SHORT).show();
//                        //Toast.makeText(AppointmentC.this, advancepayment, Toast.LENGTH_SHORT).show();
//
//                    }
//                }
//                else{
//
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//                Toast.makeText(AppointmentC.this, "no available Sname", Toast.LENGTH_SHORT).show();
//            }
//        });





        // check time and date
//        FirebaseDatabase.getInstance().getReference("Appointment").addValueEventListener(new ValueEventListener() {
        Query query = FirebaseDatabase.getInstance().getReference("Appointment").orderByChild("cid").equalTo(cid);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Appointment data1 = dataSnapshot.getValue(Appointment.class);
                        data1.set_id(dataSnapshot.getKey());
//                            if (data1.time != null) {
                        if (data1.date.equals(enterdate) || !data1.time.equals(entertime)) {
                            taketime.getText().clear();
                            Toast.makeText(AppointmentC.this, data1.date + "data1", Toast.LENGTH_SHORT).show();
                            Toast.makeText(AppointmentC.this, "this date and time already in the database", Toast.LENGTH_SHORT).show();



                        } else  if (!data1.date.equals(enterdate) || data1.time.equals(entertime)) {
                            date.getText().clear();
                            Toast.makeText(AppointmentC.this, data1.date + "data1", Toast.LENGTH_SHORT).show();
                            Toast.makeText(AppointmentC.this, "this date and time already in the database", Toast.LENGTH_SHORT).show();
                           

                        }
                        else {

                        }
                    }


                } else {

                    //Add appointment
                    Appointment Appointment = new Appointment(sid, cid, cname, Sname, enterdate, entertime, advancepayment, "completed");

                    FirebaseDatabase.getInstance().getReference("Appointment").push().setValue(Appointment).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(AppointmentC.this, "Appointment is Added", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(getIntent());
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AppointmentC.this, "Appointment is not Added", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }








            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(AppointmentC.this, "no appointments add available", Toast.LENGTH_SHORT).show();
            }
        });}





//         //Add appointment
//        Appointment Appointment = new Appointment(sid, cid, cname, Sname, enterdate, entertime, advancepayment, "completed");
//
//        FirebaseDatabase.getInstance().getReference("Appointment").push().setValue(Appointment).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                Toast.makeText(AppointmentC.this, "Appointment is Added", Toast.LENGTH_SHORT).show();
//                finish();
//                startActivity(getIntent());
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(AppointmentC.this, "Appointment is not Added", Toast.LENGTH_SHORT).show();
//            }
//        });


    // date picker
    public void PickDate(View v) {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
       // Toast.makeText(this, year+"/"+(month+1)+"/"+dayOfMonth, Toast.LENGTH_SHORT).show();
        datePickerDialog = new DatePickerDialog(AppointmentC.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public  void onDateSet(DatePicker datePicker, int year, int month, int day) {
                date.setText(day + "/" + (month + 1) + "/" + year);

            }
        }, year, month, dayOfMonth);

//        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());

        datePickerDialog.show();

    }
    // Time Picker
    public void PickTime(View v){
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // check AM and PM
                         String format = "";

                        if (hourOfDay == 0) {
                            hourOfDay += 12;
                            format = "AM";
                        } else if (hourOfDay == 12) {
                            format = "PM";
                        } else if (hourOfDay > 12) {
                            hourOfDay -= 12;
                            format = "PM";
                        } else {
                            format = "AM";
                        }

                        taketime.setText(hourOfDay + ":" + minute+format);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }


}

