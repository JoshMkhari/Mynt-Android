package com.example.mynt.homeAct.models;

public class Leaderboard_Model {

    String userName;
    int userScore, userRank, imageID;

    public Leaderboard_Model(String userName, int userScore ,int userRank, int imageID) {
        this.userName = userName;
        this.userScore = userScore;
        this.userRank = userRank;
        this.imageID = imageID;
    }

}
