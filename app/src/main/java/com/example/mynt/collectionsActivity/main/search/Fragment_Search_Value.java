package com.example.mynt.collectionsActivity.main.search;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mynt.collectionsActivity.interfaces.Interface_RecyclerView_One;
import com.example.mynt.R;
import com.example.mynt.collectionsActivity.main.Fragment_Search;
import com.example.mynt.collectionsActivity.main.search.adapters.Adapter_Search_Value;
import com.example.mynt.collectionsActivity.models.Model_Coin;
import com.example.mynt.dataAccessLayer.Model_Database_Lite;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Search_Value} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Search_Value extends Fragment implements Interface_RecyclerView_One {

    ArrayList<Model_Coin> coinWithoutDuplicates;
    public static ArrayList<Model_Coin> coinsList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View searchValue = inflater.inflate(R.layout.fragment_search_value, container, false);

        RecyclerView recyclerView = searchValue.findViewById(R.id.recyclerView_searchValue);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(1, 1);//(Professor Sluiter, 2020).
        recyclerView.setLayoutManager(layoutManager);

        //GEt all User Coins
        Model_Database_Lite mdl = new Model_Database_Lite();
        coinsList = mdl.allCoinsAndCollections(getContext(),0,0);

        coinWithoutDuplicates = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();
        for (Model_Coin coins: coinsList
        ) {
            if(!values.contains(coins.getValue()))
            {
                coinWithoutDuplicates.add(coins);
                values.add(coins.getValue());
            }
        }
        RecyclerView.Adapter<Adapter_Search_Value.Card_View_Holder> mAdapter = new Adapter_Search_Value( coinWithoutDuplicates, this, 1);//(Professor Sluiter, 2020).
        recyclerView.setAdapter(mAdapter);

        return searchValue;
    }

    @Override
    public void onItemClick(int position) {
        //Swipe to next page
        Fragment_Search.selectedCoinValue = coinWithoutDuplicates.get(position).getValue();
        Fragment_Search.changeTab(1);//https://stackoverflow.com/questions/45144785/android-studio-attempt-to-invoke-virtual-method-on-a-null-object-reference
        //https://stackoverflow.com/questions/27217362/calling-a-method-in-one-fragment-from-another
    }
}