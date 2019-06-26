package com.example.trainreservation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {

    FirebaseApp firebaseApp;
    FirebaseAuth firebaseAuth ;
    FirebaseUser firebaseUser ;
    FirebaseDatabase firebaseDatabase ;
    DatabaseReference databaseReference ;
    ImageView imageheader ;
    ModelUser modelUser ;
    TextView Email , PhoneNumber , gender , Birthdate , Name , username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        Email = (TextView) findViewById(R.id.email);
        PhoneNumber = (TextView) findViewById(R.id.PhoneNumber);
        gender = (TextView) findViewById(R.id.gender);
        Birthdate = (TextView) findViewById(R.id.Birthdate);
        Name = (TextView) findViewById(R.id.Name);
        username = (TextView) findViewById(R.id.username);


        imageheader = (ImageView) findViewById(R.id.header_cover_image) ;
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users") ;
        firebaseUser = firebaseAuth.getCurrentUser();

        databaseReference.orderByChild("id").equalTo(firebaseUser.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                modelUser = dataSnapshot.getValue(ModelUser.class);
                Email.setText(modelUser.getEmail());
                PhoneNumber.setText(modelUser.getPhoneNumber());
                gender.setText(modelUser.getGender());
                Birthdate.setText(modelUser.getBirthdate());
                Name.setText(modelUser.getFullname());
                username.setText(modelUser.getUsername());
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }});




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
