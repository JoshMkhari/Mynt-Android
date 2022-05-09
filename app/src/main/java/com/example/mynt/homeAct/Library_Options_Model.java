package com.example.mynt.homeAct;

public class Library_Options_Model {
    int imageId;
    String optionName;
    int progress;
    int optionValue;

    public Library_Options_Model(int imageId, String optionName, int progress, int optionValue) {
        this.imageId = imageId;
        this.optionName = optionName;
        this.progress = progress;
        this.optionValue = optionValue;
    }
}
