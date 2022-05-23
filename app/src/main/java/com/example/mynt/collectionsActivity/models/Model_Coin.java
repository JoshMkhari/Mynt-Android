package com.example.mynt.collectionsActivity.models;

import android.content.res.Resources;

import com.example.mynt.R;

public class Model_Coin {

    private int year, mintage, coinID;
    private String material, alternateName, observe, reverse, variety,value,ImageId, DateTaken;

    public Model_Coin(int year, int mintage, String material, String alternateName, String observe, String reverse, String variety, String value, String image, String dateTaken) {

        this.year = year;
        this.mintage = mintage;
        this.material = material;
        this.alternateName = alternateName;
        this.observe = observe;
        this.reverse = reverse;
        this.variety = variety;
        this.value = value;
        this.ImageId = image;
        this.DateTaken = dateTaken;
    }

    public String getDateTaken() {
        return DateTaken;
    }

    public void setDateTaken(String dateTaken) {
        DateTaken = dateTaken;
    }

    public int getCoinID() {
        return coinID;
    }

    public void setCoinID(int coinID) {
        this.coinID = coinID;
    }

    public String getImageId() {
        return ImageId;
    }

    public void setImageId(String imageId) {
        ImageId = imageId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getMintage() {
        return mintage;
    }

    public void setMintage(int mintage) {
        this.mintage = mintage;
    }


    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
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

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }


    public String getCoinName() {
        return material;
    }

    public void setCoinName(String coinName) {
        this.material = coinName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


}
