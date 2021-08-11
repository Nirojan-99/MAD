package com.example.hairdo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class Reviews extends AppCompatActivity {

    String userNames[] = {"nirojan","kamal","karan","arivu","sayanthan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.reviewsRecycleLayout);
        ReviewsAdapter adapter1 = new ReviewsAdapter(userNames);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView1.setAdapter(adapter1);
    }
}