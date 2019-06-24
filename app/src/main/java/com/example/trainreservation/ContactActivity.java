package com.example.trainreservation;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class ContactActivity extends AppCompatActivity {
    TextView Person1Email,Person2Email,Person3Email,Person4Email,Person5Email,Person1Phone,Person2Phone,Person3Phone,Person4Phone,Person5Phone;
    String   Email1 , Email2 , Email3 ,Email4 ,Email5,Phone1,Phone2,Phone3,Phone4,Phone5;
    ViewFlipper viewFlipper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        viewFlipper = findViewById(R.id.viewSwitcher);
        viewFlipper.setFlipInterval(20000);
        viewFlipper.startFlipping();

        Person1Email = (TextView) findViewById(R.id.Person3Email);
        Person1Phone = (TextView) findViewById(R.id.Person3Phone);


        Person1Email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email1 = Person1Email.getText().toString();
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{Email1});
                email.putExtra(Intent.EXTRA_SUBJECT, "subject");
                email.putExtra(Intent.EXTRA_TEXT, "message");
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email client :"));

            }});

        Person1Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Phone1 = Person1Phone.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+Phone1));//change the number
                startActivity(callIntent);
            }});



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
