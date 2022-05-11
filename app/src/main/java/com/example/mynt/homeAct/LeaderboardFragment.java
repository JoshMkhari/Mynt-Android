package com.example.mynt.homeAct;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mynt.R;
import com.example.mynt.homeAct.adapters.Leaderboard_ListAdapter;
import com.example.mynt.homeAct.models.Leaderboard_Model;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LeaderboardFragment#} factory method to
 * create an instance of this fragment.
 */
public class LeaderboardFragment extends Fragment {
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //https://stackoverflow.com/questions/6495898/findviewbyid-in-fragment
        View leaderboardView = inflater.inflate(R.layout.fragment_leaderboard, container, false);
        listView = (ListView) leaderboardView.findViewById(R.id.leaderboardListView);

        ArrayList<Leaderboard_Model> leaderboardList = new ArrayList<Leaderboard_Model>();

        for (int i =0; i<8;i++)
        {
            Leaderboard_Model lm = new Leaderboard_Model("IHasShoulders"+i,4396-i*2,i,R.drawable.default_user_profile_icon);
            leaderboardList.add(lm);
        }
        Leaderboard_ListAdapter listAdapter = new Leaderboard_ListAdapter(getContext(),leaderboardList);
        listView.setAdapter((listAdapter));
        return leaderboardView;
    }
}