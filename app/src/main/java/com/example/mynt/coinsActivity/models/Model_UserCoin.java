package com.example.mynt.coinsActivity.models;

import java.util.Date;

public class Model_UserCoin {

    private int userCoinID;
    private String takenLocation,dateTaken;
    private Model_Coin coin;

    public Model_UserCoin(String dateTaken, String takenLocation, Model_Coin coin) {
        this.dateTaken = dateTaken;
        this.takenLocation = takenLocation;
        this.coin = coin;
    }

    public Model_Coin getCoin() {
        return coin;
    }

    public void setCoin(Model_Coin coin) {
        this.coin = coin;
    }

    public int getUserCoinID() {
        return userCoinID;
    }

    public void setUserCoinID(int userCoinID) {
        this.userCoinID = userCoinID;
    }


    public String getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(String dateTaken) {
        this.dateTaken = dateTaken;
    }

    public String getTakenLocation() {
        return takenLocation;
    }

    public void setTakenLocation(String takenLocation) {
        this.takenLocation = takenLocation;
    }

}
