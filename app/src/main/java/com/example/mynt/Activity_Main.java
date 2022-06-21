package com.example.mynt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.mynt.collectionsActivity.Activity_Collections;
import com.example.mynt.collectionsActivity.models.Model_User;
import com.example.mynt.collectionsActivity.models.User_Data;
import com.example.mynt.dataAccessLayer.Database_Lite;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Activity_Main extends AppCompatActivity {

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
        Log.d("taskSycce", "onComplete: "+User_Data.currentUser.getEmail());
        if (!User_Data.currentUser.getEmail().equals("DefaultUser"))
        {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signInWithEmailAndPassword(User_Data.currentUser.getEmail(),User_Data.currentUser.getPassword())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Log.d("taskSycce", "onComplete: ");
                        User_Data.firebaseUser = mAuth.getCurrentUser();
                        User_Data.uploadAllLocalData(getApplicationContext());
                    }
                }
            });
            //sign in
        }
        handler.postDelayed(new Runnable(){//(Codeplayon, 2019)
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */

                Intent intent = new Intent(Activity_Main.this, Activity_Collections.class);//(Codeplayon, 2019)
                startActivity(intent);
                finish();

                //Intent mainIntent = new Intent(SplashActivity.this,LoginActivity.class);
                //SplashActivity.this.startActivity(mainIntent);
                //SplashActivity.this.finish();
            }
        }, 2500);//(Codeplayon, 2019)
    }
}