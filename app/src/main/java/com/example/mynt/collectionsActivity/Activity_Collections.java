package com.example.mynt.collectionsActivity;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.mynt.R;

public class Activity_Collections extends AppCompatActivity {
    private NavGraph collectionsNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collections);

        //Check current user
        Bundle bundle = new Bundle();
        bundle.putString("User","Josh");

        //NavHostFragment.create(R.navigation.collection_navigation, bundle);
    }

    /*
    @Override
    public void onBackPressed()
    {
        //Intent intent = new Intent(this,Activity_Collections.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //startActivity(intent);
        String currentFragment = getForegroundFragment().toString();
        Log.d("backPressed", currentFragment);

        //Fragment_Library Main Library Page and Main home Page

        //Fragment_Main Register and Login pages

        //Fragment_home_main e4feb2a ON LIBRARY PAGE
            //Close App

        try {
            Fragment navHostFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2);
            navHostFragment.
            //navHostFragment.on
            //navHostFragment
            //navigate up
        }catch (Exception e)
        {
            Intent intent = new Intent(this,Activity_Collections.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

     */

    public Fragment getForegroundFragment(){
        Fragment navHostFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2);
        return navHostFragment == null ? null : navHostFragment.getChildFragmentManager().getFragments().get(1);
    }
}