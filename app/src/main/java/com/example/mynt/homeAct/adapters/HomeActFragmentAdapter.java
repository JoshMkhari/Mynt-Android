package com.example.mynt.homeAct.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mynt.homeAct.LeaderboardFragment;
import com.example.mynt.homeAct.LibraryFragment;
import com.example.mynt.homeAct.MainFragment;

public class HomeActFragmentAdapter extends FragmentStateAdapter {

    public HomeActFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1){
            return new MainFragment();
        }else
            if (position == 2){
            return new LeaderboardFragment();
            }
            else
                return new LibraryFragment();


    }

    @Override
    public int getItemCount() {
        return 3;
    }
}