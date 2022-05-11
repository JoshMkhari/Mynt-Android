package com.example.mynt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.mynt.homeAct.adapters.HomeActFragmentAdapter;

public class CollectionsActivity extends AppCompatActivity {

    private ViewPager2 viewPager2_main;
    private HomeActFragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collections);
    }
}