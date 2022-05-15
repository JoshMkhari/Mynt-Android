package com.example.mynt.collectionsAct.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mynt.collectionsAct.GoalFragment;


public class tempAdapter extends FragmentStateAdapter {

    public tempAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new GoalFragment();

    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
