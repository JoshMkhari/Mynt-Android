package com.example.mynt.collectionsActivity;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;

import com.example.mynt.R;

public class Activity_Collections extends AppCompatActivity {
    private NavGraph collectionsNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collections);

        Bundle bundle = new Bundle();
        bundle.putString("User","Josh");
        //NavHostFragment.create(R.navigation.collection_navigation, bundle);
    }

    @Override
    public void onBackPressed()
    {
        //Intent intent = new Intent(this,Activity_Collections.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //startActivity(intent);
    }
}