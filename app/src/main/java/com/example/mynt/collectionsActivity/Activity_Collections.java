package com.example.mynt.collectionsActivity;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

import androidx.appcompat.app.AppCompatActivity;

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

        Bundle bundle = getIntent().getExtras();
        int page = 1;
        if(bundle == null)
        {
            bundle = new Bundle();
            bundle.putInt("StartPage",1);
        }
        else
        {
            page = bundle.getInt("StartPage");
            bundle.putInt("StartPage",page);
        }

        Log.d("EasyPeasyt","runing "+ page);

        findNavController(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2))).
                setGraph(R.navigation.collection_navigation,bundle);
    }
}