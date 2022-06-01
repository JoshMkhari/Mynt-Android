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

import com.example.mynt.R;

import java.util.Objects;

import pl.droidsonroids.gif.GifTextView;

public class Activity_Collections extends AppCompatActivity {
    private GifTextView gifImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collections);


        gifImageView = findViewById(R.id.imageViewGIf);
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

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                gifImageView.setBackgroundResource(R.drawable.splash_screen);
                //Intent mainIntent = new Intent(SplashActivity.this,LoginActivity.class);
                //SplashActivity.this.startActivity(mainIntent);
                //SplashActivity.this.finish();
            }
        }, 2500);

        Log.d("EasyPeasyt","runing "+ page);
        //startSplash();
        findNavController(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2))).
                setGraph(R.navigation.collection_navigation,bundle);
    }

    public void startSplash(){
        Animation fadeout = new AlphaAnimation(1.f, 1.f);
        fadeout.setDuration(2500);
        final View viewToAnimate = gifImageView;
        fadeout.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                gifImageView.setBackgroundResource(R.drawable.splash_screen);//splash_screen is your .gif file
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }
        //gifImageView.startAnimation(fadeout);
        });
    }
}