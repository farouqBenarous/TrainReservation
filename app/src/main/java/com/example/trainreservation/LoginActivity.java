package com.example.trainreservation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
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

import org.json.JSONException;

public class LoginActivity extends AppCompatActivity {

    EditText email , password;
    String EmailText , PasswordText,RightPassword;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    FirebaseUser currentUser ;
    FirebaseDatabase database ;
    DatabaseReference myRef ;
    ModelUser modelUser ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar)  findViewById(R.id.progressbar) ;


        FirebaseApp.initializeApp(getApplicationContext());
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users") ;

    }



    public void Login (View view)   {
        EmailText = String.valueOf(email.getText());
        PasswordText = String.valueOf(password.getText());

        progressBar.setVisibility(View.VISIBLE);


        if (EmailText.isEmpty() || EmailText == null) {
            email.setError("You have To set the Email ! ");
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }

        if (!isValidEmail(EmailText) ) {

           email.setError("You have To set a Correct Email !");
            progressBar.setVisibility(View.INVISIBLE);
             return;
        }
        if (PasswordText.isEmpty() || PasswordText == null) {
            password.setError("You Have To set the password !");
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }

        mAuth.signInWithEmailAndPassword(EmailText, PasswordText)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            if (EmailText.equals("admin@admin.com") && PasswordText.equals("adminadmin")) {
                                Intent intent = new Intent(getApplicationContext() , AdminActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                                startActivity(intent);
                            }

                        } else {
                            Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), String.valueOf(task.getException()), Toast.LENGTH_SHORT).show();

                        }
                    }
                });




    }


    public void gohome(View view) {

        Intent intent = new Intent(getApplicationContext() , MainActivity.class);
        startActivity(intent);


    }
    public void Gosignup(View view ) {
        Intent intent =new Intent(getApplicationContext() , SignupActivity.class);
        startActivity(intent);

    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

}
