package com.example.trainreservation;

import android.net.Uri;

import java.util.ArrayList;

public class ModelUser {

    private String  id ;
    private String fullname;
    private  String username;
    private  String email ;
    private String Gender;
    private  String phoneNumber;
    private Uri imageuri;
    private  String password;
    private  String birthdate;


    public ModelUser(String id , String Gender , String fullname , String username , String email , String phoneNumber , Uri imageuri) {
        this.id = id;
        this.fullname = fullname;
        this.Gender = Gender;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.imageuri = imageuri;
    }

     // Signup constructor without uri
    public ModelUser(String id , String Gender , String fullname , String username , String email , String phoneNumber , String birthdate , String password) {
        this.id = id;
        this.fullname = fullname;
        this.Gender = Gender;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthdate = birthdate ;
        this.password = password;
    }
    // Signup constructor without uri
    public ModelUser(String Gender , String fullname , String username , String email , String phoneNumber , String birthdate , String password) {
        this.Gender = Gender;
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthdate = birthdate ;
        this.password = password;
    }


    // Login Constructor
    public ModelUser(String username , String password ) {
        this.username = username;
        this.password = password;

    }

    public ModelUser(String id , String fullname , String username , String email , String phoneNumber) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }






    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public Uri getImageuri() {
        return imageuri;
    }

    public void setImageuri(Uri imageuri) {
        this.imageuri = imageuri;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }


    private static int lastContactId = 0;

    public static ArrayList<ModelUser>  createContactsList(int numContacts) {
        ArrayList<ModelUser> listusers = new ArrayList<ModelUser>();

        for (int i = 1; i <= numContacts; i++) {
            listusers.add(new ModelUser(String.valueOf(i) ,
                    "farouk "+i,
                    "Cr"+i ,
                    "fa"+i+"@gmail.com",
                    "0035193268609"+i ) );
        }

        return listusers;
    }

}
