package com.example.trainreservation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.angmarch.views.NiceSpinner;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditprofileActivity extends AppCompatActivity {
    NiceSpinner niceSpinner;
    List<String> dataset;
    EditText Email,FullName,UserName,Phonenumber,OldPassword,NewPassword,ConfirmPassword;
    String TextEmail,TextFullName,TextUserName,TextPhonenumber,TextOldPassword,TextNewPassword,TextConfirmPassword,RightPassword;
    ProgressBar progressBar;
    ModelUser user , user1;

    FirebaseAuth firebaseAuth ;
    FirebaseUser firebaseUser ;
    FirebaseDatabase firebaseDatabase ;
    DatabaseReference ref1 , ref2 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);


        Email = (EditText) findViewById(R.id.Email);
        FullName = (EditText) findViewById(R.id.FullName);
        UserName = (EditText) findViewById(R.id.UserName);
        Phonenumber = (EditText) findViewById(R.id.PhoneNumber);

        OldPassword = (EditText) findViewById(R.id.Password);
        NewPassword = (EditText) findViewById(R.id.NewPassword);
        ConfirmPassword = (EditText) findViewById(R.id.ConfirmPassword);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        ref1 = firebaseDatabase.getReference() ;
        ref2 = firebaseDatabase.getReference() ;
        firebaseUser = firebaseAuth.getCurrentUser();

        ref1.child("Users").orderByChild("id").equalTo(firebaseUser.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                user1 = dataSnapshot.getValue(ModelUser.class);
                RightPassword = user1.getPassword() ;

            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {}
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}});

    }
    public void Save(View view) throws JSONException {
        TextEmail = String.valueOf(Email.getText());
        TextFullName = String.valueOf(FullName.getText());
        TextUserName = String.valueOf(UserName.getText());
        TextPhonenumber = String.valueOf(Phonenumber.getText());

        TextOldPassword = String.valueOf(OldPassword.getText());
        TextNewPassword = String.valueOf(NewPassword.getText());
        TextConfirmPassword = String.valueOf(ConfirmPassword.getText());


        if (TextEmail.isEmpty() || TextEmail == null ) {
            Email.setError("You have To set the Email ! ");
            return;
        }

        if (!isValidEmail(TextEmail) ) {

            Email.setError("You have To set a Correct Email !");
            return;
        }

        if (TextFullName.isEmpty() || TextFullName == null) {
            FullName.setError("You have To set the FullName ! ");
            return;
        }

        if (! isValidName(TextFullName)) {
            FullName.setError("You have To set a Correct  FullName ! ");
            return;
        }



        if (TextUserName.isEmpty() || TextUserName == null) {
            UserName.setError("You have To set the UserName ! ");
            return;
        }

        if (TextPhonenumber.isEmpty() || TextPhonenumber == null) {
            Phonenumber.setError("You have To set the Phonenumber ! ");
            return;
        }

        if (TextOldPassword.isEmpty() || TextOldPassword == null) {
            OldPassword.setError("You have To set the Password ! ");
            return;
        }

        if (! TextOldPassword.equals(RightPassword)) {
            OldPassword.setError("The Password does't match with the old one  ");
            return ;
        }




        if (TextNewPassword.isEmpty() || TextNewPassword == null) {
            OldPassword.setError("You have To set the Password ! ");
            return;
        }
        if (TextConfirmPassword.isEmpty() || TextConfirmPassword == null) {
            ConfirmPassword.setError("You have To set the Confirm Password ! ");
            return;
        }

        if (! TextConfirmPassword.equals(TextNewPassword)) {
            NewPassword.setError("The Password does't match ");
            ConfirmPassword.setError("The Password does't match ");
            return ;
        }


        ref2.child("Users").child(firebaseUser.getUid()).child("email").setValue(TextEmail);
        ref2.child("Users").child(firebaseUser.getUid()).child("fullname").setValue(TextFullName);
        ref2.child("Users").child(firebaseUser.getUid()).child("phoneNumber").setValue(TextPhonenumber);
        ref2.child("Users").child(firebaseUser.getUid()).child("password").setValue(TextConfirmPassword);
        ref2.child("Users").child(firebaseUser.getUid()).child("username").setValue(TextUserName);

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(TextFullName)
                .build();

        firebaseUser.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) { } }
                });

        firebaseUser.updateEmail(TextEmail)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) { } }
                });

        firebaseUser.updatePassword(TextNewPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) { }}
                });

        Toast.makeText(getApplicationContext(),"Successfully Saved the Changes " , Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
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
