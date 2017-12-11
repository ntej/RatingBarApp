package com.example.navatejareddy.ratingbarapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.RecyclerViewAdapter;
import model.RatingAndReview;

public class MainActivity extends AppCompatActivity {

    AppCompatRatingBar ratingBar;
    EditText reviewEditText;
    Button submitButton;
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutmanager;
    List<RatingAndReview> ratingAndReviewList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeWidgets();
        setUpRecyclerView();
        attachListeners();
    }

    public void initializeWidgets() {
        ratingBar = findViewById(R.id.simpleRatingBar);
        reviewEditText = findViewById(R.id.review_edit_text);
        submitButton = findViewById(R.id.submit_button);
        recyclerView = findViewById(R.id.recycler_view);
    }

    public void setUpRecyclerView() {
        mLayoutmanager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutmanager);
        mAdapter = new RecyclerViewAdapter(ratingAndReviewList);
        recyclerView.setAdapter(mAdapter);
    }

    public void attachListeners() {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateRecyclerView();
            }
        });
    }

    public void updateRecyclerView() {
        RatingAndReview ratingAndReview = new RatingAndReview();
        float givenRating = ratingBar.getRating();
        if (givenRating == 0.0f) {
            Toast.makeText(this, "At least Rating is needed", Toast.LENGTH_SHORT).show();
            ratingAndReview = null;
            return;
        } else {
            ratingAndReview.setRating(ratingBar.getRating());
        }

        String review = reviewEditText.getText().toString();
        if (!review.isEmpty()) {
            ratingAndReview.setReview(review);
        } else {
            ratingAndReview.setReview("review is not provided");
        }
        ratingAndReviewList.add(ratingAndReview);
        mAdapter.notifyDataSetChanged();

        ratingBar.setRating(0.0f);
        reviewEditText.setText("");
    }
}