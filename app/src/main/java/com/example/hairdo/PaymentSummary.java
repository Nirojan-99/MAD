package com.example.hairdo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class PaymentSummary extends AppCompatActivity {
    ArrayList<String> date = new ArrayList<>();
    ArrayList<Integer> amount = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_summary);

        date.add("2020-12-10");
        date.add("2020-2-10");
        date.add("2020-1-11");
        date.add("2020-3-3");
        date.add("2020-3-3");
        date.add("2020-3-3");

        amount.add(1200);
        amount.add(200);
        amount.add(1500);
        amount.add(1100);
        amount.add(1100);
        amount.add(1100);

        RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.paymentSummaryRecycle);
        PaymentSummaryAdapter adapter1 = new PaymentSummaryAdapter(date,amount);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView1.setAdapter(adapter1);
    }
}