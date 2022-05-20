package com.example.mynt.mainActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.mynt.Activity_Main;
import com.example.mynt.coinsActivity.Activity_CoinDetails;
import com.example.mynt.RecyclerViewInterface;
import com.example.mynt.coinsActivity.adapters.Adapter_Coin;
import com.example.mynt.coinsActivity.models.Model_Coin;
import com.example.mynt.coinsActivity.Activity_Coins;
import com.example.mynt.R;
import com.example.mynt.coinsActivity.models.Model_UserCoin;
import com.example.mynt.collectionsActivity.Activity_Collections;
import com.example.mynt.collectionsActivity.Model_Collections;
import com.example.mynt.dataAccessLayer.Database_Lite;
import com.example.mynt.goalsActivity.Activity_Goals;
import com.example.mynt.mainActivity.adapters.Adapter_Library_Options;
import com.example.mynt.mainActivity.models.Model_Library_Options;
import com.example.mynt.userActivity.Activity_User;
import com.example.mynt.userActivity.Model_User;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Library#} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Library extends Fragment implements RecyclerViewInterface {
    //Variable Declarations
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ListView optionListView;
    private ImageButton loginButton;
    private Adapter_Library_Options optionsListAdapter;

    private Model_User user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View libraryView = inflater.inflate(R.layout.fragment_library, container, false);

        //Initializing variables
        String email = getArguments().getString("userEmail");
        Database_Lite dl = new Database_Lite(getContext());

        Model_User user = dl.getUser(email);

        ArrayList<Model_Coin> userCoins = dl.getAllCoins();

        optionListView = libraryView.findViewById(R.id.listView_navigation_library);
        loginButton = libraryView.findViewById(R.id.imageButton_userActivity_library);
        ArrayList<Model_Library_Options> arrayList_library_navigation = new ArrayList<>();
        ArrayList<Model_Coin> arrayList_recent_coins = new ArrayList<>();

        //Populating Library Options List
        arrayList_library_navigation.add(new Model_Library_Options( R.drawable.img_app_logo,
                getResources().getString(R.string.library_option_coins),
                0,
                50));

        arrayList_library_navigation.add(new Model_Library_Options( R.drawable.ic_collection_icon,
                getResources().getString(R.string.library_option_collections),
                0,
                0));

        arrayList_library_navigation.add(new Model_Library_Options( R.drawable.ic_goal_icon,
                getResources().getString(R.string.library_option_goals),
                62,
                62));



        for (int i =userCoins.size()-1; i>=userCoins.size()-5;i--)
        {
            arrayList_recent_coins.add(userCoins.get(i));
        }

        //Passing data to list recycler view
        recyclerView = (RecyclerView) libraryView.findViewById(R.id.recyclerView_recentCoins_library);
        //recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);

        //Ensuring the recycler view layout contains 2 item in each row
        layoutManager = new StaggeredGridLayoutManager(2,1);
        recyclerView.setLayoutManager(layoutManager);

        //Setting up adapters
        //ListView
        optionsListAdapter = new Adapter_Library_Options(getContext(),arrayList_library_navigation);
        optionListView.setAdapter(optionsListAdapter);
        //recyclerView
        mAdapter = new Adapter_Coin(arrayList_recent_coins, getContext(),this);
        recyclerView.setAdapter(mAdapter);

        //Onclick Listeners
        SetUpOnClickListeners();

        return libraryView;
    }

    //Implementing RecyclerViewInterface Method
    @Override
    public void onItemClick(int position) {
        Intent i = new Intent(getContext(), Activity_CoinDetails.class);

        startActivity(i);
    }

    private void SetUpOnClickListeners()
    {
        optionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView parent, View v, int position, long id){

                if(position==0)
                {
                    Intent i = new Intent(getContext(), Activity_Coins.class);
                    startActivity(i);
                }else if (position==1)
                {
                    Intent s = new Intent(getContext(), Activity_Collections.class);
                    startActivity(s);
                }else if (position==2)
                {
                    Intent t = new Intent(getContext(), Activity_Goals.class);
                    startActivity(t);
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), Activity_User.class);
                //Bundle.Add.Extra(Name of coin, year, country)
                startActivity(i);
            }
        });
    }
}