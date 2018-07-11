package com.solutions.oryc.foodrater.domain;

import android.graphics.Bitmap;
import android.location.Location;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Review {

    public static final int ENTRY_CATEG = 1;
    public static final int MAIN_COURSE_CATEG = 2;
    public static final int DESSERT_CATEG = 3;
    public static final int SNACK_CATEG = 4;

    public static final String ENTRY = "Entry";
    public static final String MAIN_COURSE = "Main course";
    public static final String DESSERT = "Dessert";
    public static final String SNACK = "Snack";

    private String description;
    private int category;
    private Bitmap picture;
    private float rate;
    private Date date;
    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDetail() {

        String stringDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

        String detail = String.format("Rated with " + this.rate + " stars in " + stringDate);
        return detail;
    }
}
