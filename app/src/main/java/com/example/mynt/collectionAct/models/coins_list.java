package com.example.mynt.collectionAct.models;

public class coins_list {
    int imageId;
    String coinName;
    String coinDate;
    String country;
    int year;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public coins_list(int imageId, String coinName, String coinDate, String country, int year) {
        this.imageId = imageId;
        this.coinName = coinName;
        this.coinDate = coinDate;
        this.country = country;
        this.year = year;
    }

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

    public String getCoinDate() {
        return coinDate;
    }

    public void setCoinDate(String coinDate) {
        this.coinDate = coinDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
