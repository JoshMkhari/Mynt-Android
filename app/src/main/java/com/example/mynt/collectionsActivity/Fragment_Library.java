package com.example.mynt.collectionsActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.service.autofill.UserData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;


import com.example.mynt.Interface_RecyclerView;
import com.example.mynt.collectionsActivity.adapters.Adapter_Recent_Coins;
import com.example.mynt.collectionsActivity.models.Model_Coin;

import com.example.mynt.R;
import com.example.mynt.collectionsActivity.models.Model_Coin_Comparator_ID;
import com.example.mynt.collectionsActivity.models.Model_User_Data;

import com.example.mynt.collectionsActivity.adapters.Adapter_Library_Options;
import com.example.mynt.collectionsActivity.models.Model_Library_Options;
import com.example.mynt.collectionsActivity.models.Model_User;
import com.example.mynt.dataAccessLayer.Model_Database_Lite;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Library#} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Library extends Fragment implements Interface_RecyclerView {
    private ListView optionListView;
    private ImageButton loginButton;
    private View libraryView;
    private ArrayList<Model_Coin> arrayList_recent_coins;
    private Model_User user;//(Section, 2021)

    @Override
    public void onResume() {
        super.onResume();
        if(Model_User_Data.currentUser.getEmail().equals("DefaultUser"))
        {
            loginButton.setBackgroundResource(R.drawable.ic_baseline_account_circle_24);
        }else
        {
            loginButton.setBackgroundResource(R.drawable.ic_baseline_settings_24);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        libraryView = inflater.inflate(R.layout.fragment_library, container, false);

        //Initializing variables
        //String email = getArguments().getString("userEmail");

        optionListView = libraryView.findViewById(R.id.listView_navigation_library);
        loginButton = libraryView.findViewById(R.id.imageButton_userActivity_library);
        ArrayList<Model_Library_Options> arrayList_library_navigation = new ArrayList<>();
        arrayList_recent_coins = new ArrayList<>();
        if(Model_User_Data.currentUser.getEmail().equals("DefaultUser"))
        {
            loginButton.setBackgroundResource(R.drawable.ic_baseline_account_circle_24);
        }else
        {
            loginButton.setBackgroundResource(R.drawable.ic_baseline_settings_24);
        }
        ReturnToRegister();
        ViewLoggedInUser();
        NavigationToOtherPages();

        Model_Database_Lite mdl = new Model_Database_Lite();
        ArrayList<Model_Coin> currentUserCoins = mdl.allCoinsAndCollections(getContext(), 0, 0);

        //Populating Library Options List
        arrayList_library_navigation.add(new Model_Library_Options(//(Section, 2021)
                getResources().getString(R.string.library_option_coins),
                currentUserCoins.size()));

        arrayList_library_navigation.add(new Model_Library_Options(//(Section, 2021)
                getResources().getString(R.string.library_option_collections),
                0));

        arrayList_library_navigation.add(new Model_Library_Options(//(Section, 2021)
                getResources().getString(R.string.library_option_goals),
                62));

        int i= currentUserCoins.size();


        Collections.sort(currentUserCoins, new Model_Coin_Comparator_ID());//(GeeksForGeeks,2020)

        arrayList_recent_coins.addAll(currentUserCoins);


        //Passing data to list recycler view
        //Variable Declarations
        RecyclerView recyclerView = libraryView.findViewById(R.id.recyclerView_recentCoins_library);
        //recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);

        //Ensuring the recycler view layout contains 2 item in each row
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, 1);//(Professor Sluiter, 2020).
        recyclerView.setLayoutManager(layoutManager);

        //Setting up adapters
        //ListView
        Adapter_Library_Options optionsListAdapter = new Adapter_Library_Options(getContext(), arrayList_library_navigation, user);//(FoxAndroid,2021)
        optionListView.setAdapter(optionsListAdapter);
        //recyclerView
        RecyclerView.Adapter<Adapter_Recent_Coins.CoinViewHolder> mAdapter = new Adapter_Recent_Coins(arrayList_recent_coins, getContext(), this);//(Professor Sluiter, 2020).
        recyclerView.setAdapter(mAdapter);


        return libraryView;
    }

    private void ViewLoggedInUser(){
        user = Model_User_Data.currentUser;
    }

    private void NavigationToOtherPages(){


        //Onclick Listeners
        optionListView.setOnItemClickListener((parent, v, position, id) -> {
            Bundle bundle = new Bundle();//(valerybodak,2020)
            bundle.putInt("User",user.getUserID());
            //Intent collections = new Intent(getContext(), Activity_Collections.class);
            if(position==0)
            {
                bundle.putInt("Task",2);
                Navigation.findNavController(libraryView).navigate(R.id.action_fragment_home_main_to_fragment_Coins,bundle);
                //collections.putExtra("action","coins");
            }else if (position==1)
            {
                bundle.putInt("Task", 0);
                Navigation.findNavController(libraryView).navigate(R.id.action_fragment_home_main_to_fragment_Collections, bundle);
            }else if (position==2)
            {
                //POE
                //Goals Activity
                Navigation.findNavController(libraryView).navigate(R.id.action_fragment_home_main_to_fragment_All_Goals, bundle);
            }

            //startActivity(collections);
        });


    }

    private void ReturnToRegister(){
        loginButton.setOnClickListener(v -> {
            //Bundle.Add.Extra(Name of coin, year, country)
            if(Model_User_Data.currentUser.getEmail().equals("DefaultUser"))
            {
                loginButton.setBackgroundResource(R.drawable.ic_baseline_account_circle_24);
                Navigation.findNavController(libraryView).navigate(R.id.action_fragment_home_main_to_fragment_Register);
            }else
            {
                loginButton.setBackgroundResource(R.drawable.ic_baseline_settings_24);
                Navigation.findNavController(libraryView).navigate(R.id.action_fragment_home_main_to_fragment_Settings);
            }

        });

    }
    //Implementing RecyclerViewInterface Method
    @Override
    public void onItemClick(int position, ImageView coinImage) {
        Bundle bundle = new Bundle();//(valerybodak,2020)
        bundle.putInt("Task", 0);
        bundle.putInt("CoinID", arrayList_recent_coins.get(position).getCoinID());
        //ViewCompat.setTransitionName(coinImage, "recentTransaction");

        Navigation.findNavController(libraryView).navigate(
                R.id.action_fragment_home_main_to_fragment_Coin_Details,
                bundle);


    }



}