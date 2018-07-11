package com.solutions.oryc.foodrater;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.solutions.oryc.foodrater.adapters.ReviewAdapter;
import com.solutions.oryc.foodrater.domain.Review;
import com.solutions.oryc.foodrater.model.ReviewDAO;

import java.util.ArrayList;
import java.util.Date;

public class DesktopActivity extends AppCompatActivity {

    private ArrayList<Review> reviewList = new ArrayList<Review>();
    private RecyclerView rvReviewList;
    private ReviewAdapter reviewAdapter;
    private Button btnViewMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desktop);

        btnViewMap = findViewById(R.id.btnViewMap);
        Review review;

        /*
        for(int i=1; i<10; i++){
            review = new Review();
            review.setDescription("Descrição" + i );
            review.setRate(i);
            review.setDate(new Date());
            reviewList.add(review);
        }*/

        reviewList = ReviewDAO.getReviewList();

        if(reviewList.isEmpty()){
           btnViewMap.setVisibility(View.GONE);
        } else {
            btnViewMap.setVisibility(View.VISIBLE);
        }

        reviewAdapter = new ReviewAdapter(reviewList);

        rvReviewList = findViewById(R.id.rvReviewList);
        rvReviewList.setAdapter(reviewAdapter);
        rvReviewList.setLayoutManager(new LinearLayoutManager(this));

        /*
        reviewList.clear();
        for (Review review : reviewList) {
            reviewList.add(review);
        }*/


    }

    public void onClickCreateReview(View view) {
        Intent createReviewIntent = new Intent(this, CreateReviewActivity.class);
        startActivityForResult(createReviewIntent, 200);
    }

    public void onClickViewMap(View view) {
        Intent createReviewIntent = new Intent(this, MapsActivity.class);
        startActivity(createReviewIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        reviewList = ReviewDAO.getReviewList();
        reviewAdapter.notifyDataSetChanged();

        if (!reviewList.isEmpty()) {
            btnViewMap.setVisibility(View.VISIBLE);
        }
    }

}
