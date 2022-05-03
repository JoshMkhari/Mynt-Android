package com.example.mynt.mainact;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mynt.R;

public class MainActFragmentAdapter extends FragmentStateAdapter {

    public MainActFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
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