package com.example.mynt.collectionsActivity.models;

import java.util.ArrayList;

public class Model_Collections {//(Section, 2021)

    private int CollectionID;//Image for collection cover
    private final String CollectionName;
    private final int goal;
    private int size;
    private ArrayList<ModelFireBaseCoin> fireBaseCoinscoins;

    public Model_Collections(String collectionName, int goal) {
        CollectionName = collectionName;
        this.goal = goal;
    }

    public Model_Collections(String collectionName, int goal, ArrayList<ModelFireBaseCoin> fireBaseCoinscoins) {
        CollectionName = collectionName;
        this.goal = goal;
        this.fireBaseCoinscoins = fireBaseCoinscoins;
    }

    public ArrayList<ModelFireBaseCoin> getFireBaseCoinscoins() {
        return fireBaseCoinscoins;
    }

    public void setFireBaseCoinscoins(ArrayList<ModelFireBaseCoin> fireBaseCoinscoins) {
        this.fireBaseCoinscoins = fireBaseCoinscoins;
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
