package com.example.mynt.collectionsActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.mynt.Activity_Main;
import com.example.mynt.R;
import com.example.mynt.collectionsActivity.adapters.Adapter_HomeActFragment;
import com.example.mynt.collectionsActivity.models.Model_User;
import com.example.mynt.dataAccessLayer.Database_Lite;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class Fragment_home_main extends Fragment {
    Model_User user = new Model_User();
    private LinearLayout layoutIndicators;
    private Database_Lite db;
    private Adapter_HomeActFragment fragmentAdapter;
    View home;
    //23:54
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        home = inflater.inflate(R.layout.fragment_home, container, false);
        //private TabLayout tabLayout_main;
        ViewPager2 viewPager2_main = home.findViewById(R.id.main_act_viewPager2);
        //layoutIndicators = home.findViewById(R.id.linearLayoutViewPagerIndicators);

        assert getArguments() != null;
        int currentPage = getArguments().getInt("StartPage");
        db  = new Database_Lite(getContext());

        //assert getArguments() != null;
        //String testuser = getArguments().getString("User");
        //Toast.makeText(getContext(), testuser, Toast.LENGTH_SHORT).show();

        ArrayList<Model_User> users = db.getAllUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getState() == 1) {
                user = users.get(i);
            }
        }
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentAdapter = new Adapter_HomeActFragment(fragmentManager, getLifecycle(), user.getUserID());
        viewPager2_main.setAdapter((fragmentAdapter));

        setupIndicators();
        TabLayout tabLayout = home.findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager2_main,
                (tab, position) -> tab.setText("")
        ).attach();

        //Comment
        return home;
    }


}