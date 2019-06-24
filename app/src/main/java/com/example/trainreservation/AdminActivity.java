package com.example.trainreservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class AdminActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        firebaseAuth = FirebaseAuth.getInstance() ;
    }

public void AddTrip(View view) {
    Intent intent = new Intent(getApplicationContext() , AddtripActivity.class);
    startActivity(intent);

}


    public void Logout(View view) {
        firebaseAuth.signOut();
        Toast.makeText(getApplicationContext(),"Logout successfully ",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext() , LoginActivity.class);
        startActivity(intent);
    }
}

