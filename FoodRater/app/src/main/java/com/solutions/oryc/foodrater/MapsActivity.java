package com.solutions.oryc.foodrater;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.solutions.oryc.foodrater.domain.Review;
import com.solutions.oryc.foodrater.model.ReviewDAO;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<Review> reviewList = new ArrayList<Review>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        reviewList = ReviewDAO.getReviewList();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for (Review review: reviewList) {
            String color = "";

            switch (review.getCategory()) {
                case Review.ENTRY_CATEG:
                    color = "#FF0000";
                    break;
                case Review.MAIN_COURSE_CATEG:
                    color = "#FFFF00";
                    break;
                case Review.DESSERT_CATEG:
                    color = "#3ADF00";
                    break;
                case Review.SNACK_CATEG:
                    color = "#0101DF";
                    break;
            }


            LatLng marker = new LatLng(review.getLocation().getLatitude(), review.getLocation().getLongitude());
            mMap.addMarker(new MarkerOptions().position(marker).title(review.getDescription()).icon(getMarkerIcon(color)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
        }

    }

    public BitmapDescriptor getMarkerIcon(String color) {
        float[] hsv = new float[3];
        Color.colorToHSV(Color.parseColor(color), hsv);
        return BitmapDescriptorFactory.defaultMarker(hsv[0]);
    }
}
