package com.example.mynt.collectionsActivity.models;

import java.util.Comparator;

public class CoinListComparator implements Comparator<Model_Coin> {

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
