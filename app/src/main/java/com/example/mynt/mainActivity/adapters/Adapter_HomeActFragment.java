package com.example.mynt.mainActivity.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mynt.mainActivity.Fragment_Leaderboard;
import com.example.mynt.mainActivity.Fragment_Library;
import com.example.mynt.mainActivity.Fragment_Home;

public class Adapter_HomeActFragment extends FragmentStateAdapter {

    public Adapter_HomeActFragment(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1){
            return new Fragment_Home();
        }else
            if (position == 2){
            return new Fragment_Leaderboard();
            }
            else
                return new Fragment_Library();

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}