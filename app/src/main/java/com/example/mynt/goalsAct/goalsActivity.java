package com.example.mynt.goalsAct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mynt.R;

public class goalsActivity extends AppCompatActivity {

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

        collectionName_textView.setText("Roaring 80s");
        numCoinsInCollection_textView.setText("2");
        percentOfGoal_textView.setText("0.2%");
        target_textView.setText("Target 1000");
        goalProgress_progressBar.setProgress(2);
    }
}