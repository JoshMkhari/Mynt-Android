package com.example.mynt.collectionAct;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mynt.R;
import com.example.mynt.collectionAct.adapters.Collections_AllCoins_ListAdapter;
import com.example.mynt.collectionAct.models.coins_baselist_model;
import com.example.mynt.collectionAct.models.coins_list;
import com.example.mynt.collectionAct.models.coins_superlist_model;
import com.example.mynt.homeAct.adapters.Library_Options_ListAdapter;
import com.example.mynt.homeAct.models.Library_Options_Model;

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
        coinsListView = (ListView) allCoinsView.findViewById(R.id.all_coins_listview);

        ArrayList<coins_list> coinsList = new ArrayList<>();

        coinsList.add(new coins_list( R.drawable.app_logo,
                getResources().getString(R.string.library_option_coins),
                "21 April, 17:36",
                "South Africa",
                2020));

        coinsList.add(new coins_list( R.drawable.app_logo,
                getResources().getString(R.string.library_option_coins),
                "21 April, 09:15",
                "South Africa",
                1994));

        ArrayList<coins_list> coinsList2 = new ArrayList<>();

        coinsList2.add(new coins_list( R.drawable.app_logo,
                getResources().getString(R.string.library_option_coins),
                "20 April, 09:10",
                "South Africa",
                1985));

        coinsList2.add(new coins_list( R.drawable.app_logo,
                getResources().getString(R.string.library_option_coins),
                "20 April, 09:10",
                "South Africa",
                2004));

        ArrayList<coins_baselist_model> coinsBaseList = new ArrayList<>();
        coinsBaseList.add(new coins_baselist_model(coinsList,"Today"));
        coinsBaseList.add(new coins_baselist_model(coinsList2,"Yesterday"));

        ArrayList<coins_superlist_model> coinsSuperList = new ArrayList<>();
        coinsSuperList.add(new coins_superlist_model(coinsBaseList));

        Collections_AllCoins_ListAdapter coinsListAdapter = new Collections_AllCoins_ListAdapter(getContext(),coinsSuperList);
        coinsListView.setAdapter(coinsListAdapter);

        return allCoinsView;
    }
}