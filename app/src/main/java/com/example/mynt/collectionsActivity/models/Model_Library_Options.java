package com.example.mynt.collectionsActivity.models;

public class Model_Library_Options {
    private int imageId;
    private String optionName;
    private int progress;

    public String getOptionName() {
        return optionName;
    }

    public int getOptionValue() {
        return optionValue;
    }

    int optionValue;

    public Model_Library_Options(int imageId, String optionName, int progress, int optionValue) {
        this.imageId = imageId;
        this.optionName = optionName;
        this.progress = progress;
        this.optionValue = optionValue;
    }
}
