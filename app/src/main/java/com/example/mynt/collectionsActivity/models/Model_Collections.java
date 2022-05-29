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


    public int getCollectionID() {
        return CollectionID;
    }

    public void setCollectionID(int collectionID) {
        CollectionID = collectionID;
    }


    public String getCollectionName() {
        return CollectionName;
    }

    public int getGoal() {
        return goal;
    }

}
