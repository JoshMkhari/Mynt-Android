package com.example.mynt.collectionsActivity.models;

public class ModelFireBaseCoin {

    private final String ValueYear;
    private final String DateTaken;
    private final int CoinID;


    public ModelFireBaseCoin(String valueYear, String dateTaken, int coinID) {
        ValueYear = valueYear;
        DateTaken = dateTaken;
        CoinID = coinID;
    }

    public String getValueYear() {
        return ValueYear;
    }

    public String getDateTaken() {
        return DateTaken;
    }

    public int getCoinID() {return CoinID;
    }
}
