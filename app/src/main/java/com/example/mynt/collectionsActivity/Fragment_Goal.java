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
import android.widget.Toast;

import com.example.mynt.R;
import com.example.mynt.collectionsActivity.models.Model_Collections;
import com.example.mynt.collectionsActivity.models.Model_Goals;
import com.example.mynt.dataAccessLayer.Database_Lite;

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

        assert getArguments() != null;
        model_goals = new Model_Goals(getArguments().getString("Collection Name"),getArguments().getInt("Coins"),getArguments().getInt("Goal"));
        int task = getArguments().getInt("Task");

        collectionName_textView.setText(model_goals.getCollectionName());
        numCoinsInCollection_textView.setText(String.valueOf(model_goals.getNumCoins()));
        target_Edittext.setText(String.valueOf(model_goals.getTarget()));

        float coins = (float)model_goals.getNumCoins();
        float target = (float)model_goals.getTarget();
        float progress =  coins /target ;

        goalProgress_progressBar.setProgress(Math.round(progress));
        //model_goals = new Model_Goals(collectionName,numCoins,Integer.parseInt(target_Edittext.getText().toString()));

        //1000000 GoalsPage_add GoalsPage_subtract GoalsPage_GoalValue


        setGoal_imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(target_Edittext.getText().toString())!=0)
                {
                    Bundle bundle = new Bundle();
                    bundle.putString("User", getArguments().getString("User"));
                    Database_Lite localDB = new Database_Lite(getContext());

                    Model_Collections model_collections = new Model_Collections(model_goals.getCollectionName(),Integer.parseInt(target_Edittext.getText().toString()));
                    localDB.addCollection(model_collections);
                    //Add collection to database for user
                    if(task==0)// Creating new Collection
                    {
                        Navigation.findNavController(goals).navigate(R.id.action_fragment_Goal_to_fragment_Collections2,bundle);

                    }else //Creating new collection for a new coin
                    {
                        localDB.addCollectionCoin();
                        Navigation.findNavController(goals).navigate(R.id.action_fragment_Goal_to_fragment_home_main,bundle);
                    }
                }else
                {
                    Toast.makeText(getContext(), "Target cannot be 0", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return goals;
    }
}