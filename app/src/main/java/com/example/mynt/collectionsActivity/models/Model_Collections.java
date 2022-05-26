package com.example.mynt.collectionsActivity.models;

import java.util.ArrayList;

public class Model_Collections {

    private int CollectionID;//Image for collection cover
    private String CollectionName;
    private ArrayList<Model_UserCollection> model_userCollectionArrayList;
    private int goal;

    public Model_Collections(String collectionName, int goal) {
        CollectionName = collectionName;
        this.goal = goal;
    }



    private int CollectionCoinAmount;
    private int ImageID;//Cover should be last added imnage

    public ArrayList<Model_UserCollection> getModel_userArrayList() {
        return model_userCollectionArrayList;
    }

    public void setModel_userArrayList(ArrayList<Model_UserCollection> model_userArrayList) {
        this.model_userCollectionArrayList = model_userArrayList;
    }


    public int getCollectionID() {
        return CollectionID;
    }

    public void setCollectionID(int collectionID) {
        CollectionID = collectionID;
    }


    public String getCollectionName() {
        return CollectionName;
    }

    public void setCollectionName(String collectionName) {
        CollectionName = collectionName;
    }

    public int getCollectionCoinAmount() {
        return CollectionCoinAmount;
    }

    public void setCollectionCoinAmount(int collectionCoinAmount) {
        CollectionCoinAmount = collectionCoinAmount;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public int getImageID() {
        return ImageID;
    }

    public void setImageID(int imageID) {
        ImageID = imageID;
    }
}
