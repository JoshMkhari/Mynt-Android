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

    public void uploadAllLocalData()
    {
        //First check if user is authorized
        Log.d("NiggaP", "uploadAllLocalData: ");
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference mDatabase = database.getReference();
            mDatabase.child("users").child(firebaseUser.getUid()).setValue(currentUser);
        //mDatabase.child("users").child(firebaseUser.getUid()).setValue("Nigga");

        //Merge online data with offline data
    }

    private void downloadOnlineData()
    {
        Log.d("downloadOnlineData", "downloadOnlineData: ");
    }

    public void mergeData()
    {
        Log.d("mergeData", "mergeData: ");
    }

}
