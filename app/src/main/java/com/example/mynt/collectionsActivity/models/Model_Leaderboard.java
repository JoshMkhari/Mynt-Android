package com.example.mynt.collectionsActivity.models;

public class Model_Leaderboard {

    private final String userName;
    private final int userScore;
    private final int imageID;

    public Model_Leaderboard(String userName, int userScore, int imageID) {
        this.userName = userName;
        this.userScore = userScore;
        this.imageID = imageID;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserScore() {
        return userScore;
    }


    public int getImageID() {
        return imageID;
    }

}
