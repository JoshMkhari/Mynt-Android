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

import java.util.Objects;

public class Activity_Collections extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collections);

        //Check current user
        Bundle bundle = new Bundle();
        bundle.putInt("StartPage",1);
        findNavController(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2))).
                setGraph(R.navigation.collection_navigation,bundle);
    }
}