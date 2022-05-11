package com.example.mynt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.mynt.collectionAct.adapters.CollectionActFragmentAdapter;
import com.example.mynt.homeAct.adapters.HomeActFragmentAdapter;

public class CollectionsActivity extends AppCompatActivity {

    private ViewPager2 viewPager2_collections;
    private CollectionActFragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collections);

        viewPager2_collections = findViewById(R.id.collectionsAct_viewPager2);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentAdapter = new CollectionActFragmentAdapter(fragmentManager,getLifecycle());
        viewPager2_collections.setAdapter((fragmentAdapter));
    }
}