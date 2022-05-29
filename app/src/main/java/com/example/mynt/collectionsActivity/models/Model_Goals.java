package com.example.mynt.collectionsActivity.models;

public class Model_Goals {
    private String collectionName;
    private int numCoins ,target;

    public Model_Goals(String collectionName, int numCoins, int target) {
        this.collectionName = collectionName;
        this.numCoins = numCoins;
        this.target = target;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public int getNumCoins() {
        return numCoins;
    }

    public int getTarget() {
        return target;
    }

}
