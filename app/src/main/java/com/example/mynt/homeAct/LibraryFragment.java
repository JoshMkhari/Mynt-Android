package com.example.mynt.homeAct;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mynt.coinsAct.CoinAdapter;
import com.example.mynt.Coin_Model;
import com.example.mynt.coinsAct.CoinsActivity;
import com.example.mynt.R;
import com.example.mynt.collectionsAct.CollectionsActivity;
import com.example.mynt.homeAct.adapters.Library_Options_ListAdapter;
import com.example.mynt.homeAct.models.Library_Options_Model;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LibraryFragment#} factory method to
 * create an instance of this fragment.
 */
public class LibraryFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ListView optionListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View libraryView = inflater.inflate(R.layout.fragment_library, container, false);
        optionListView = (ListView) libraryView.findViewById(R.id.libraryOptionsListView);

        ArrayList<Library_Options_Model> libraryOptionsList = new ArrayList<>();

        libraryOptionsList.add(new Library_Options_Model( R.drawable.app_logo,
                getResources().getString(R.string.library_option_coins),
                0,
                50));

        libraryOptionsList.add(new Library_Options_Model( R.drawable.collection_icon,
                getResources().getString(R.string.library_option_collections),
                0,
                0));

        libraryOptionsList.add(new Library_Options_Model( R.drawable.goal_icon,
                getResources().getString(R.string.library_option_goals),
                62,
                62));
        Library_Options_ListAdapter optionsListAdapter = new Library_Options_ListAdapter(getContext(),libraryOptionsList);

        ArrayList<Coin_Model> libraryCoinsList = new ArrayList<>();
        libraryCoinsList.add(new Coin_Model(R.drawable.two_rand,"Two Rand",2020));
        libraryCoinsList.add(new Coin_Model(R.drawable.two_rand,"Two Rand",2020));
        libraryCoinsList.add(new Coin_Model(R.drawable.two_rand,"Two Rand",2020));
        libraryCoinsList.add(new Coin_Model(R.drawable.two_rand,"Two Rand",2020));

        recyclerView = (RecyclerView) libraryView.findViewById(R.id.recentCoins_list);
        //recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);

        layoutManager = new StaggeredGridLayoutManager(2,1);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new CoinAdapter(libraryCoinsList, getContext());
        recyclerView.setAdapter(mAdapter);


        optionListView.setAdapter(optionsListAdapter);


        optionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView parent, View v, int position, long id){


                switch(position)
                {
                    case 0:
                        Intent i = new Intent(getContext(), CoinsActivity.class);
                        startActivity(i);
                    case 1:
                        Intent s = new Intent(getContext(), CollectionsActivity.class);
                        startActivity(s);
                }

            }
        });

        return libraryView;
    }

}