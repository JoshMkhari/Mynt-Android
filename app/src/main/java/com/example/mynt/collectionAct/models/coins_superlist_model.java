package com.example.mynt.collectionAct.models;

import java.util.ArrayList;

public class coins_superlist_model {
    ArrayList<coins_baselist_model> coins;

    public coins_superlist_model(ArrayList<coins_baselist_model> coins) {
        this.coins = coins;
    }

    public ArrayList<coins_baselist_model> getCoins() {
        return coins;
    }

    public void setCoins(ArrayList<coins_baselist_model> coins) {
        this.coins = coins;
    }
}
