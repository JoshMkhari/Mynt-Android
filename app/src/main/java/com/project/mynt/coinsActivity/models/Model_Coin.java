package com.project.mynt.coinsActivity.models;

import android.content.res.Resources;

import com.example.mynt.R;

public class Model_Coin {

    private Resources res;
    private int year, mintage, ImageId;
    private String material, alternateName, observe, reverse, variety,value ;



    public Model_Coin(int year, int mintage, int material, String alternateName, String observe, String reverse, int variety, int value, String image) {
        this.year = year;
        this.mintage = mintage;
        String[] dataList = res.getStringArray(R.array.Material);
        this.material = dataList[material];
        this.alternateName = alternateName;
        this.observe = observe;
        this.reverse = reverse;
        dataList = res.getStringArray(R.array.Variants);
        this.variety = dataList[variety];
        dataList = res.getStringArray(R.array.Value);
        this.value = dataList[value];
        this.ImageId = 55;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
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
