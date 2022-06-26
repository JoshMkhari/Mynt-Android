package com.example.mynt.collectionsActivity.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mynt.R;
import com.example.mynt.collectionsActivity.adapters.Adapter_HomeActFragment;
import com.example.mynt.collectionsActivity.main.search.Adapter_Search;
import com.google.android.material.tabs.TabLayout;

public class Fragment_Search extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View search = inflater.inflate(R.layout.fragment_search, container, false);

        //Comment
        tabLayout = search.findViewById(R.id.search_tab_layout);
        viewPager2 = search.findViewById(R.id.search_viewPager2);

        //Comment
        FragmentManager fragmentManager = getParentFragmentManager();
        Adapter_Search fragmentAdapter = new Adapter_Search(fragmentManager, getLifecycle());
        viewPager2.setAdapter((fragmentAdapter));

        //Adding Tabs
        tabLayout.addTab((tabLayout.newTab().setText("Value")));
        tabLayout.addTab((tabLayout.newTab().setText("Year")));
        tabLayout.addTab((tabLayout.newTab().setText("Coin")));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
         @Override
        public void onTabSelected(TabLayout.Tab tab) {
             viewPager2.setCurrentItem(tab.getPosition());
         }

         @Override
         public void onTabUnselected(TabLayout.Tab tab) {

         }

        @Override
         public void onTabReselected(TabLayout.Tab tab) {

        }
        });

        return search;
    }
}