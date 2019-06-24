package com.example.trainreservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class FirstPageActivity extends AppCompatActivity {

    ViewFlipper viewFlipper;
    ProgressBar progressBar;
    ImageView imageView1,imageView2,imageView3;
    FirebaseAuth auth ;
    FirebaseUser  user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);


        viewFlipper = findViewById(R.id.viewSwitcher);
        progressBar = (ProgressBar ) findViewById(R.id.progressbar);
        imageView1 = (ImageView) findViewById(R.id.ImageFeature1) ;
        imageView2 = (ImageView) findViewById(R.id.ImageFeature2) ;
        imageView3 = (ImageView) findViewById(R.id.ImageFeature3) ;
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();




        viewFlipper.setFlipInterval(20000);
        viewFlipper.startFlipping();

        Picasso.with(getApplicationContext())
                .load("https://firebasestorage.googleapis.com/v0/b/trainreservation-bead7.appspot.com/o/chris-yang-746390-unsplash.jpg?alt=media&token=6daeaa8a-a968-45a3-a57e-ef6f49c744fd")
                .into(imageView1);


        Picasso.with(getApplicationContext())
                .load("https://firebasestorage.googleapis.com/v0/b/trainreservation-bead7.appspot.com/o/mateo-broquedis-536709-unsplash.jpg?alt=media&token=55539107-8916-41db-8b08-16c124cdc24f")
                .into(imageView2);

        Picasso.with(getApplicationContext())
                .load("https://firebasestorage.googleapis.com/v0/b/trainreservation-bead7.appspot.com/o/jp-valery-1164847-unsplash.jpg?alt=media&token=0709ad4a-b8a7-468b-8424-0241f08dac54")
                .into(imageView3);



    if (user == null) {
        Intent intent = new Intent (getApplicationContext() ,  MainActivity.class) ;
        startActivity(intent);

    }
    }


    public void Login (View view) {
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);

    }
    public void Signup (View view) {
        Intent intent = new Intent(getApplicationContext(),SignupActivity.class);
        startActivity(intent);

    }

    public void Next (View view) {
        viewFlipper.showNext();
        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
        viewFlipper.showNext();


    }
    public void Privous (View view) {
        viewFlipper.showPrevious();
        viewFlipper.setInAnimation(this, R.anim.slide_in_right);
        viewFlipper.setOutAnimation(this, R.anim.slide_out_left);
        viewFlipper.showPrevious();

    }
}
