package com.example.mynt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;


import android.os.Bundle;

import com.example.mynt.homeAct.HomeActFragmentAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout_main;
    private ViewPager2 viewPager2_main;
    private HomeActFragmentAdapter fragmentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Comment
        //tabLayout_main = findViewById(R.id.main_act_tabLayout);
        viewPager2_main = findViewById(R.id.main_act_viewPager2);

        //Comment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentAdapter = new HomeActFragmentAdapter(fragmentManager, getLifecycle());
        viewPager2_main.setAdapter((fragmentAdapter));

        //Adding Tabs
        //tabLayout_main.addTab((tabLayout_main.newTab().setText("")));
        //tabLayout_main.addTab((tabLayout_main.newTab().setText("")));
        //tabLayout_main.addTab((tabLayout_main.newTab().setText("")));

        //tabLayout_main.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           // @Override
            //public void onTabSelected(TabLayout.Tab tab) {
            //    viewPager2_main.setCurrentItem(tab.getPosition());
           // }

           // @Override
           // public void onTabUnselected(TabLayout.Tab tab) {

           // }

            //@Override
           // public void onTabReselected(TabLayout.Tab tab) {

           // }
       // });
    }
}