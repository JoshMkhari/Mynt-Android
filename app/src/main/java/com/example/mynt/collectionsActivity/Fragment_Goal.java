package com.example.mynt.collectionsActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mynt.R;
import com.example.mynt.collectionsActivity.models.Model_Goals;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class Fragment_Goal extends Fragment {
    private ImageButton setGoal_imageButton;
    private TextView collectionName_textView,target_textView,numCoinsInCollection_textView,percentOfGoal_textView;
    private ProgressBar goalProgress_progressBar;
    private EditText target_Edittext;
    private Model_Goals model_goals;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View goals = inflater.inflate(R.layout.fragment_goal, container, false);
        //Bundle extras = getIntent().getExtras();
        //String collectionName = extras.getString("collectionName");
        //int numCoins = extras.getInt("coins");
        //int target = extras.getInt("target");

        collectionName_textView = goals.findViewById(R.id.GoalPageCollectionName_TextView);
        numCoinsInCollection_textView = goals.findViewById(R.id.GoalsPageCoinsTotal_TextView);
        percentOfGoal_textView = goals.findViewById(R.id.GoalPagePercentage_TextView);
        target_textView = goals.findViewById(R.id.GoalsPageTarget_TextView);
        goalProgress_progressBar = goals.findViewById(R.id.GoalPageProgressBar);
        setGoal_imageButton = goals.findViewById(R.id.imageview_blockTitle_goal);
        target_Edittext = goals.findViewById(R.id.GoalsPage_GoalValue);

        collectionName_textView.setText(getArguments().getString("Collection Name"));

        //model_goals = new Model_Goals(collectionName,numCoins,Integer.parseInt(target_Edittext.getText().toString()));

        //1000000 GoalsPage_add GoalsPage_subtract GoalsPage_GoalValue


        setGoal_imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("Collection Name", getArguments().getString("Collection Name"));;
                bundle.putString("Task", "Create Collection");;
                Navigation.findNavController(goals).navigate(R.id.action_fragment_Goal_to_fragment_Add,bundle);
                //setResult(14,i);

                //Activity_Goals.super.onBackPressed();
            }
        });
        return goals;
    }
}