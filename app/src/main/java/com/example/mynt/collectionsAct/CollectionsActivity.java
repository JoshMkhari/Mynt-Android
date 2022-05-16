package com.example.mynt.collectionsAct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.example.mynt.R;

import java.util.ArrayList;

public class CollectionsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collections);

        ArrayList<CollectionsModel> collectionsList = new ArrayList<>();
        collectionsList.add(new CollectionsModel("20th Century",9,55,R.drawable.img_two_rand));
        collectionsList.add(new CollectionsModel("20th Century",9,55,R.drawable.img_two_rand));
        collectionsList.add(new CollectionsModel("20th Century",9,55,R.drawable.img_two_rand));
        collectionsList.add(new CollectionsModel("20th Century",9,55,R.drawable.img_two_rand));
        collectionsList.add(new CollectionsModel("20th Century",9,55,R.drawable.img_two_rand));
        collectionsList.add(new CollectionsModel("20th Century",9,55,R.drawable.img_two_rand));
        collectionsList.add(new CollectionsModel("20th Century",9,55,R.drawable.img_two_rand));
        collectionsList.add(new CollectionsModel("20th Century",9,55,R.drawable.img_two_rand));
        collectionsList.add(new CollectionsModel("20th Century",9,55,R.drawable.img_two_rand));

        recyclerView = (RecyclerView) findViewById(R.id.all_collectionsList);
        //recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);

        layoutManager = new StaggeredGridLayoutManager(1,1);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new CollectionsAdapter(collectionsList, this);
        recyclerView.setAdapter(mAdapter);
    }
}