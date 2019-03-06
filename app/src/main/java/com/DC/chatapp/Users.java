package com.DC.chatapp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class Users implements Serializable{

    private DatabaseReference mUsersDatabase;
    private String name;
    private String image;
    private String status;

//    mUsersDatabase = FirebaseDatabase.getInstance().getReference("Users");

    public Users(){
    }

    public Users(String name, String image, String status) {
        this.name = name;
        this.image = image;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    public String getKey() {
//
//        return
//    }
}
