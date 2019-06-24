package com.example.trainreservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    FirebaseApp firebaseApp;
    FirebaseAuth firebaseAuth ;
    FirebaseUser firebaseUser ;
    FirebaseDatabase firebaseDatabase ;
    DatabaseReference databaseReference ;
    ImageView imageheader ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        imageheader = (ImageView) findViewById(R.id.header_cover_image) ;
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users") ;
        firebaseUser = firebaseAuth.getCurrentUser();




        Picasso.with(getApplicationContext())
                .load("https://www.industryweek.com/sites/industryweek.com/files/styles/article_featured_retina/public/uploads/2017/05/teamwork.jpg?itok=U5ZihnwP")
                .into(imageheader);
    }



    public void Logout(View view) {
        firebaseAuth.signOut();
        Toast.makeText(getApplicationContext(),"Logout successfully ",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext() , LoginActivity.class);
        startActivity(intent);
    }


    public void Editprofile(View view) {
        Intent intent = new Intent(getApplicationContext(),EditprofileActivity.class);
        startActivity(intent);
    }
}
