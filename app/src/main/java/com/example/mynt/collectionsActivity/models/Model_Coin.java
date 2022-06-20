package com.example.mynt.collectionsActivity.models;

import java.io.Serializable;
import java.util.Comparator;

public class Model_Coin implements Serializable {//(GeeksForGeeks,2020)

    //(Section, 2021)
    private final int year;
    private final int mintage;
    private int coinID;
    private final String material;
    private final String alternateName;
    private final String observe;
    private final String reverse;
    private final String variety;
    private final String value;
    private final byte[] ImageId;
    private final String DateTaken;

    public Model_Coin(int year, int mintage, String material, String alternateName, String observe, String reverse, String variety, String value, byte[] image, String dateTaken) {

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

    public String getDateAcquired() {
        return DateTaken;
    }

    public int getCoinID() {
        return coinID;
    }

    public void setCoinID(int coinID) {
        this.coinID = coinID;
    }

    public byte[] getImageId() {
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

