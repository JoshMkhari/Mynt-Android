package com.example.mynt.collectionsActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.mynt.R;

public class Activity_Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler;

        handler = new Handler();

        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */

                Intent intent = new Intent(Activity_Splash.this, Activity_Collections.class);
                startActivity(intent);
                finish();
                //Intent mainIntent = new Intent(SplashActivity.this,LoginActivity.class);
                //SplashActivity.this.startActivity(mainIntent);
                //SplashActivity.this.finish();
            }
        }, 2500);
    }
}