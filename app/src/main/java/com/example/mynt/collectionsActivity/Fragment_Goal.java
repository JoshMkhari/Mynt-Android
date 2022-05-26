package com.example.mynt.collectionsActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynt.R;
import com.example.mynt.collectionsActivity.models.Model_Coin;
import com.example.mynt.collectionsActivity.models.Model_Collections;
import com.example.mynt.collectionsActivity.models.Model_Goals;
import com.example.mynt.collectionsActivity.models.Model_User;
import com.example.mynt.dataAccessLayer.Database_Lite;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class Fragment_Goal extends Fragment {

    private EditText target_Edittext;
    private String oldText;
    private Model_Goals model_goals;
    private ImageButton setGoal_imageButton, back, subtract,add;
    private TextView collectionName_textView,target_textView,numCoinsInCollection_textView,percentOfGoal_textView;
    private ProgressBar goalProgress_progressBar;
    private View goals;
    private Model_User model_user;
    private String userID;
    private String targetText;
    private float coins;
    private float target;
    private float progress;
    private String percentage;
    private String currentText;
    private int currentTarget;
    private int task;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        goals = inflater.inflate(R.layout.fragment_goal, container, false);

        collectionName_textView = goals.findViewById(R.id.GoalPageCollectionName_TextView);
        numCoinsInCollection_textView = goals.findViewById(R.id.GoalsPageCoinsTotal_TextView);
        percentOfGoal_textView = goals.findViewById(R.id.GoalPagePercentage_TextView);
        target_textView = goals.findViewById(R.id.GoalsPageTarget_TextView);
        goalProgress_progressBar = goals.findViewById(R.id.GoalPageProgressBar);
        setGoal_imageButton = goals.findViewById(R.id.imageview_blockTitle_goal);
        back = goals.findViewById(R.id.GoalsPage_back);
        target_Edittext = goals.findViewById(R.id.GoalsPage_GoalValue);
        add = goals.findViewById(R.id.GoalsPage_add);
        subtract = goals.findViewById(R.id.GoalsPage_subtract);

        //GoalsPage_add
        //GoalsPage_subtract



        assert getArguments() != null;
        model_goals = new Model_Goals(getArguments().getString("Collection Name"),getArguments().getInt("Coins"),getArguments().getInt("Goal"));
        task = getArguments().getInt("Task");
        model_user = new Model_User();
        model_user.setUserID(getArguments().getInt("User"));


        userID = model_user.getUserID() + " this";
        Log.d("goal", userID);

        collectionName_textView.setText(model_goals.getCollectionName());
        numCoinsInCollection_textView.setText(String.valueOf(model_goals.getNumCoins()));
        target_Edittext.setText(String.valueOf(model_goals.getTarget()));
        targetText = "Target: " + String.valueOf(model_goals.getTarget());
        target_textView.setText(targetText);



        goalProgress_progressBar.setProgress(Math.round(progress));
        //model_goals = new Model_Goals(collectionName,numCoins,Integer.parseInt(target_Edittext.getText().toString()));
        //1000000 GoalsPage_add GoalsPage_subtract GoalsPage_GoalValue


        ReturnToHomePage();
        CreateGoal();
        CalculateGoalProgress();

        return goals;
    }

    private void ReturnToHomePage(){



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(goals).navigateUp();
            }
        });
    }
    private void CreateGoal(){

        setGoal_imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(target_Edittext.getText().toString())!=0)
                {
                    Database_Lite localDB = new Database_Lite(getContext());


                    Model_Collections model_collections = new Model_Collections(model_goals.getCollectionName(),Integer.parseInt(target_Edittext.getText().toString()));

                    localDB.addCollection(model_collections,model_user);
                    //Add collection to database for user
                    if(task==1)// Creating new Collection and assigning it to a coin
                    {
                        Toast.makeText(getContext(), "Running new", Toast.LENGTH_SHORT).show();
                        //Get latest collection ID
                        ArrayList<Integer> userCollectionIDs = localDB.getAllCollectionsForUser(model_user);
                        ArrayList<Model_Collections> allCollections = localDB.getAllCollections();

                        ArrayList<Model_Collections> allUserCollections = new ArrayList<>();

                        for (int i=0; i<allCollections.size(); i++)
                        {
                            if(userCollectionIDs.contains(allCollections.get(i).getCollectionID()))
                                allUserCollections.add(allCollections.get(i));
                        }
                        localDB.addCollectionCoin(allUserCollections.get(allUserCollections.size()-1).getCollectionID());
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



        target_Edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                oldText = target_Edittext.getText().toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                currentText = target_Edittext.getText().toString();
                if(currentText.length()==5)
                {
                    target_Edittext.setText(oldText);
                    Toast.makeText(getContext(), "Goal cannot be greater than 9999", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String targetText = "Target: " + currentText;
                    target_textView.setText(targetText);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        target_Edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                currentTarget = Integer.parseInt(target_Edittext.getText().toString());
                if (currentTarget==0)
                {
                    target_Edittext.setText("");
                }
            }
        });
        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentTarget = Integer.parseInt(target_Edittext.getText().toString());
                currentTarget--;
                target_Edittext.setText(String.valueOf(currentTarget));
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentTarget = Integer.parseInt(target_Edittext.getText().toString());
                currentTarget++;
                target_Edittext.setText(String.valueOf(currentTarget));
            }
        });
    }

    private void CalculateGoalProgress(){

        coins = (float)model_goals.getNumCoins();
        target = (float)model_goals.getTarget();
        progress =  coins /target *100;
        percentage = String.valueOf(Math.round(progress)) + '%';
        percentOfGoal_textView.setText(percentage);


    }
}