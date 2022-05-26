package com.example.mynt.collectionsActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mynt.Interface_Back;
import com.example.mynt.R;
import com.example.mynt.Interface_RecyclerView;
import com.example.mynt.collectionsActivity.adapters.Adapter_Coins;
import com.example.mynt.collectionsActivity.models.Model_Coin;
import com.example.mynt.collectionsActivity.models.Model_Collections;
import com.example.mynt.collectionsActivity.models.Model_User;
import com.example.mynt.dataAccessLayer.Database_Lite;

import java.util.ArrayList;

public class Fragment_Coins extends Fragment implements Interface_RecyclerView, Interface_Back {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ImageButton back_imageButton;
    private View coinsView;
    private int collectionID,task;
    private ArrayList<Integer> coinIDs;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        coinsView = inflater.inflate(R.layout.fragment_coins, container, false);


        TextView pageTitle_textView = coinsView.findViewById(R.id.textview_title_coins);
        TextView collectionName_textView = coinsView.findViewById(R.id.textview_blockTitle_coins);
        back_imageButton = coinsView.findViewById(R.id.image_button_back_coins);

        assert getArguments() != null;
        task = getArguments().getInt("Task");
        String blockTitle = getArguments().getString("Collection Name");
        Model_User model_user = new Model_User();
        model_user.setUserID(getArguments().getInt("User"));
        collectionID = getArguments().getInt("CollectionID");

        Database_Lite db = new Database_Lite(getContext());

        ArrayList<Model_Coin> coinsList = new ArrayList<>();

        ArrayList<Model_Coin> dbCoins;
        dbCoins = db.getAllCoins();
        //get all collections for a user
        //get all collectionCoins
        //then match correct coins

        ArrayList<Integer> userCollectionIDs = db.getAllCollectionsForUser(model_user);
        ArrayList<Model_Collections> allCollections = db.getAllCollections();

        ArrayList<Model_Collections> allUserCollections = new ArrayList<>();

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
            ArrayList<Integer> collectionCoins;

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
            ArrayList<Integer> collectionCoins;
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


        recyclerView = (RecyclerView) coinsView.findViewById(R.id.recyclerView_coins);
        recyclerView.setHasFixedSize(true);

        layoutManager = new StaggeredGridLayoutManager(1,1);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new Adapter_Coins(coinsList, getContext(),this);
        recyclerView.setAdapter(mAdapter);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                backActivity();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),callback);

        back_imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backActivity();
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
        return coinsView;
    }

    //implementing RecyclerViewInterface
    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("Task", task);
        bundle.putInt("CoinID", coinIDs.get(position));
        Navigation.findNavController(coinsView).navigate(R.id.action_fragment_Coins_to_fragment_Coin_Details,bundle);
    }

    @Override
    public void backActivity() {
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
}