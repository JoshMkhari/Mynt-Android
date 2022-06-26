package com.example.mynt.collectionsActivity.main.search;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mynt.Interface_RecyclerView_One;
import com.example.mynt.R;
import com.example.mynt.collectionsActivity.adapters.Adapter_Coins;
import com.example.mynt.collectionsActivity.main.search.adapters.Adapter_Search_Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Search_Value} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Search_Value extends Fragment implements Interface_RecyclerView_One {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View searchValue = inflater.inflate(R.layout.fragment_search_value, container, false);
        RecyclerView recyclerView = searchValue.findViewById(R.id.recyclerView_searchValue);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(1, 1);//(Professor Sluiter, 2020).
        recyclerView.setLayoutManager(layoutManager);

        List<String> values = Arrays.asList(getResources().getStringArray(R.array.Value));
        RecyclerView.Adapter<Adapter_Search_Value.Card_View_Holder> mAdapter = new Adapter_Search_Value( values, this);//(Professor Sluiter, 2020).
        recyclerView.setAdapter(mAdapter);

        return searchValue;
    }

    @Override
    public void onItemClick(int position) {
        //Swipe to next page
    }
}