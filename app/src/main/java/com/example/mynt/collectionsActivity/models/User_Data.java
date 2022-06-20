package com.example.mynt.collectionsActivity.models;

import android.graphics.Bitmap;
import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User_Data {

    // Will Hold static once off data
    public static Model_Coin model_coin;
    public static Bitmap coinBitmap;
    public static FirebaseUser firebaseUser;
    public static Model_User currentUser;

    public void uploadLocalData()
    {
        //First check if user is authorized

        //Download From FireBase
        downloadOnlineData();

        mergeData();
        // Write a message to the database
        if(currentUser.getUuid() != null)
        {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference(currentUser.getUuid());
            myRef.setValue("Hello, World!");
        }



        //Merge online data with offline data
    }

    private void downloadOnlineData()
    {
        Log.d("downloadOnlineData", "downloadOnlineData: ");
    }

    private void mergeData()
    {
        Log.d("mergeData", "mergeData: ");
    }

}
