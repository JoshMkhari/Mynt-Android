package com.example.mynt.collectionsActivity.models;

import java.util.List;

public class Model_Fire_Base_User {
    private String email;
    private String userName;
    private int state;
    private List<Model_Collections> collections;
    private String lastSync;


    public Model_Fire_Base_User(String email, String userName, int state, List<Model_Collections> collections, String lastSync) {
        this.email = email;
        this.userName = userName;
        this.state = state;
        this.collections = collections;
        this.lastSync = lastSync;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public int getState() {
        return state;
    }

    public List<Model_Collections> getCollections() {
        return collections;
    }

    public String getLastSync() {
        return lastSync;
    }
}
