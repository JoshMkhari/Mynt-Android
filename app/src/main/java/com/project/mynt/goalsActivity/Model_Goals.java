package com.project.mynt.goalsActivity;

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

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public int getNumCoins() {
        return numCoins;
    }

    public void setNumCoins(int numCoins) {
        this.numCoins = numCoins;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

}
