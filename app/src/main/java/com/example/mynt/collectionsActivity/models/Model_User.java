package com.example.mynt.collectionsActivity.models;

import java.util.ArrayList;
import java.util.List;

public class Model_User {//(Section, 2021)

    private String email;
    private String password;
    private int state,userID;
    private List<Model_Collections> collections;

    public Model_User(String email, String password, int state) {
        this.email = email;
        this.password = password;
        this.state = state;
    }

    public List<Model_Collections> getCollections() {
        return collections;
    }

    public void setCollections(List<Model_Collections> collections) {
        this.collections = collections;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getState() {
        return state;
    }


    public void setState(int state) {
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
