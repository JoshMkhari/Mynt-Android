package com.example.mynt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.mynt.collectionsActivity.Activity_Collections;
import com.example.mynt.collectionsActivity.models.Model_User;
import com.example.mynt.collectionsActivity.models.User_Data;
import com.example.mynt.dataAccessLayer.Database_Lite;

import java.util.ArrayList;

public class Activity_Main extends AppCompatActivity {

    public static boolean sync = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler;//(Codeplayon, 2019)

        handler = new Handler();//(Codeplayon, 2019)

        Database_Lite db = new Database_Lite(getApplicationContext());
        ArrayList<Model_User> allUsers = db.getAllUsers();
        for (int i = 0; i < allUsers.size(); i++) {
            if(allUsers.get(i).getState()==1)
            {
                User_Data.currentUser = allUsers.get(i);
                break;
            }
        }

        handler.postDelayed(new Runnable(){//(Codeplayon, 2019)
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */

                Intent intent = new Intent(Activity_Main.this, Activity_Collections.class);//(Codeplayon, 2019)
                sync = true;
                startActivity(intent);
                finish();

                //Intent mainIntent = new Intent(SplashActivity.this,LoginActivity.class);
                //SplashActivity.this.startActivity(mainIntent);
                //SplashActivity.this.finish();
            }
        }, 2500);//(Codeplayon, 2019)
    }
}