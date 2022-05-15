package com.example.mynt.coinsAct;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mynt.R;
import com.example.mynt.coinsAct.adapters.AllCoins_ListAdapter;
import com.example.mynt.coinsAct.models.coins_list_model;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllCoinsFragment#} factory method to
 * create an instance of this fragment.
 */
public class AllCoinsFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ListView coinsListView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View allCoinsView = inflater.inflate(R.layout.fragment_all_coins_, container, false);
        //coinsListView = (ListView) allCoinsView.findViewById(R.id.all_coins_listview);

        TextView pageTitle_textView = allCoinsView.findViewById(R.id.CoinsPageTitle);
        TextView collectionName_textView = allCoinsView.findViewById(R.id.coinsPageBlockTitle);

        pageTitle_textView.setText("Coins test");
        collectionName_textView.setText("BLock Test");

        ArrayList<coins_list_model> coinsList = new ArrayList<>();

        coinsList.add(new coins_list_model( R.drawable.app_logo,
                getResources().getString(R.string.library_option_coins),
                "21 April, 17:36",
                "South Africa",
                2020));

        coinsList.add(new coins_list_model( R.drawable.app_logo,
                getResources().getString(R.string.library_option_coins),
                "21 April, 09:15",
                "South Africa",
                1994));

        coinsList.add(new coins_list_model( R.drawable.app_logo,
                getResources().getString(R.string.library_option_coins),
                "20 April, 09:10",
                "South Africa",
                1985));

        coinsList.add(new coins_list_model( R.drawable.app_logo,
                getResources().getString(R.string.library_option_coins),
                "20 April, 09:10",
                "South Africa",
                2004));


        recyclerView = (RecyclerView) allCoinsView.findViewById(R.id.all_coins_list);
        recyclerView.setHasFixedSize(true);

        layoutManager = new StaggeredGridLayoutManager(1,1);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new AllCoins_ListAdapter(coinsList, getContext());
        recyclerView.setAdapter(mAdapter);


        //ListView myList = new ListView(allCoinsView.getContext());
        /*

        for (int i = 0; i < coinsBaseList.size(); i++) {
            coinDate.setText(coinsBaseList.get(i).getDate());
            myLayout.addView(coinDate);
            Collections_AllCoins_ListAdapter coinsListAdapter = new Collections_AllCoins_ListAdapter(getContext(),coinsBaseList.get(i).getCoins());
            for (int s = 0; coinsBaseList.get(i).getCoins().size() < 5; s++) {
                //myList.addView(coinsListAdapter.getView(s,null,myLayout));
                myLayout.addView(coinsListAdapter.getView(s,null,myLayout));
            }
        }
        */

        return allCoinsView;
    }
}