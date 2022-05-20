package com.example.mynt.coinsActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mynt.R;
import com.example.mynt.RecyclerViewInterface;
import com.example.mynt.coinsActivity.adapters.Adapter_Coins;
import com.example.mynt.coinsActivity.models.Model_Coins_List;

import java.util.ArrayList;

public class Activity_Coins extends AppCompatActivity implements RecyclerViewInterface {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ImageButton back_imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coins);

        TextView pageTitle_textView = findViewById(R.id.textview_title_coins);
        TextView collectionName_textView = findViewById(R.id.textview_blockTitle_coins);
        back_imageButton = findViewById(R.id.image_button_back_coins);


        pageTitle_textView.setText("Coins test");
        collectionName_textView.setText("BLock Test");

        ArrayList<Model_Coins_List> coinsList = new ArrayList<>();

        coinsList.add(new Model_Coins_List( R.drawable.img_app_logo,
                getResources().getString(R.string.library_option_coins),
                "21 April, 17:36",
                "South Africa",
                2020));

        coinsList.add(new Model_Coins_List( R.drawable.img_app_logo,
                getResources().getString(R.string.library_option_coins),
                "21 April, 09:15",
                "South Africa",
                1994));

        coinsList.add(new Model_Coins_List( R.drawable.img_app_logo,
                getResources().getString(R.string.library_option_coins),
                "20 April, 09:10",
                "South Africa",
                1985));

        coinsList.add(new Model_Coins_List( R.drawable.img_app_logo,
                getResources().getString(R.string.library_option_coins),
                "20 April, 09:10",
                "South Africa",
                2004));

        for (int i = 0; i < 4; i++) {
            coinsList.add(new Model_Coins_List( R.drawable.img_app_logo,
                    getResources().getString(R.string.library_option_coins),
                    "20 April, 09:10",
                    "South Africa",
                    2004));
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_coins);
        recyclerView.setHasFixedSize(true);

        layoutManager = new StaggeredGridLayoutManager(1,1);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new Adapter_Coins(coinsList, this,this);
        recyclerView.setAdapter(mAdapter);

        back_imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

        //ListView myList = new ListView(allCoinsView.getContext());
        /*

        for (int i = 0; i < coinsBaseList.size(); i++) {
            coinDate.setText(coinsBaseList.get(i).getDate());
            myLayout.addView(coinDate);
            Collections_AllCoins_ListAdapter coinsListAdapter = new Collections_AllCoins_ListAdapter(getContext(),coinsBaseList.get(i).getCoins());
            for (int s = 0; coinsBaseList.get(i).getCoins().size() < 5; s++) {
                //myList.addView(coinsListAdapter.getView(s,null,myLayout));
                myLayout.addView(coinsListAdapter.getView(s,null,myLayout));
            }
        }
        */


    }
    //implementing RecyclerViewInterface
    @Override
    public void onItemClick(int position) {
        Intent i = new Intent(getApplicationContext(), Activity_CoinDetails.class);
        startActivity(i);
    }
}