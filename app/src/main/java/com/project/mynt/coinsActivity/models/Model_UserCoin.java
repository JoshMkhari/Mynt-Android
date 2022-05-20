package com.project.mynt.coinsActivity.models;

import java.util.Date;

public class Model_UserCoin {

    private int userCoinID;
    private int imageID;//User taken image
    private Date dateTaken;
    private String takenLocation;
    private Model_Coin coin;

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

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public Date getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(Date dateTaken) {
        this.dateTaken = dateTaken;
    }

    public String getTakenLocation() {
        return takenLocation;
    }

    public void setTakenLocation(String takenLocation) {
        this.takenLocation = takenLocation;
    }

}
