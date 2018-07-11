package com.solutions.oryc.foodrater.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.solutions.oryc.foodrater.R;
import com.solutions.oryc.foodrater.domain.Review;

public class ReviewHolder extends RecyclerView.ViewHolder {

    private TextView vtxtCategory;
    private TextView vtxtDescription;
    private TextView vtxtDetail;
    private ImageView imgPicture;

    public ReviewHolder(View itemView) {
        super(itemView);

        vtxtCategory = itemView.findViewById(R.id.vtxtCategory);
        vtxtDescription = itemView.findViewById(R.id.vtxtDescription);
        vtxtDetail = itemView.findViewById(R.id.vtxtDetail);
        imgPicture = itemView.findViewById(R.id.imgPicture);

    }

    public void displayReview(Review currentReview){
        vtxtDescription.setText(currentReview.getDescription());
        vtxtDetail.setText(String.valueOf(currentReview.getDetail()));
        imgPicture.setImageBitmap(currentReview.getPicture());

        switch (currentReview.getCategory()) {
            case Review.ENTRY_CATEG:
                vtxtCategory.setText(Review.ENTRY);
                break;
            case Review.MAIN_COURSE_CATEG:
                vtxtCategory.setText(Review.MAIN_COURSE);
                break;
            case Review.DESSERT_CATEG:
                vtxtCategory.setText(Review.DESSERT);
                break;
            case Review.SNACK_CATEG:
                vtxtCategory.setText(Review.SNACK);
                break;
        }
    }
}
