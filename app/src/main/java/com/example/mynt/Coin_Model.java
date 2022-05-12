package com.example.mynt;

public class Coin_Model {

    int imageId;
    String coinName;
    int year;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Coin_Model(int imageId, String coinName, int year) {
        this.imageId = imageId;
        this.coinName = coinName;
        this.year = year;
    }
}
