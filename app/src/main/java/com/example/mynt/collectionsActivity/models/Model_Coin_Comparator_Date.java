package com.example.mynt.collectionsActivity.models;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Model_Coin_Comparator_Date implements Comparator<Model_Coin> {

    @Override
    public int compare(Model_Coin o1, Model_Coin o2) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat SDFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            Date d1 = SDFormat.parse(o1.getDateAcquired());
            Date d2 = SDFormat.parse(o2.getDateAcquired());
            assert d1 != null;
            if (d1.compareTo(d2)>0) {
                //Date 1 occurs after Date 2
                return 1;
            } else if (d1.compareTo(d2)<0) {
                //Date 1 occurs before Date 2
                return 0;
            } else {
                //Both dates are equal
                return -1;
            }
        } catch (ParseException e) {
            //e.printStackTrace();
            return -1;
        }

    }
}
