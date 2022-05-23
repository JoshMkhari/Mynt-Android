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

    private EditText target_Edittext;
    private Model_Goals model_goals;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ImageButton setGoal_imageButton, back;
        TextView collectionName_textView,target_textView,numCoinsInCollection_textView,percentOfGoal_textView;
        ProgressBar goalProgress_progressBar;
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
        back = goals.findViewById(R.id.GoalsPage_back);
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
        Toast.makeText(getContext(), task + " this is it", Toast.LENGTH_SHORT).show();
        goalProgress_progressBar.setProgress(Math.round(progress));
        //model_goals = new Model_Goals(collectionName,numCoins,Integer.parseInt(target_Edittext.getText().toString()));

        //1000000 GoalsPage_add GoalsPage_subtract GoalsPage_GoalValue

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(goals).navigateUp();
            }
        });

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
                    if(task==1)// Creating new Collection and assigning it to a coin
                    {
                        localDB.addCollectionCoin(0);
                    }
                    Intent home = new Intent(getContext(),Activity_Collections.class);
                    //home.putExtra("View","library");
                    home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(home);

                }else
                {
                    Toast.makeText(getContext(), "Target cannot be 0", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return goals;
    }
}