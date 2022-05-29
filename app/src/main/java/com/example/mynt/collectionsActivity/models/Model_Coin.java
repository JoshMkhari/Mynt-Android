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

    public int getCoinID() {
        return coinID;
    }

    public void setCoinID(int coinID) {
        this.coinID = coinID;
    }

    public String getImageId() {
        return ImageId;
    }

    public String getValue() {
        return value;
    }

    public int getMintage() {
        return mintage;
    }


    public String getMaterial() {
        return material;
    }

    public String getAlternateName() {
        return alternateName;
    }

    public String getObserve() {
        return observe;
    }

    public String getReverse() {
        return reverse;
    }

    public String getVariety() {
        return variety;
    }


    public int getYear() {
        return year;
    }


}
