package com.example.mynt.collectionsActivity.main.search;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mynt.Interface_RecyclerView_One;
import com.example.mynt.R;
import com.example.mynt.collectionsActivity.main.Fragment_Search;
import com.example.mynt.collectionsActivity.main.search.adapters.Adapter_Search_Value;
import com.example.mynt.collectionsActivity.models.Model_Coin;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Search_Coins} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Search_Coins extends Fragment implements Interface_RecyclerView_One {
    ArrayList<Model_Coin> resultCoinsLIst;
    RecyclerView recyclerView;

    @Override
    public void onResume() {
        super.onResume();
        setAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View searchCoins = inflater.inflate(R.layout.fragment_search_coins, container, false);

        recyclerView = searchCoins.findViewById(R.id.recyclerView_searchCoin);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(1, 1);//(Professor Sluiter, 2020).
        recyclerView.setLayoutManager(layoutManager);
        setAdapter();
        return searchCoins;
    }

    @Override
    public void onItemClick(int position) {
        Log.d("theClick", "onItemClick: " + resultCoinsLIst.get(position).getYear());
        Fragment_Search.selectedCoinYear = resultCoinsLIst.get(position).getYear();
        Fragment_Search.changeTab(2);//https://stackoverflow.com/questions/45144785/android-studio-attempt-to-invoke-virtual-method-on-a-null-object-reference
        //https://stackoverflow.com/questions/27217362/calling-a-method-in-one-fragment-from-another
    }

    private void setAdapter()
    {
        resultCoinsLIst = new ArrayList<>();
        for (Model_Coin coin:Fragment_Search_Year.selectedCoinsLIst
        ) {
            if(coin.getYear() == Fragment_Search.selectedCoinYear)
            {
                resultCoinsLIst.add(coin);
            }
        }
        RecyclerView.Adapter<Adapter_Search_Value.Card_View_Holder> mAdapter = new Adapter_Search_Value(resultCoinsLIst, this, 3);//(Professor Sluiter, 2020).
        recyclerView.setAdapter(mAdapter);
    }
}