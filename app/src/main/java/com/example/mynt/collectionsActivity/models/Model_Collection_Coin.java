package com.example.mynt.collectionsActivity.models;

public class Model_Collection_Coin {
    private final int collectionID;
    private final int coinID;

    public Model_Collection_Coin(int collectionID, int coinID) {
        this.collectionID = collectionID;
        this.coinID = coinID;
    }

    public int getCollectionID() {
        return collectionID;
    }

    public int getCoinID() {
        return coinID;
    }
}
