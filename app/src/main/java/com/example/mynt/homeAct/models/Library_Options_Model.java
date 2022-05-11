package com.example.mynt.homeAct.models;

public class Library_Options_Model {
    int imageId;
    String optionName;
    int progress;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(int optionValue) {
        this.optionValue = optionValue;
    }

    int optionValue;

    public Library_Options_Model(int imageId, String optionName, int progress, int optionValue) {
        this.imageId = imageId;
        this.optionName = optionName;
        this.progress = progress;
        this.optionValue = optionValue;
    }
}
