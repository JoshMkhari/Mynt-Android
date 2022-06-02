package com.example.mynt.collectionsActivity.models;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Model_Coin_Comparator_ID implements Comparator<Model_Coin> {

    @Override
    public int compare(Model_Coin o1, Model_Coin o2) {

        if (o1.getCoinID() == o2.getCoinID()) {
            return 0;
        } else if (o1.getCoinID() < o2.getCoinID()) {
            return 1;
        } else {
            return -1;
        }

    }
}
