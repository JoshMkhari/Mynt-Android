package com.example.mynt.collectionsActivity.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mynt.collectionsActivity.Fragment_Leaderboard;
import com.example.mynt.collectionsActivity.Fragment_Library;
import com.example.mynt.collectionsActivity.Fragment_Main;

public class Adapter_HomeActFragment extends FragmentStateAdapter {

    private String email;
    private Bundle bundle = new Bundle();
    public Adapter_HomeActFragment(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, String email) {
        super(fragmentManager, lifecycle);
        this.email = email;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        bundle.putString("userEmail",email);
        if (position == 1){

            Fragment_Main home = new Fragment_Main();
            home.setArguments(bundle);
            return home;
        }else
            if (position == 2){
                Fragment_Leaderboard leaderboard = new Fragment_Leaderboard();
                leaderboard.setArguments(bundle);
                return leaderboard;
            }
            else
            {
                Fragment_Library library = new Fragment_Library();
                library.setArguments(bundle);
                return library;
            }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}