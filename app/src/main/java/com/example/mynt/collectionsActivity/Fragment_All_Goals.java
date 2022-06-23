package com.example.mynt.collectionsActivity;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mynt.R;
import com.example.mynt.collectionsActivity.models.Model_Coin;
import com.example.mynt.collectionsActivity.models.Model_Collections;
import com.example.mynt.collectionsActivity.models.Model_Goals;
import com.example.mynt.collectionsActivity.models.User_Data;
import com.example.mynt.dataAccessLayer.Database_Lite;
import com.example.mynt.dataAccessLayer.Model_Database_Lite;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_All_Goals} factory method to
 * create an instance of this fragment.
 */
public class Fragment_All_Goals extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View allGoals = inflater.inflate(R.layout.fragment_all_goals, container, false);

        PieChart pieChart = allGoals.findViewById(R.id.pie_chart);

        ArrayList<PieEntry> goals = new ArrayList<>();


        Database_Lite localDB = new Database_Lite(getContext());
        ArrayList<Model_Collections> allCollections = localDB.getAllCollections();
        Model_Database_Lite mdl = new Model_Database_Lite();
        for (Model_Collections currentCollection: allCollections
             ) {
            ArrayList<Model_Coin> coinsList = mdl.allCoinsAndCollections(getContext(),1,currentCollection.getCollectionID());
            float collectionSize = (float)coinsList.size();
            float target = (float)currentCollection.getGoal();
            float goal = collectionSize/target*100;
            String pieChartTitle = currentCollection.getCollectionName() + " " + Math.round(goal) + "%";
            goals.add(new PieEntry(coinsList.size(),pieChartTitle));
        }

        //X collection Name
        //Y axis second number

        PieDataSet pieDataSet = new PieDataSet(goals,"Goal Progress");
        //Bar colours
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        //CHange to theme colour
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(true);
        pieChart.setCenterText("All Collections");
        pieChart.animate();
        return allGoals;
    }
}