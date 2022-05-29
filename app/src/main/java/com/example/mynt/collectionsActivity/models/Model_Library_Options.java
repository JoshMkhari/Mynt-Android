package com.example.mynt.collectionsActivity.models;

public class Model_Library_Options {
    private final String optionName;

    public String getOptionName() {
        return optionName;
    }

    public int getOptionValue() {
        return optionValue;
    }

    final int optionValue;

    public Model_Library_Options(String optionName, int optionValue) {
        this.optionName = optionName;
        this.optionValue = optionValue;
    }
}
