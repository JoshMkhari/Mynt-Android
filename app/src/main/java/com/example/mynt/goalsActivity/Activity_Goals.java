package com.example.mynt.goalsActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mynt.R;

public class Activity_Goals extends AppCompatActivity {

    ViewPager2 tempPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        TextView collectionName_textView = findViewById(R.id.GoalPageCollectionName_TextView);
        TextView numCoinsInCollection_textView = findViewById(R.id.GoalsPageCoinsTotal_TextView);
        TextView percentOfGoal_textView = findViewById(R.id.GoalPagePercentage_TextView);
        TextView target_textView = findViewById(R.id.GoalsPageTarget_TextView);
        ProgressBar goalProgress_progressBar = findViewById(R.id.GoalPageProgressBar);

        Model_Goals model_goals = new Model_Goals("Roaring 80s",2,1000);
        collectionName_textView.setText(model_goals.getCollectionName());
        numCoinsInCollection_textView.setText(""+model_goals.getNumCoins());
        percentOfGoal_textView.setText("0.2%");
        target_textView.setText("Target " + model_goals.getTarget());
        goalProgress_progressBar.setProgress(model_goals.getNumCoins());
    }
}