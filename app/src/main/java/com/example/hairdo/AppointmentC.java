package com.example.hairdo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import java.util.Calendar;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hairdo.model.Appointment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

public class AppointmentC extends AppCompatActivity {
    Button selectDate;
    TextView date ;
    TextView Advancepayment;
    RadioButton timeradioButton;
    RadioGroup radioGroup;

    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        selectDate = findViewById(R.id.btnDate);
        date = findViewById(R.id.tvSelectedDate);
        Advancepayment = findViewById(R.id.Advancepayment);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);


    }


    public void onClickCompletePayment(View v){
        int selectedId = radioGroup.getCheckedRadioButtonId();
        timeradioButton = (RadioButton) findViewById(selectedId);
//        if(selectedId<=0){//Grp is your radio group object
//            timeradioButton.setError("Select time");//Set error to  Radio button
//        }
//        if(selectedId==-1){
//            Toast.makeText(AppointmentC.this,"please select the time", Toast.LENGTH_SHORT).show();
//        }

         String enterdate = date.getText().toString().trim();
         String entertime = timeradioButton.getText().toString().trim();
         String enterAdvancepayment = Advancepayment.getText().toString().trim();


        if(enterdate.isEmpty()){
            date.setError("select the date");
            date.requestFocus();
            return;

        }




       Appointment Appointment = new com.example.hairdo.model.Appointment("Nirojan","HairWizard",enterdate,entertime,"350");

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

//        Intent intent = new Intent(this,Payment.class);
//        startActivity(intent);
    }
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

}
