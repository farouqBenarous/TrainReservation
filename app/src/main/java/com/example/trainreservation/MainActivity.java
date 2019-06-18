package com.example.trainreservation;

import android.app.ActionBar;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView BottNav;
    FragmentManager transaction ;
    SettingsFragment settingsFragment = new SettingsFragment();
    MyTicketsBlankFragment myTicketsBlankFragment = new MyTicketsBlankFragment();
    BookTripFragment bookTripFragment = new BookTripFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottNav = (BottomNavigationView) findViewById(R.id.BottNav);
        Objects.requireNonNull(this.getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar);
        getSupportActionBar().setElevation(0);
        View view = getSupportActionBar().getCustomView();



        BottNav.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {

                            case R.id.booktrip:
                                transaction = getSupportFragmentManager();
                                transaction.beginTransaction().replace(R.id.framelayout,bookTripFragment).commit() ;

                                break;
                            case R.id.myticktes:
                                transaction = getSupportFragmentManager();
                                transaction.beginTransaction().replace(R.id.framelayout,myTicketsBlankFragment).commit() ;
                                break;
                            case R.id.settings:
                                transaction = getSupportFragmentManager();
                                transaction.beginTransaction().replace(R.id.framelayout,settingsFragment).commit() ;
                                break;

                        }
                        return false;
                    }
                });


    }


    public  void profile(View view) {
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(intent);

    }

}
