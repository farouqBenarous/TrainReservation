package com.example.trainreservation;

import android.content.Intent;
import android.provider.Contacts;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {
    NiceSpinner niceSpinner,dayspin,monthspin,yearspin;
    List<String> datasetgender , datasetday , datasetmonth , datasetyear  ;
    EditText Email,FullName,UserName,Phonenumber,Password,ConfirmPassword;
    String TextEmail,TextFullName,TextUserName,TextPhonenumber,TextPassword,TextConfirmPassword;
    String birthdate , UID ;
    ProgressBar progressBar;
    int spin,day,month,year;
    private FirebaseAuth mAuth;
    FirebaseUser currentUser ;
    FirebaseDatabase database ;
    DatabaseReference myRef ;
    FirebaseApp firebaseApp;
    ModelUser modelUser ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);



        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users") ;


        Email = (EditText) findViewById(R.id.Email);
        FullName = (EditText) findViewById(R.id.FullName);
        UserName = (EditText) findViewById(R.id.UserName);
        Phonenumber = (EditText) findViewById(R.id.PhoneNumber);
        Password = (EditText) findViewById(R.id.Password);
        ConfirmPassword = (EditText) findViewById(R.id.ConfirmPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        niceSpinner = (NiceSpinner) findViewById(R.id.nice_spinner);
        dayspin = (NiceSpinner) findViewById(R.id.day);
        monthspin = (NiceSpinner) findViewById(R.id.month);
        yearspin = (NiceSpinner) findViewById(R.id.year);

        datasetgender = new LinkedList<>(Arrays.asList("Male", "Female"));



        niceSpinner.attachDataSource(datasetgender);
        niceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spin = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        datasetday  = new LinkedList<>(Arrays.asList("1"));
        for (int i =1 ; i <= 31 ;i++) { datasetday.add(String.valueOf(i));}
        dayspin.attachDataSource(datasetday);
        dayspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                day = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        datasetmonth = new LinkedList<>(Arrays.asList("1"));
        for (int i =1 ; i <= 11 ;i++) { datasetmonth.add(String.valueOf(i));}
        monthspin.attachDataSource(datasetmonth);
        monthspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                month = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        datasetyear = new LinkedList<>(Arrays.asList("1960"));
        for (int i =1961 ; i <= 2019 ;i++) { datasetyear.add(String.valueOf(i)); }
        yearspin.attachDataSource(datasetyear);
        yearspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                year = position+1960;
            }




            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    public void  Signup(View view) {


        currentUser = mAuth.getCurrentUser();
        TextEmail = String.valueOf(Email.getText());
        TextFullName = String.valueOf(FullName.getText());
        TextUserName = String.valueOf(UserName.getText());
        TextPhonenumber = String.valueOf(Phonenumber.getText());
        TextPassword = String.valueOf(Password.getText());
        TextConfirmPassword = String.valueOf(ConfirmPassword.getText());
        progressBar.setVisibility(View.VISIBLE);
        birthdate = day+"/"+month+"/"+year ;

        if (TextEmail.isEmpty() || TextEmail == null ) {
            Email.setError("You have To set the Email ! ");
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }

        if (!isValidEmail(TextEmail) ) {

            Email.setError("You have To set a Correct Email !");
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }

        if (TextFullName.isEmpty() || TextFullName == null) {
            FullName.setError("You have To set the FullName ! ");
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }

        if (! isValidName(TextFullName)) {
            FullName.setError("You have To set a Correct  FullName ! ");
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }


        if (TextUserName.isEmpty() || TextUserName == null) {
            UserName.setError("You have To set the UserName ! ");
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }

        if (TextPhonenumber.isEmpty() || TextPhonenumber == null) {
            Phonenumber.setError("You have To set the Phonenumber ! ");
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }

       /* if (PhoneNumberUtils.isGlobalPhoneNumber(TextPhonenumber)) {
            Phonenumber.setError("Phone number is not Valid ");
            progressBar.setVisibility(View.INVISIBLE);
            return;
        } */


        if (TextPassword.isEmpty() || TextPassword == null) {
            Password.setError("You have To set the Confirm Password ! ");
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }

        if (TextConfirmPassword.isEmpty() || TextConfirmPassword == null) {
            ConfirmPassword.setError("You have To set the Confirm Password ! ");
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }

        if (!TextConfirmPassword.equals(TextPassword)) {
            Password.setError("The Password does't match ");
            progressBar.setVisibility(View.INVISIBLE);
            return ;
        }


        mAuth.createUserWithEmailAndPassword(TextEmail, TextPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Authentication Succeed.", Toast.LENGTH_SHORT).show() ;

                            FirebaseUser user = mAuth.getCurrentUser();
                            UID = user.getUid();
                            modelUser = new ModelUser(UID,datasetgender.get(spin) , TextFullName , TextUserName , TextEmail , TextPhonenumber , birthdate , TextPassword ) ;

                            myRef.child(UID).setValue(modelUser) ;

                            Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                            startActivity(intent);


                        } else {

                            Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), String.valueOf(task.getException()) , Toast.LENGTH_SHORT).show();


                        }
                    }
                });

    }


    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static boolean isValidName (String Name) {
        Pattern pattern = Pattern.compile(new String ("^[a-zA-Z\\s]*$"));
        Matcher matcher = pattern.matcher(Name);
        if(!matcher.matches())
        {
            return  false;
        }
        return true ;

    }


}
