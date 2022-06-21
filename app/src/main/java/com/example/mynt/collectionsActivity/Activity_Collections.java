package com.example.mynt.collectionsActivity;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.example.mynt.Activity_Main;
import com.example.mynt.R;
import com.example.mynt.collectionsActivity.models.User_Data;

import java.util.Objects;

import pl.droidsonroids.gif.GifTextView;

public class Activity_Collections extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_collections);

        Bundle bundle = getIntent().getExtras();//(valerybodak,2020)
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
        User_Data.uploadAllLocalData(getApplicationContext());
        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/

        findNavController(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2))).
                setGraph(R.navigation.collection_navigation,bundle);//(developer Android NavController, n.d)

    }
}