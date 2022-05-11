package com.example.mynt.coinsAct.models;

import java.util.ArrayList;

public class coins_baselist_model {

    ArrayList<coins_list_model> coins;
    String date;

    public ArrayList<coins_list_model> getCoins() {
        return coins;
    }

    public void setCoins(ArrayList<coins_list_model> coins) {
        this.coins = coins;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public coins_baselist_model(ArrayList<coins_list_model> coins, String date) {
        this.coins = coins;
        this.date = date;
    }
}
