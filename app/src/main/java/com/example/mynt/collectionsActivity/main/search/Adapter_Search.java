package com.example.mynt.collectionsActivity.main.search;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mynt.collectionsActivity.Fragment_Leaderboard;
import com.example.mynt.collectionsActivity.Fragment_Library;
import com.example.mynt.collectionsActivity.Fragment_Main;

import java.util.ArrayList;

public class Adapter_Search extends FragmentStateAdapter {

    public Adapter_Search(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        if (position == 0){

            return new Fragment_Search_Value();
        }else
        if (position == 1){
            return new Fragment_Search_Year();
        }

        else
        {
            return new Fragment_Search_Coins();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }


}
