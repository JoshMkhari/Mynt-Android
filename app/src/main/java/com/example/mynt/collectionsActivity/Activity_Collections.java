package com.example.mynt.collectionsActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.mynt.CoinDetailsActivity;
import com.example.mynt.R;
import com.example.mynt.RecyclerViewInterface;
import com.example.mynt.coinsActivity.Activity_Coins;
import com.example.mynt.goalsActivity.Activity_Goals;

import java.util.ArrayList;

public class Activity_Collections extends AppCompatActivity implements RecyclerViewInterface {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ImageButton createCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collections);
        createCollection = findViewById(R.id.imageview_blockTitle_collections);

        ArrayList<Model_Collections> collectionsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            collectionsList.add(new Model_Collections("20th Century",9,55,R.drawable.img_two_rand));
        }


        recyclerView = (RecyclerView) findViewById(R.id.all_collectionsList);
        //recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);

        layoutManager = new StaggeredGridLayoutManager(1,1);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new Adapter_Collections(collectionsList, this, this);
        recyclerView.setAdapter(mAdapter);

        createCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Activity_Goals.class);
                startActivity(i);
            }
        });
    }

    //implementing RecyclerViewInterface
    @Override
    public void onItemClick(int position) {
        Intent i = new Intent(getApplicationContext(), Activity_Coins.class);
        startActivity(i);
    }
}