package com.example.mynt.collectionsActivity.models;

public class Model_Goals {//(Section, 2021)
    private final String collectionName;
    private int numCoins;
    private final int target;

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

    public void setNumCoins(int size) {
        this.numCoins = size;
    }
}
