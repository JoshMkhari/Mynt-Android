package com.project.mynt.goalsActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.project.mynt.Activity_Main;
import com.example.mynt.R;
import com.project.mynt.dataAccessLayer.Database_Lite;

public class Activity_Goals extends AppCompatActivity {
    private ImageButton setGoal_imageButton;
    private TextView collectionName_textView,target_textView,numCoinsInCollection_textView,percentOfGoal_textView;
    private ProgressBar goalProgress_progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        collectionName_textView = findViewById(R.id.GoalPageCollectionName_TextView);
        numCoinsInCollection_textView = findViewById(R.id.GoalsPageCoinsTotal_TextView);
        percentOfGoal_textView = findViewById(R.id.GoalPagePercentage_TextView);
        target_textView = findViewById(R.id.GoalsPageTarget_TextView);
        goalProgress_progressBar = findViewById(R.id.GoalPageProgressBar);
        setGoal_imageButton = findViewById(R.id.imageview_blockTitle_goal);

        Model_Goals model_goals = new Model_Goals("NOnsense",5,50);


        collectionName_textView.setText(model_goals.getCollectionName());
        numCoinsInCollection_textView.setText(String.valueOf(model_goals.getNumCoins()));
        percentOfGoal_textView.setText("0.2%");
        target_textView.setText("Target " + model_goals.getTarget());
        goalProgress_progressBar.setProgress(model_goals.getNumCoins());


        setGoal_imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Activity_Main.class);
                Database_Lite database_lite = new Database_Lite(getApplicationContext());
                //database_lite.populateCoins();
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
    }
}