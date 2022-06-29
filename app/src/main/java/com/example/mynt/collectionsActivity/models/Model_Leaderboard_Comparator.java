package com.example.mynt.collectionsActivity.models;

import java.util.Calendar;
import java.util.Comparator;

public class Model_Leaderboard_Comparator implements Comparator<Model_Leaderboard> {
    @Override
    public int compare(Model_Leaderboard o1, Model_Leaderboard o2) {
        if (o1.getUserScore() < o2.getUserScore()) {
            //Leaderboard 1 occurs after Leaderboard 2
            return 1;
        } else if (o1.getUserScore() > o2.getUserScore()) {
            //Date 1 occurs before Date 2
            return -1;
        } else return 0;
    }
}
