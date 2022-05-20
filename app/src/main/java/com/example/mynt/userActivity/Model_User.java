package com.example.mynt.userActivity;

import com.example.mynt.collectionsActivity.Model_Collections;

import java.util.ArrayList;

public class Model_User {

    private String email;
    private String password;
    private String userName;
    private int profilePicture;
    private ArrayList<Model_Collections> collectionsArrayList;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<Model_Collections> getCollectionsArrayList() {
        return collectionsArrayList;
    }

    public void setCollectionsArrayList(ArrayList<Model_Collections> collectionsArrayList) {
        this.collectionsArrayList = collectionsArrayList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(int profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
