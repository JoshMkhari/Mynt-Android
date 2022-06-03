package com.example.mynt.collectionsActivity.models;

public class Model_Collections {//(Section, 2021)

    private int CollectionID;//Image for collection cover
    private final String CollectionName;
    private final int goal;
    private int size;

    public Model_Collections(String collectionName, int goal) {
        CollectionName = collectionName;
        this.goal = goal;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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
