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
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;


import com.example.mynt.RecyclerViewInterface;
import com.example.mynt.collectionsActivity.adapters.Adapter_Coin;
import com.example.mynt.collectionsActivity.models.Model_Coin;

import com.example.mynt.R;
import com.example.mynt.dataAccessLayer.Database_Lite;

import com.example.mynt.collectionsActivity.adapters.Adapter_Library_Options;
import com.example.mynt.collectionsActivity.models.Model_Library_Options;
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
        //String email = getArguments().getString("userEmail");
        String email = "josh";
        Database_Lite dl = new Database_Lite(getContext());

        Model_User user = dl.getUser(email);

        //ArrayList<Model_Coin> userCoins = dl.getAllCoins();
        ArrayList<Model_Coin> userCoins = new ArrayList<>();
        userCoins = dl.getAllCoins();

        optionListView = libraryView.findViewById(R.id.listView_navigation_library);
        loginButton = libraryView.findViewById(R.id.imageButton_userActivity_library);
        ArrayList<Model_Library_Options> arrayList_library_navigation = new ArrayList<>();
        ArrayList<Model_Coin> arrayList_recent_coins = new ArrayList<>();

        //Populating Library Options List
        arrayList_library_navigation.add(new Model_Library_Options( R.drawable.img_app_logo,
                getResources().getString(R.string.library_option_coins),
                0,
                dl.getAllCoins().size()));

        arrayList_library_navigation.add(new Model_Library_Options( R.drawable.ic_collection_icon,
                getResources().getString(R.string.library_option_collections),
                0,
                0));

        arrayList_library_navigation.add(new Model_Library_Options( R.drawable.ic_goal_icon,
                getResources().getString(R.string.library_option_goals),
                62,
                62));


        int i=userCoins.size()-1;

        if(i>0)
            do {
                arrayList_recent_coins.add(userCoins.get(i));
                if(arrayList_recent_coins.size()>3)
                {
                    break;
                }
                i--;
            }while (i>=0);


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
        optionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView parent, View v, int position, long id){

                //Intent collections = new Intent(getContext(), Activity_Collections.class);
                if(position==0)
                {
                    Bundle bundle = new Bundle();
                    bundle.putInt("Task",0);
                    Navigation.findNavController(libraryView).navigate(R.id.action_fragment_home_main_to_fragment_Coins,bundle);
                    //collections.putExtra("action","coins");
                }else if (position==1)
                {
                    Navigation.findNavController(libraryView).navigate(R.id.action_fragment_home_main_to_fragment_Collections);
                    //collections.putExtra("action","collections");
                    //Navigation.findNavController(libraryView).navigate(R.id.action_fragment_Library_to_fragment_Collections);
                }else if (position==2)
                {
                    //POE
                    //Goals Activity
                }

                //startActivity(collections);
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

        return libraryView;
    }

    //Implementing RecyclerViewInterface Method
    @Override
    public void onItemClick(int position) {
        //Intent i = new Intent(getContext(), Activity_CoinDetails.class);

        //startActivity(i);
    }

}