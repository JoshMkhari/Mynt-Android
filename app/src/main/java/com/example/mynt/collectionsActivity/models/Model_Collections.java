package com.example.mynt.collectionsActivity.models;

public class Model_Collections {

    private int CollectionID;//Image for collection cover
    private final String CollectionName;
    private final int goal;

    public Model_Collections(String collectionName, int goal) {
        CollectionName = collectionName;
        this.goal = goal;
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

    public int getGoal() {
        return goal;
    }

}
