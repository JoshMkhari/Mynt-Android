package com.example.mynt.collectionsActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mynt.R;
import com.example.mynt.RecyclerViewInterface;
import com.example.mynt.collectionsActivity.adapters.Adapter_Coins;
import com.example.mynt.collectionsActivity.models.Model_Coin;
import com.example.mynt.collectionsActivity.models.Model_Collections;
import com.example.mynt.collectionsActivity.models.Model_User;
import com.example.mynt.dataAccessLayer.Database_Lite;

import java.util.ArrayList;

public class Fragment_Coins extends Fragment implements RecyclerViewInterface {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ImageButton back_imageButton;
    private View coinsView;
    private int collectionID,task;
    private ArrayList<Integer> coinIDs;
    private TextView pageTitle_textView;
    private TextView collectionName_textView;
    private String blockTitle;
    private Model_User model_user;
    private Database_Lite db;
    private ArrayList<Model_Coin> coinsList;
    private ArrayList<Model_Coin> dbCoins;
    private ArrayList<Integer> userCollectionIDs;
    private ArrayList<Model_Collections> allCollections;
    private ArrayList<Model_Collections> allUserCollections;
    private ArrayList<Integer> collectionCoins;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        coinsView = inflater.inflate(R.layout.fragment_coins, container, false);


        pageTitle_textView = coinsView.findViewById(R.id.textview_title_coins);
        collectionName_textView = coinsView.findViewById(R.id.textview_blockTitle_coins);
        back_imageButton = coinsView.findViewById(R.id.image_button_back_coins);

        ReturnToCoinPage();
        DisplayAllLocalCoinsAndCollections();

        assert getArguments() != null;
        task = getArguments().getInt("Task");
        blockTitle = getArguments().getString("Collection Name");
        model_user = new Model_User();
        model_user.setUserID(getArguments().getInt("User"));
        collectionID = getArguments().getInt("CollectionID");



        recyclerView = (RecyclerView) coinsView.findViewById(R.id.recyclerView_coins);
        recyclerView.setHasFixedSize(true);

        layoutManager = new StaggeredGridLayoutManager(1,1);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new Adapter_Coins(coinsList, getContext(),this);
        recyclerView.setAdapter(mAdapter);



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


        return coinsView;
    }

    private void ReturnToCoinPage(){

        back_imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getArguments() != null;
                int task = getArguments().getInt("Task");
                if(task==1)// Fragment was accessed from somewhere else
                {
                    Navigation.findNavController(coinsView).navigateUp();
                }else
                {
                    Intent home = new Intent(getContext(),Activity_Collections.class);
                    //home.putExtra("View","library");
                    home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(home);
                }
            }
        });

    }
    private void DisplayAllLocalCoinsAndCollections(){

        db = new Database_Lite(getContext());

        coinsList = new ArrayList<>();


        dbCoins = db.getAllCoins();
        //get all collections for a user
        //get all collectionCoins
        //then match correct coins

        userCollectionIDs = db.getAllCollectionsForUser(model_user);
        allCollections = db.getAllCollections();

        allUserCollections = new ArrayList<>();

        for (int i=0; i<allCollections.size(); i++)
        {
            if(userCollectionIDs.contains(allCollections.get(i).getCollectionID()))
                allUserCollections.add(allCollections.get(i));
        }

        coinIDs = new ArrayList<>();
        if(task == 0 || task ==2) //All Coins
        {
            pageTitle_textView.setText(R.string.coins_title);
            collectionName_textView.setText(R.string.all_coins_block_title);
            ;

            for (int b=0; b<allUserCollections.size(); b++) {
                collectionCoins = db.getAllCoinsInCollection(allUserCollections.get(b).getCollectionID());
                for (int i = 0; i < collectionCoins.size(); i++) {
                    for (int s = 0; s < dbCoins.size(); s++) {
                        if (collectionCoins.get(i) == dbCoins.get(s).getCoinID()) {
                            coinsList.add(dbCoins.get(s));
                            //add final list here
                            coinIDs.add(dbCoins.get(s).getCoinID());
                            break;
                        }
                    }
                }
            }
        }else//For specific collection
        {
            pageTitle_textView.setText(R.string.collections_title);
            collectionName_textView.setText(blockTitle);

            collectionCoins = db.getAllCoinsInCollection(collectionID);
            for (int i=0; i<collectionCoins.size(); i++)
            {
                for (int s=0; s<dbCoins.size(); s++) {
                    if(collectionCoins.get(i)==dbCoins.get(s).getCoinID())
                    {
                        coinsList.add(dbCoins.get(s));
                        //add final list here
                        coinIDs.add(dbCoins.get(s).getCoinID());
                        break;
                    }
                }
            }
        }




    }

    //implementing RecyclerViewInterface
    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("Task", task);
        bundle.putInt("CoinID", coinIDs.get(position));
        Navigation.findNavController(coinsView).navigate(R.id.action_fragment_Coins_to_fragment_Coin_Details,bundle);
    }
}