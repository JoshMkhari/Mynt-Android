package com.example.mynt.mainActivity.models;

public class Model_Leaderboard {

    private String userName;
    private int userScore, imageID;

    public Model_Leaderboard(String userName, int userScore, int imageID) {
        this.userName = userName;
        this.userScore = userScore;
        this.imageID = imageID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }


    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
