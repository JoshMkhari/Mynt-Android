package com.example.mynt.collectionsActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mynt.R;
import com.example.mynt.Interface_RecyclerView;
import com.example.mynt.collectionsActivity.adapters.Adapter_Leaderboard;
import com.example.mynt.collectionsActivity.models.Model_Leaderboard;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment_Leaderboard extends Fragment implements Interface_RecyclerView {
    private ArrayList<Model_Leaderboard> array_list_leaderboard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view_leaderboard = inflater.inflate(R.layout.fragment_leaderboard, container, false);

        //Passing data to list recycler view
        RecyclerView recycler_view_leaderboard = view_leaderboard.findViewById(R.id.recycler_view_ranking_leaderboard);
        recycler_view_leaderboard.setHasFixedSize(true);

        //Ensuring the recycler view layout contains 1 item in each row
        RecyclerView.LayoutManager layout_manager_leaderboard = new StaggeredGridLayoutManager(1, 1);
        recycler_view_leaderboard.setLayoutManager(layout_manager_leaderboard);

        //Setting up adapter
        RecyclerView.Adapter<Adapter_Leaderboard.Card_View_Holder> rv_leaferbaord_adapter = new Adapter_Leaderboard(array_list_leaderboard);
        recycler_view_leaderboard.setAdapter(rv_leaferbaord_adapter);

        DisplayLeaderBoardRanks();

        return view_leaderboard;
    }

    private void DisplayLeaderBoardRanks(){

        //Creating list to store users and their ranks
        array_list_leaderboard = new ArrayList<>();

        //Populating leaderboard list
        for (int i =0; i<8;i++)
        {
            /*
                Using constructor to create new leaderboard items
                    Passing Username, UserScore, UserIcon
             */
            Model_Leaderboard lm = new Model_Leaderboard("IHasShoulders", 4396, R.drawable.ic_default_user_profile_icon);
            array_list_leaderboard.add(lm);
        }

    }
    @Override
    public void onItemClick(int position, ImageView coinImage) {
        //Code intent here if we are to code an on click event for this
    }
}