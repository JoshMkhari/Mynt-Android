package com.example.mynt.collectionAct.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mynt.collectionAct.AllCoinsFragment;
import com.example.mynt.collectionAct.AllCollectionsFragment;
import com.example.mynt.collectionAct.CurrentCollectionFragment;
import com.example.mynt.homeAct.LeaderboardFragment;
import com.example.mynt.homeAct.LibraryFragment;
import com.example.mynt.homeAct.MainFragment;

public class CollectionActFragmentAdapter extends FragmentStateAdapter {

    public CollectionActFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1){
            return new AllCoinsFragment();
        }else
        if (position == 2){
            return new AllCollectionsFragment();
        }
        else
            return new CurrentCollectionFragment();


    }

    @Override
    public int getItemCount() {
        return 3;
    }

}
