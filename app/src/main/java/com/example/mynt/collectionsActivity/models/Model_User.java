package com.example.mynt.collectionsActivity.models;

public class Model_User {//(Section, 2021)

    private String email;
    private String password;
    private String uuid;
    private int state,userID;

    public Model_User(String email, String password, int state) {
        this.email = email;
        this.password = password;
        this.uuid = uuid;
        this.state = state;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
