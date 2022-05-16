package com.example.mynt.collectionsActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.example.mynt.R;

import java.util.ArrayList;

public class Activity_Collections extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collections);

        ArrayList<Model_Collections> collectionsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            collectionsList.add(new Model_Collections("20th Century",9,55,R.drawable.img_two_rand));
        }

        recyclerView = (RecyclerView) findViewById(R.id.all_collectionsList);
        //recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);

        layoutManager = new StaggeredGridLayoutManager(1,1);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new Adapter_Collections(collectionsList, this);
        recyclerView.setAdapter(mAdapter);
    }
}