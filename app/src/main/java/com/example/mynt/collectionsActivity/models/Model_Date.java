package com.example.mynt.collectionsActivity.models;

import android.util.Log;

import java.util.ArrayList;

public class Model_Date { //(Shabbir Dhangot,2016)



    public String makeDateString(int day, int month, int year, boolean type) {//(Code With Cal, 2020)
        //return month + " " + day + " " + year;
        return getMonthFormat(month,type) + " " + day + " " + year;
    }

    public String convertDateString(String date, boolean type) {//(Code With Cal, 2020)
        //return month + " " + day + " " + year;
        ArrayList<Integer> dashes = new ArrayList<>();
        for (int i = 0; i < date.length(); i++) {
            if(date.charAt(i) == '/')
            {
                dashes.add(i);
            }
        }

        int monthInt = Integer.parseInt(date.substring(0,dashes.get(0))) - 1;
        Log.d("month", "getMonthFormat: " + monthInt);
        int subStringDistance;
        subStringDistance = dashes.get(1)-dashes.get(0)-1;
        int start;
        start = dashes.get(0)+1;
        String dayInt = date.substring(start,start+subStringDistance);

        start = dashes.get(1)+1;
        String yearInt = date.substring(start);
        return getMonthFormat(monthInt,type) + " " + dayInt + " " + yearInt;
    }

    public String getMonthFormat(int month, boolean type) {//(Code With Cal, 2020)
        if(type)
        month++;

        switch (month) {
            case 1:
                return "JAN";
            case 2:
                return "FEB";
            case 3:
                return "MAR";
            case 4:
                return "APR";
            case 5:
                return "MAY";
            case 6:
                return "JUN";
            case 7:
                return "JUL";
            case 8:
                return "AUG";
            case 9:
                return "SEP";
            case 10:
                return "OCT";
            case 11:
                return "NOV";
            default:
                return "DEC";
        }
    }
    public String setupMonthAq(String substring) {//(Code With Cal, 2020)

        switch (substring)
        {
            case "JAN":
                return "01";
            case "FEB":
                return "02";
            case "MAR":
                return "03";
            case "APR":
                return "04";
            case "MAY":
                return "05";
            case "JUN":
                return "06";
            case "JUL":
                return "07";
            case "AUG":
                return "08";
            case "SEP":
                return "09";
            case "OCT":
                return "10";
            case "NOV":
                return "11";
            default:
                return "12";
        }

    }
}
