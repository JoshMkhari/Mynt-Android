package com.example.mynt.collectionsActivity.models;

public class Model_Leaderboard {//(Section, 2021)

    private final String userName;
    private final int userScore;
    private final byte[] imageID;

    public Model_Leaderboard(String userName, int userScore, byte[] image) {
        this.userName = userName;
        this.userScore = userScore;
        this.imageID = image;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserScore() {
        return userScore;
    }

    public byte[] getImageID() {
        return imageID;
    }
}
