package com.example.mynt.collectionsActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mynt.R;
import com.example.mynt.RecyclerViewInterface;
import com.example.mynt.collectionsActivity.adapters.Adapter_Leaderboard;
import com.example.mynt.collectionsActivity.models.Model_Leaderboard;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment_Leaderboard extends Fragment implements RecyclerViewInterface  {
    private RecyclerView recycler_view_leaderboard;
    private RecyclerView.Adapter rv_leaferbaord_adapter;
    private RecyclerView.LayoutManager layout_manager_leaderboard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view_leaderboard = inflater.inflate(R.layout.fragment_leaderboard, container, false);

        //Creating list to store users and their ranks
        ArrayList<Model_Leaderboard> array_list_leaderboard = new ArrayList<Model_Leaderboard>();

        //Populating leaderboard list
        for (int i =0; i<8;i++)
        {
            /*
                Using constructor to create new leaderboard items
                    Passing Username, UserScore, UserIcon
             */
            Model_Leaderboard lm = new Model_Leaderboard("IHasShoulders",4396,R.drawable.ic_default_user_profile_icon);
            array_list_leaderboard.add(lm);
        }

        //Passing data to list recycler view
        recycler_view_leaderboard = (RecyclerView) view_leaderboard.findViewById(R.id.recycler_view_ranking_leaderboard);
        recycler_view_leaderboard.setHasFixedSize(true);

        //Ensuring the recycler view layout contains 1 item in each row
        layout_manager_leaderboard = new StaggeredGridLayoutManager(1,1);
        recycler_view_leaderboard.setLayoutManager(layout_manager_leaderboard);

        //Setting up adapter
        rv_leaferbaord_adapter = new Adapter_Leaderboard(array_list_leaderboard, getContext(),this);
        recycler_view_leaderboard.setAdapter(rv_leaferbaord_adapter);
        return view_leaderboard;
    }

    @Override
    public void onItemClick(int position) {
        //Code intent here if we are to code an on click event for this
    }
}