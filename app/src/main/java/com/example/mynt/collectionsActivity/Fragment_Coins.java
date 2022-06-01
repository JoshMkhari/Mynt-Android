package com.example.mynt.collectionsActivity;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

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
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.mynt.R;
import com.example.mynt.Interface_RecyclerView;
import com.example.mynt.collectionsActivity.adapters.Adapter_Coins;
import com.example.mynt.collectionsActivity.models.Model_Coin_Comparator;
import com.example.mynt.collectionsActivity.models.Model_Coin;
import com.example.mynt.collectionsActivity.models.Model_Collections;
import com.example.mynt.collectionsActivity.models.Model_Goals;
import com.example.mynt.collectionsActivity.models.Model_User;
import com.example.mynt.dataAccessLayer.Database_Lite;
import com.example.mynt.dataAccessLayer.Model_Database_Lite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Fragment_Coins extends Fragment implements Interface_RecyclerView {
    private ImageButton back_imageButton;
    private View coinsView;
    private int collectionID,task;
    private ArrayList<Integer> coinIDs;
    private TextView collectionName_textView,pageTitle_textView, progress_textview;
    private String blockTitle;
    private Model_User model_user;
    private ArrayList<Model_Coin> coinsList;
    private ProgressBar goalProgress;
    private Model_Goals currentCollection;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        coinsView = inflater.inflate(R.layout.fragment_coins, container, false);


        pageTitle_textView = coinsView.findViewById(R.id.textview_title_coins);
        collectionName_textView = coinsView.findViewById(R.id.textview_blockTitle_coins);
        back_imageButton = coinsView.findViewById(R.id.image_button_back_coins);
        goalProgress = coinsView.findViewById(R.id.progressBarCollection);
        progress_textview = coinsView.findViewById(R.id.collections_goal_percent);


        assert getArguments() != null;
        task = getArguments().getInt("Task");
        blockTitle = getArguments().getString("Collection Name");
        model_user = new Model_User();
        model_user.setUserID(getArguments().getInt("User"));
        collectionID = getArguments().getInt("CollectionID");

        DisplayAllLocalCoinsAndCollections();
        ReturnToCoinPage();
        RecyclerView recyclerView = coinsView.findViewById(R.id.recyclerView_coins);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(1, 1);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter<Adapter_Coins.CoinViewHolder> mAdapter = new Adapter_Coins(coinsList, getContext(), this);
        recyclerView.setAdapter(mAdapter);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                backActivity();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),callback);

        goalProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change to goal page
                Bundle bundle = new Bundle();
                bundle.putString("Collection Name", currentCollection.getCollectionName());
                bundle.putInt("Coins", currentCollection.getNumCoins());
                bundle.putInt("Goal", currentCollection.getTarget());
                bundle.putInt("CollectionID",collectionID);
                bundle.putInt("Task",2);
                Navigation.findNavController(coinsView).navigate(R.id.action_fragment_Coins_to_fragment_Goal,bundle);
            }
        });

        //ListView myList = new ListView(allCoinsView.getContext());


        return coinsView;
    }

    private void ReturnToCoinPage(){

        back_imageButton.setOnClickListener(v -> backActivity());


    }
    private void DisplayAllLocalCoinsAndCollections() {

        Model_Database_Lite mdl = new Model_Database_Lite();

        coinsList = mdl.allCoinsAndCollections(getContext(),task,collectionID, model_user);
        coinIDs = new ArrayList<>();

        Collections.sort(coinsList, new Model_Coin_Comparator());

        if (task == 0 || task == 2) //All Coins
        {
            pageTitle_textView.setText(R.string.coins_title);
            collectionName_textView.setText(R.string.all_coins_block_title);
            goalProgress.setVisibility(View.GONE);
            progress_textview.setVisibility(View.GONE);

        } else//For specific collection
        {
            pageTitle_textView.setText(R.string.collections_title);
            collectionName_textView.setText(blockTitle);

            Database_Lite db = new Database_Lite(getContext());
            ArrayList<Model_Collections> AllCollections = db.getAllCollections();

            ArrayList<Integer> collectionSize = db.getAllCoinsInCollection(collectionID);
            for (int i=0; i<AllCollections.size(); i++)
            {
                if(AllCollections.get(i).getCollectionID()== collectionID)
                {
                    currentCollection= new Model_Goals(AllCollections.get(i).getCollectionName(),0,AllCollections.get(i).getGoal());
                }
            }
            currentCollection.setNumCoins(coinsList.size());

            float coins = (float)currentCollection.getNumCoins();
            float target = (float)currentCollection.getTarget();

            Log.d("ValuesDarsh",coins+" coins this");
            Log.d("ValuesDarsh",target+" target this");
            float progress =  coins /target *100;

            if(progress>100)
                progress =100;

            String progressText = Math.round(progress)+"%";
            goalProgress.setProgress(Math.round(progress));
            progress_textview.setText(progressText);
        }

        for (int i=0; i<coinsList.size(); i++)
        {
            coinIDs.add(coinsList.get(i).getCoinID());
        }

    }

    //implementing RecyclerViewInterface
    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("Task", task);
        bundle.putInt("CoinID", coinIDs.get(position));
        Navigation.findNavController(coinsView).navigate(R.id.action_fragment_Coins_to_fragment_Coin_Details, bundle);
    }

    private void backActivity() {
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