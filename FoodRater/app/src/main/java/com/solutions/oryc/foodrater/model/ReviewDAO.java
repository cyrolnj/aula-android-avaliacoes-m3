package com.solutions.oryc.foodrater.model;

import com.solutions.oryc.foodrater.domain.Review;

import java.util.ArrayList;

public class ReviewDAO {

    public static final ArrayList<Review> reviewList = new ArrayList<Review>();

    public static ArrayList<Review> getReviewList() {
        return reviewList;
    }

    public static void addToList (Review newReview) {
        reviewList.add(newReview);
    }
}
