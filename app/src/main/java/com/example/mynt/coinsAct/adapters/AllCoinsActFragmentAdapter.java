package com.example.mynt.coinsAct.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mynt.coinsAct.AllCoinsFragment;

public class AllCoinsActFragmentAdapter extends FragmentStateAdapter {

    public AllCoinsActFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
            return new AllCoinsFragment();
    }

    @Override
    public int getItemCount() {
        return 1;
    }

}
