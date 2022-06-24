package com.example.mynt.collectionsActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mynt.R;
import com.example.mynt.collectionsActivity.adapters.Adapter_HomeActFragment;
import com.example.mynt.collectionsActivity.models.Model_User_Data;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator; //(tommybuonomo,2022)


/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class Fragment_ViewPager extends Fragment {
    //(Section, 2021)
    //23:54
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View home = inflater.inflate(R.layout.fragment_view_pager, container, false);
        //private TabLayout tabLayout_main;
        ViewPager2 viewPager2_main = home.findViewById(R.id.main_act_viewPager2);
        //layoutIndicators = home.findViewById(R.id.linearLayoutViewPagerIndicators);

        assert getArguments() != null;
        int currentPage = getArguments().getInt("StartPage");



        FragmentManager fragmentManager = getParentFragmentManager();
        Adapter_HomeActFragment fragmentAdapter = new Adapter_HomeActFragment(fragmentManager, getLifecycle(), Model_User_Data.currentUser.getUserID());//(Foxandroid,2021)
        viewPager2_main.setAdapter((fragmentAdapter));//(Foxandroid,2021)

        SpringDotsIndicator springDotsIndicator = (SpringDotsIndicator) home.findViewById(R.id.spring_dots_indicator);//(tommybuonomo,2022)
        springDotsIndicator.setViewPager2(viewPager2_main);

        //Comment
        return home;
    }


}