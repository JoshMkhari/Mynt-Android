package com.example.mynt.coinsActivity.models;

public class Model_Coin {

    private int imageId, value, year, mintage, circulation;
    private String name, alternateName, observe, reverse, type;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getMintage() {
        return mintage;
    }

    public void setMintage(int mintage) {
        this.mintage = mintage;
    }

    public int getCirculation() {
        return circulation;
    }

    public void setCirculation(int circulation) {
        this.circulation = circulation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlternateName() {
        return alternateName;
    }

    public void setAlternateName(String alternateName) {
        this.alternateName = alternateName;
    }

    public String getObserve() {
        return observe;
    }

    public void setObserve(String observe) {
        this.observe = observe;
    }

    public String getReverse() {
        return reverse;
    }

    public void setReverse(String reverse) {
        this.reverse = reverse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getCoinName() {
        return name;
    }

    public void setCoinName(String coinName) {
        this.name = coinName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Model_Coin(int imageId, String coinName, int year) {
        this.imageId = imageId;
        this.name = coinName;
        this.year = year;
    }
}
