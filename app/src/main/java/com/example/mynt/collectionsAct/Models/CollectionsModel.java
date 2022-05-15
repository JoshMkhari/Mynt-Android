package com.example.mynt.collectionsAct.Models;

public class CollectionsModel {
    String CollectionName;
    int CollectionCoinAmount;
    int CollectionProgressBar;
    int ImageID;

    public CollectionsModel(String collectionName, int collectionCoinAmount, int collectionProgressBar, int imageID) {
        CollectionName = collectionName;
        CollectionCoinAmount = collectionCoinAmount;
        CollectionProgressBar = collectionProgressBar;
        ImageID = imageID;
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

    public int getCollectionProgressBar() {
        return CollectionProgressBar;
    }

    public void setCollectionProgressBar(int collectionProgressBar) {
        CollectionProgressBar = collectionProgressBar;
    }

    public int getImageID() {
        return ImageID;
    }

    public void setImageID(int imageID) {
        ImageID = imageID;
    }
}
