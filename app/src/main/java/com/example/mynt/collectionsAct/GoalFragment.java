package com.example.mynt.collectionsAct;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mynt.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class GoalFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View goalView = inflater.inflate(R.layout.fragment_goal, container, false);

        TextView collectionName_textView = goalView.findViewById(R.id.GoalPageCollectionName_TextView);
        TextView numCoinsInCollection_textView = goalView.findViewById(R.id.GoalsPageCoinsTotal_TextView);
        TextView percentOfGoal_textView = goalView.findViewById(R.id.GoalPagePercentage_TextView);
        TextView target_textView = goalView.findViewById(R.id.GoalsPageTarget_TextView);
        ProgressBar goalProgress_progressBar = goalView.findViewById(R.id.GoalPageProgressBar);

        collectionName_textView.setText("Roaring 80s");
        numCoinsInCollection_textView.setText("2");
        percentOfGoal_textView.setText("0.2%");
        target_textView.setText("Target 1000");
        goalProgress_progressBar.setProgress(2);

        return goalView;
    }
}