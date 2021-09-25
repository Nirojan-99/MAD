package com.example.hairdo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.Calendar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Freezable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.system.Int64Ref;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.DatePicker;

import com.example.hairdo.model.Holiday;
import com.example.hairdo.model.Offer;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Holidays extends AppCompatActivity {
    ProgressBar pg;
    ImageButton selectDate;
    Button addHolidayBtn;
    EditText selectedDate;
    EditText remark;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    String monthName;

    int Dyear;
    int Dmonth;
    int DdayOfMonth;

    RecyclerView recyclerView;
    ArrayList<Holiday> holidayList = new ArrayList<>();
    HolidaysRvAd holidaysRvAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holidays);
        addHolidayBtn = findViewById(R.id.btn_add_holiday);
        selectDate = findViewById(R.id.btnHoliDate);
        selectedDate = findViewById(R.id.picked_holiDate);
        remark = findViewById(R.id.Holidayremark);
        pg=findViewById(R.id.holidayPG);


        setDateforRecyclerview();
        setAdapter();

        addHolidayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String FDate = selectedDate.getText().toString().trim();
                String FRemark = remark.getText().toString().trim();
                String Fmonth = Integer.toString(Dmonth);
                String Fday = Integer.toString(DdayOfMonth);
                String Fyear = Integer.toString(Dyear);
                String FformatDate=Fday+"/"+Fmonth+"/"+Fyear;
                Holiday holiday = new Holiday(FDate, FRemark,FformatDate);


                FirebaseDatabase.getInstance().getReference("Holiday").push().setValue(holiday).addOnSuccessListener(suc -> {
                    Toast.makeText(Holidays.this, "Successfully added Holiday ", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }).addOnFailureListener(er -> {
                    Toast.makeText(Holidays.this, "Not Added. Error is: " + er.getMessage(), Toast.LENGTH_SHORT).show();
                });

            }
        });


    }

    private void setDateforRecyclerview() {

        FirebaseDatabase.getInstance().getReference(Holiday.class.getSimpleName()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {

                    Holiday h = data.getValue(Holiday.class);
                    h.setFbKey(data.getKey());
                    holidayList.add(h);


                }
                pg.setVisibility(View.GONE);
                holidaysRvAd.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }


    private void setAdapter() {
        recyclerView = findViewById(R.id.holidays_rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        holidaysRvAd = new HolidaysRvAd(this, holidayList);
        recyclerView.setAdapter(holidaysRvAd);


    }


    public void goToCalender(View view) {
        Intent intent = new Intent(this, Calendar.class);
        startActivity(intent);
    }

    public String getMonthName(int m) {
        String mName = null;
        switch (m) {
            case 1:
                mName = "January";
                break;
            case 2:
                mName = "February";
                break;
            case 3:
                mName = "March";
                break;
            case 4:
                mName = "April";
                break;
            case 5:
                mName = "May";
                break;
            case 6:
                mName = "June";
                break;
            case 7:
                mName = "July";
                break;
            case 8:
                mName = "August";
                break;
            case 9:
                mName = "September";
                break;
            case 10:
                mName = "October";
                break;
            case 11:
                mName = "November";
                break;
            case 12:
                mName = "December";
                break;
            default:
                mName = null;
        }
        return mName;
    }

    public void PickDate(View v) {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(Holidays.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                monthName = getMonthName(month + 1);
//                selectedDate.setText(day + " / " + (month + 1) + " / " + year);
                selectedDate.setText(monthName + " " + day + " , " + year);
                Dyear = year;
                Dmonth = month + 1;
                DdayOfMonth = day;

            }
        }, year, month, dayOfMonth);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
//        Toast.makeText(this, year+"/"+(month+1)+"/"+dayOfMonth, Toast.LENGTH_SHORT).show();
        datePickerDialog.show();
    }


}