package com.solutions.oryc.foodrater;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Toast;

import com.solutions.oryc.foodrater.domain.Review;
import com.solutions.oryc.foodrater.model.ReviewDAO;

import java.util.Date;

public class CreateReviewActivity extends AppCompatActivity {

    private EditText txtDescription;
    private RadioButton rbEntry;
    private RadioButton rbMainCourse;
    private RadioButton rbDessert;
    private RadioButton rbSnack;
    private RatingBar ratingBar;
    private Button btnTakePicture;
    private ImageView imgPicture;
    private int category;
    private Bitmap picture;
    private LocationListener listenerCoordinates;
    LocationManager locationManager;
    private Location location;

    @Override
    public void onPause() {
        super.onPause();
        locationManager.removeUpdates(listenerCoordinates);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_review);

        txtDescription = findViewById(R.id.txtDescription);

        rbEntry = findViewById(R.id.rbEntry);
        rbMainCourse = findViewById(R.id.rbMainCourse);
        rbDessert = findViewById(R.id.rbDessert);
        rbSnack = findViewById(R.id.rbSnack);
        ratingBar = findViewById(R.id.ratingBar);
        btnTakePicture = findViewById(R.id.btnTakePicture);
        imgPicture = findViewById(R.id.imgPicture);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            btnTakePicture.setEnabled(false);
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
            }, 0);
        } else {
            getCoordinates();
        }



    }

    public void onRadioButtonClicked(View view) {

        if (rbEntry.isChecked() == true) {
            category = Review.ENTRY_CATEG;
        } else if (rbMainCourse.isChecked() == true) {
            category = Review.MAIN_COURSE_CATEG;
        } else if (rbDessert.isChecked() == true) {
            category = Review.DESSERT_CATEG;
        } else if (rbSnack.isChecked() == true) {
            category = Review.SNACK_CATEG;
        }
    }

    public void onClickSave(View view) {

        if (location != null) {
            Review newReview = new Review();
            newReview.setDescription(txtDescription.getText().toString());
            newReview.setCategory(category);
            newReview.setRate(ratingBar.getRating());
            newReview.setPicture(picture);
            newReview.setDate(new Date());
            newReview.setLocation(location);

            ReviewDAO.addToList(newReview);
            finish();
        } else {
            Toast.makeText(getApplicationContext(),
                    "No coordinates found, try again",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                btnTakePicture.setEnabled(true);
            }
        }
    }

    public void onClickTakePicture(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, 200);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(200 == requestCode && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgPicture.setImageBitmap(bitmap);
            picture = bitmap;
        }
    }

    @SuppressLint("MissingPermission")
    public void getCoordinates() {

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if(listenerCoordinates == null){

            listenerCoordinates = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    setLocation(location);
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {}

                @Override
                public void onProviderEnabled(String s) {}

                @Override
                public void onProviderDisabled(String s) {}
            };

            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    0,
                    0,
                    listenerCoordinates
            );
        }else{
            //locationManager.removeUpdates( listenerCoordinates );
            //listenerCoordinates = null;
        }


    }

    public void setLocation (Location location){
        this.location = location;
    }
}
