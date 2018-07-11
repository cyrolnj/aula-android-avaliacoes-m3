package com.solutions.oryc.foodrater.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.solutions.oryc.foodrater.R;
import com.solutions.oryc.foodrater.domain.Review;
import com.solutions.oryc.foodrater.holders.ReviewHolder;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter {

    private ReviewHolder reviewHolder;
    private ArrayList<Review> reviewList;

    public ReviewAdapter(ArrayList<Review> reviewList){
        this.reviewList = reviewList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        reviewHolder = new ReviewHolder(inflatedView);
        return reviewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        reviewHolder = (ReviewHolder) holder;
        Review currentReview = this.reviewList.get(position);
        reviewHolder.displayReview( currentReview );
    }

    @Override
    public int getItemCount() {
        return this.reviewList.size();
    }
}
