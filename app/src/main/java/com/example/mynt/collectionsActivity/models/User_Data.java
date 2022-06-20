package com.example.mynt.collectionsActivity.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mynt.dataAccessLayer.Database_Lite;
import com.example.mynt.dataAccessLayer.Model_Database_Lite;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class User_Data {

    // Will Hold static once off data
    public static Model_Coin model_coin;
    public static Bitmap coinBitmap;
    public static FirebaseUser firebaseUser;
    public static Model_User currentUser;

    public void uploadAllLocalData(Context context)
    {
        //First check if user is authorized

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mDatabase = database.getReference();

        //Adding collections
        Database_Lite db = new Database_Lite(context);

        ArrayList<Model_Collections> allCollections = db.getAllCollections();
        ArrayList<Model_Collections> userCollections = new ArrayList<>();
        Model_Database_Lite mdl = new Model_Database_Lite();
        for (int i=0; i<allCollections.size(); i++)
        {
            ArrayList<Model_Coin> coins = mdl.allCoinsAndCollections(context,1,allCollections.get(i).getCollectionID());
            ArrayList<ModelFireBaseCoin> modelFireBaseCoinArrayList = new ArrayList<>();
            for (int s=0; s<coins.size(); s++)
            {
                String Value_Year = coins.get(s).getValue() + coins.get(s).getYear();
                ModelFireBaseCoin modelFireBaseCoin = new ModelFireBaseCoin(Value_Year,coins.get(s).getDateAcquired());
                uploadImage(Value_Year, coins.get(s).getImageId());
                modelFireBaseCoinArrayList.add(modelFireBaseCoin);
            }
            userCollections.add(new Model_Collections(allCollections.get(i).getCollectionName()
                    ,allCollections.get(i).getGoal(),
                    modelFireBaseCoinArrayList));
        }

        //Adding coins to collections
        currentUser.setCollections(userCollections);

        //Adding User if not existing
        mDatabase.child("users").child(firebaseUser.getUid()).setValue(currentUser);

        //Merge online data with offline data
    }

    private void uploadImage(String ImageID, byte[] data)
    {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        // Create a reference to "ImageID.jpg"
        String fileName = ImageID + ".jpg";
        StorageReference mountainsRef = storageRef.child(fileName);

        // Create a reference to 'images/mountains.jpg'
        String directory = firebaseUser.getUid() + "/" + fileName;

        UploadTask uploadTask = storageRef.child(directory).putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Log.d("godDammit", "onFailure: ");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
                Log.d("godDammit", "onSuccess: ");
            }
        });
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
