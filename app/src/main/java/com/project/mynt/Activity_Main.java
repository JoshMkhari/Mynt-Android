package com.project.mynt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;


import android.os.Bundle;

import com.example.mynt.R;
import com.project.mynt.mainActivity.adapters.Adapter_HomeActFragment;
import com.project.mynt.userActivity.Model_User;

public class Activity_Main extends AppCompatActivity {

    //private TabLayout tabLayout_main;
    private ViewPager2 viewPager2_main;
    private Adapter_HomeActFragment fragmentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2_main = findViewById(R.id.main_act_viewPager2);
        Model_User user = new Model_User();

        //Comment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentAdapter = new Adapter_HomeActFragment(fragmentManager, getLifecycle(), user.getEmail());
        viewPager2_main.setAdapter((fragmentAdapter));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int page = extras.getInt("page");
            viewPager2_main.setCurrentItem(1);
            //The key argument here must match that used in the other activity
        }
        //Read Data from Database

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