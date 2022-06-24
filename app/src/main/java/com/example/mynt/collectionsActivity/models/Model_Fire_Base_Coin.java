package com.example.mynt.collectionsActivity.models;

public class Model_Fire_Base_Coin {

    private final String ValueYear;
    private final String DateTaken;


    public Model_Fire_Base_Coin(String valueYear, String dateTaken) {
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
