package com.example.mynt.collectionsActivity.models;

public class ModelFireBaseCoin {

    private final String ValueYear;
    private final String DateTaken;


    public ModelFireBaseCoin(String valueYear, String dateTaken) {
        ValueYear = valueYear;
        DateTaken = dateTaken;
    }

    public String getValueYear() {
        return ValueYear;
    }

    public String getDateTaken() {
        return DateTaken;
    }
}
