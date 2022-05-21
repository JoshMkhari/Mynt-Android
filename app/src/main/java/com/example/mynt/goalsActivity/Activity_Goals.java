package com.example.mynt.goalsActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mynt.Activity_Main;
import com.example.mynt.R;
import com.example.mynt.dataAccessLayer.Database_Lite;

public class Activity_Goals extends AppCompatActivity {
    private ImageButton setGoal_imageButton;
    private TextView collectionName_textView,target_textView,numCoinsInCollection_textView,percentOfGoal_textView;
    private ProgressBar goalProgress_progressBar;
    private EditText target_Edittext;
    private Model_Goals model_goals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);
        
        //Bundle extras = getIntent().getExtras();
        //String collectionName = extras.getString("collectionName");
        //int numCoins = extras.getInt("coins");
        //int target = extras.getInt("target");
        
        collectionName_textView = findViewById(R.id.GoalPageCollectionName_TextView);
        numCoinsInCollection_textView = findViewById(R.id.GoalsPageCoinsTotal_TextView);
        percentOfGoal_textView = findViewById(R.id.GoalPagePercentage_TextView);
        target_textView = findViewById(R.id.GoalsPageTarget_TextView);
        goalProgress_progressBar = findViewById(R.id.GoalPageProgressBar);
        setGoal_imageButton = findViewById(R.id.imageview_blockTitle_goal);
        target_Edittext = findViewById(R.id.GoalsPage_GoalValue);

        //model_goals = new Model_Goals(collectionName,numCoins,Integer.parseInt(target_Edittext.getText().toString()));

        //1000000 GoalsPage_add GoalsPage_subtract GoalsPage_GoalValue


        setGoal_imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("target",String.valueOf(model_goals.getTarget()));
                setResult(14,i);

                Activity_Goals.super.onBackPressed();
            }
        });
    }
}