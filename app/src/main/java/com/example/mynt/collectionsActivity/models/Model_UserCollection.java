package com.example.mynt.collectionsActivity.models;

public class Model_UserCollection {

    private int userCollectionID, collectionID;
    private String userID;

    public Model_UserCollection(int collectionID, String userID) {
        this.collectionID = collectionID;
        this.userID = userID;
    }

    public int getCollectionID() {
        return collectionID;
    }

    public void setCollectionID(int collectionID) {
        this.collectionID = collectionID;
    }

    public int getUserCollectionID() {
        return userCollectionID;
    }

    public void setUserCollectionID(int userCollectionID) {
        this.userCollectionID = userCollectionID;
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

}
