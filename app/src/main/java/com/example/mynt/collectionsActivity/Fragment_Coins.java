package com.example.mynt.collectionsActivity;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
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
import com.example.mynt.dataAccessLayer.Model_Database_Lite;

import java.util.ArrayList;
import java.util.Objects;

public class Fragment_Coins extends Fragment implements Interface_RecyclerView, Interface_Back {
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
    private Bundle bundle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        coinsView = inflater.inflate(R.layout.fragment_coins, container, false);


        pageTitle_textView = coinsView.findViewById(R.id.textview_title_coins);
        collectionName_textView = coinsView.findViewById(R.id.textview_blockTitle_coins);
        back_imageButton = coinsView.findViewById(R.id.image_button_back_coins);

        assert getArguments() != null;
        task = getArguments().getInt("Task");
        blockTitle = getArguments().getString("Collection Name");
        model_user = new Model_User();
        model_user.setUserID(getArguments().getInt("User"));
        collectionID = getArguments().getInt("CollectionID");

        DisplayAllLocalCoinsAndCollections();
        ReturnToCoinPage();
        recyclerView = coinsView.findViewById(R.id.recyclerView_coins);
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
                backActivity();
            }
        });


    }
    private void DisplayAllLocalCoinsAndCollections() {

        Model_Database_Lite mdl = new Model_Database_Lite();

        coinsList = mdl.allCoinsAndCollections(getContext(),task,collectionID, model_user);
        coinIDs = new ArrayList<>();

        if (task == 0 || task == 2) //All Coins
        {
            pageTitle_textView.setText(R.string.coins_title);
            collectionName_textView.setText(R.string.all_coins_block_title);

        } else//For specific collection
        {
            pageTitle_textView.setText(R.string.collections_title);
            collectionName_textView.setText(blockTitle);
        }

        for (int i=0; i<coinsList.size(); i++)
        {
            coinIDs.add(coinsList.get(i).getCoinID());
        }

    }

    //implementing RecyclerViewInterface
    @Override
    public void onItemClick(int position) {
        bundle = new Bundle();
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
            Bundle bundle = new Bundle();
            bundle.putInt("StartPage",0);
            findNavController(Objects.requireNonNull(getParentFragmentManager().findFragmentById(R.id.fragmentContainerView2))).
                    setGraph(R.navigation.collection_navigation,bundle);
        }
    }
}