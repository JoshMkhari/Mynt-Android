package com.example.mynt.collectionsAct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.mynt.R;
import com.example.mynt.collectionsAct.Adapters.tempAdapter;
import com.example.mynt.homeAct.adapters.HomeActFragmentAdapter;

public class tempActivity extends AppCompatActivity {

    ViewPager2 tempPager;
    private tempAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        tempPager = findViewById(R.id.tempViewPager);

        //Comment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentAdapter = new tempAdapter(fragmentManager, getLifecycle());
        tempPager.setAdapter((fragmentAdapter));
    }
}