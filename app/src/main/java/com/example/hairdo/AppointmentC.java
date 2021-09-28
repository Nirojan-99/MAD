package com.example.hairdo;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import java.text.ParseException;
import java.util.Calendar;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.hairdo.Helper.DateCompare;
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
    EditText date;
    TextView Advancepayment;
    EditText taketime;
    Button timebtn;
    FirebaseAuth auth;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    int mHour, mMinute;
    String Sname;
    String advancepayment;
    String sid;
    String cid;
    String cname;
    boolean isAppointmentValuable = true;
    String enterdate;
    String entertime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        selectDate = findViewById(R.id.btnDate);
        date = findViewById(R.id.tvSelectedDate);
        Advancepayment = findViewById(R.id.Advancepayment);
        taketime = findViewById(R.id.in_time);
        timebtn = findViewById(R.id.btn_time);

        cid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Intent intent = getIntent();
        sid = intent.getStringExtra("id");
        cname = intent.getStringExtra("cusName");


        // get Salon name and advance
        Query query8 = FirebaseDatabase.getInstance().getReference("Salon").child(sid);
        query8.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    Salon sal = snapshot.getValue(Salon.class);
                    Sname = sal.name;
                    advancepayment = sal.advance;
                    if (advancepayment == null) {
                        Advancepayment.setText("Unavilable");
                    } else {
                        Advancepayment.setText(advancepayment);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(AppointmentC.this, "no available Sname", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClickCompletePayment(View v) {
        enterdate = date.getText().toString().trim();
        entertime = taketime.getText().toString().trim();

        // validasion
        if (enterdate.isEmpty()) {
            date.setError("select the date");
            date.requestFocus();
            return;

        } else if (entertime.isEmpty()) {
            taketime.setError("select the time");
            taketime.requestFocus();
            return;

        }


        // check time and date
        FirebaseDatabase.getInstance().getReference("Appointment").orderByChild("id").equalTo(sid).addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Appointment ser = dataSnapshot.getValue(Appointment.class);
                        Boolean result = false;
                        try {
                            result = DateCompare.comparedates(enterdate, ser.date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (result) {
                            boolean timeResult = DateCompare.compareTime(ser.time, entertime);
                            if (timeResult) {
                                Toast.makeText(AppointmentC.this, "Time is alredy Taken", Toast.LENGTH_SHORT).show();
                                isAppointmentValuable = false;
                                return;
                            }
                        } else {

                        }
                    }
                    addAppointment();

                } else {
                    addAppointment();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }

    // date picker
    public void PickDate(View v) {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        // Toast.makeText(this, year+"/"+(month+1)+"/"+dayOfMonth, Toast.LENGTH_SHORT).show();
        datePickerDialog = new DatePickerDialog(AppointmentC.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                date.setText(day + "/" + (month + 1) + "/" + year);

            }
        }, year, month, dayOfMonth);
        datePickerDialog.show();

    }

    // Time Picker
    public void PickTime(View v) {
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

                        taketime.setText(hourOfDay + ":" + minute + format);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    public void addAppointment() {
        //Add appointment
        Appointment appointment = new Appointment(sid, cid, cname, Sname, enterdate, entertime, advancepayment, "waiting");

        FirebaseDatabase.getInstance().getReference("Appointment").push().setValue(appointment).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(AppointmentC.this, "Appointment is Added", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(AppointmentC.this, Payment.class);
////                intent.putExtra("id", sid);
//                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AppointmentC.this, "Appointment is not Added", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

