package com.example.mynt.collectionsActivity;

import android.os.Bundle;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mynt.R;
import com.example.mynt.Interface_RecyclerView;
import com.example.mynt.collectionsActivity.adapters.Adapter_Leaderboard;
import com.example.mynt.collectionsActivity.models.Model_Leaderboard;
import com.example.mynt.collectionsActivity.models.Model_User_Data;
import com.example.mynt.dataAccessLayer.Database_Lite;
import com.example.mynt.dataAccessLayer.Model_Database_Lite;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment_Leaderboard extends Fragment implements Interface_RecyclerView {
    private ArrayList<Model_Leaderboard> array_list_leaderboard;
    RecyclerView recycler_view_leaderboard;
    RecyclerView.Adapter<Adapter_Leaderboard.Card_View_Holder> rv_leaferbaord_adapter;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    @Override
    public void onResume() {
        super.onResume();
        RecyclerView.Adapter<Adapter_Leaderboard.Card_View_Holder> rv_leaferbaord_adapter = new Adapter_Leaderboard(array_list_leaderboard);//(Professor Sluiter, 2020).
        recycler_view_leaderboard.setAdapter(rv_leaferbaord_adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view_leaderboard = inflater.inflate(R.layout.fragment_leaderboard, container, false);

        //Passing data to list recycler view
        recycler_view_leaderboard= view_leaderboard.findViewById(R.id.recycler_view_ranking_leaderboard);
        recycler_view_leaderboard.setHasFixedSize(true);

        //Ensuring the recycler view layout contains 1 item in each row
        RecyclerView.LayoutManager layout_manager_leaderboard = new StaggeredGridLayoutManager(1, 1);//(Professor Sluiter, 2020).
        recycler_view_leaderboard.setLayoutManager(layout_manager_leaderboard);

        DisplayLeaderBoardRanks();
        //Setting up adapter
        rv_leaferbaord_adapter = new Adapter_Leaderboard(array_list_leaderboard);//(Professor Sluiter, 2020).
        recycler_view_leaderboard.setAdapter(rv_leaferbaord_adapter);



        return view_leaderboard;
    }

    private void DisplayLeaderBoardRanks(){
        Database_Lite db = new Database_Lite(getContext());
        //Creating list to store users and their ranks
        array_list_leaderboard= db.getLeaderboard();
        Log.d("offLEade", "DisplayLeaderBoardRanks: arraylist" + array_list_leaderboard.size() );
    }
    @Override
    public void onItemClick(int position, ImageView coinImage) {
        //Code intent here if we are to code an on click event for this
    }
}