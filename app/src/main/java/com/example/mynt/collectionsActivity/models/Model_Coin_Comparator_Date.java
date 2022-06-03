package com.example.mynt.collectionsActivity.models;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

public class Model_Coin_Comparator_Date implements Comparator<Model_Coin> {//(GeeksForGeeks,2020)

    @Override
    public int compare(Model_Coin o1, Model_Coin o2) {
        Calendar calo1 = Calendar.getInstance();//(Shabbir Dhangot,2016)
        Calendar calo2 = Calendar.getInstance();//(Shabbir Dhangot,2016)
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        try {
            calo1.setTime(Objects.requireNonNull(sdf.parse(o1.getDateAcquired())));//(Shabbir Dhangot,2016)
            calo2.setTime(Objects.requireNonNull(sdf.parse(o2.getDateAcquired())));//(Shabbir Dhangot,2016)

            if (calo1.get(Calendar.YEAR)<calo2.get(Calendar.YEAR)) {
                //Date 1 occurs after Date 2
                return 1;
            } else if (calo1.get(Calendar.YEAR)>calo2.get(Calendar.YEAR)) {
                //Date 1 occurs before Date 2
                return -1;
            } else {
                if(calo1.get(Calendar.YEAR)==calo2.get(Calendar.YEAR))
                {
                    //Both Years are Equal
                    if (calo1.get(Calendar.DAY_OF_YEAR)<calo2.get(Calendar.DAY_OF_YEAR)) {
                        //Date 1 occurs after Date 2
                        return 1;
                    } else if (calo1.get(Calendar.DAY_OF_YEAR)>calo2.get(Calendar.DAY_OF_YEAR)) {
                        //Date 1 occurs before Date 2
                        return -1;
                    }else
                        return 0;//they are the same
                }else
                {
                    //years are not equal
                    return 1;
                }
            }
        } catch (ParseException e) {
            //e.printStackTrace();
            return -1;
        }

    }
}
