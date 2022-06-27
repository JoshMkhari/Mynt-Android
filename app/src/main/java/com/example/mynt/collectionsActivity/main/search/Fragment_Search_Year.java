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
 * Use the {@link Fragment_Search_Year} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Search_Year extends Fragment implements Interface_RecyclerView_One {

    public static ArrayList<Model_Coin> selectedCoinsLIst;
    private RecyclerView recyclerView;

    @Override
    public void onResume() {
        super.onResume();
        setAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View searchYear = inflater.inflate(R.layout.fragment_search_year, container, false);

        recyclerView = searchYear.findViewById(R.id.recyclerView_searchYear);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, 1);//(Professor Sluiter, 2020).
        recyclerView.setLayoutManager(layoutManager);
        setAdapter();


        //Populate adapter and stuff
        return searchYear;
    }

    @Override
    public void onItemClick(int position) {
        Log.d("theClick", "onItemClick: " + selectedCoinsLIst.get(position).getYear());
        Fragment_Search.selectedCoinYear = selectedCoinsLIst.get(position).getYear();
        Fragment_Search.changeTab(2);//https://stackoverflow.com/questions/45144785/android-studio-attempt-to-invoke-virtual-method-on-a-null-object-reference
        //https://stackoverflow.com/questions/27217362/calling-a-method-in-one-fragment-from-another
    }

    private void setAdapter()
    {
        selectedCoinsLIst = new ArrayList<>();
        ArrayList<Model_Coin> displayCoinList = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();
        for (Model_Coin coin:Fragment_Search_Value.coinsList
        ) {
            if(coin.getValue().equals(Fragment_Search.selectedCoinValue))
            {
                if(!values.contains(String.valueOf(coin.getYear())))
                {
                    displayCoinList.add(coin);
                    values.add(String.valueOf(coin.getYear()));
                }
                selectedCoinsLIst.add(coin);
            }
        }
        RecyclerView.Adapter<Adapter_Search_Value.Card_View_Holder> mAdapter = new Adapter_Search_Value(displayCoinList, this, 2);//(Professor Sluiter, 2020).
        recyclerView.setAdapter(mAdapter);
    }
}