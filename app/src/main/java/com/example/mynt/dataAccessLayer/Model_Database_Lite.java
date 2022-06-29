package com.example.mynt.dataAccessLayer;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mynt.collectionsActivity.models.Model_Fire_Base_Coin;
import com.example.mynt.collectionsActivity.models.Model_Coin;
import com.example.mynt.collectionsActivity.models.Model_Collections;
import com.example.mynt.collectionsActivity.models.Model_Leaderboard;
import com.example.mynt.collectionsActivity.models.Model_User;
import com.example.mynt.collectionsActivity.models.Model_User_Data;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Model_Database_Lite extends Thread {

    private ArrayList<Model_Collections>  allUserCollections;
    private ArrayList<Integer> collectionCoins;
    private Database_Lite db;
    private ArrayList<Model_Coin> dbCoins;
    private ArrayList<Model_Coin> coinsList;
    int coinID;
    int collectionID;
    Model_Coin model_coin;
    int year = 0;
    String value = "";

    public void replaceSqlDatabase(Context appContext)
    {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        db = new Database_Lite(appContext);
        //Delete all data within all Tables
        //Storing old password
        Log.d("password", "replaceSqlDatabase: " + Model_User_Data.pass + "for ");
        db.removeUserData();
        Model_User_Data.currentUser.setPassword(Model_User_Data.pass);
        Log.d("password", "replaceSqlDatabase: " + Model_User_Data.currentUser.getPassword());
        db.addUser(Model_User_Data.currentUser);
        ArrayList<Model_User> userArrayList = db.getAllUsers();
        Log.d("TAG", "LOGIN: result = " + db.addUser(Model_User_Data.currentUser));
        Log.d("TAG", "LOGIN: change made" + userArrayList.size());
        //Populate User Table
        coinID = 1;
        collectionID=1;
        //Log.d("lastCync", "replaceSqlDatabase: " + User_Data.currentUser.getLastSync());
        Log.d("theSync", "downloadData: udated sync" + Model_User_Data.currentUser.getLastSync());
        //Populate Collections Table
        if(Model_User_Data.currentUser.getCollections() != null)
        {
            for (Model_Collections currentCollection: Model_User_Data.currentUser.getCollections()) {
                ArrayList<Model_Collections> allCollections = db.getAllCollections();
                currentCollection.setCollectionID(allCollections.size()+1);
                db.addCollection(currentCollection);
                //Populate Coins Table
                Log.d("wehatWeGot", "replaceSqlDatabase: coins in collection " + currentCollection.getCollectionName() + " size " + currentCollection.getFireBaseCoinscoins().size());
                for (Model_Fire_Base_Coin currentFireCoin: currentCollection.getFireBaseCoinscoins()) {
                    // Create a reference to "ImageID.jpg"
                    String fileName = currentFireCoin.getValueYear() + ".jpg";
                    String directory = Model_User_Data.firebaseUser.getUid() + "/"+currentCollection.getCollectionName()+"/" + fileName;
                    Log.d("directory", "replaceSqlDatabase: " + directory);
                    StorageReference mountainsRef = storageRef.child(directory);
                    final long ONE_MEGABYTE = 1024 * 1024;
                    mountainsRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                        @Override
                        public void onSuccess(byte[] bytes) {
                            Log.d("theSync", "onSuccess: ");
                            char underscore = '_';
                            char[] valYearArray  = currentFireCoin.getValueYear().toLowerCase().toCharArray();
                            for (int i = 0; i < valYearArray.length; i++) {
                                if(valYearArray[i]==underscore)
                                {
                                    year = Integer.parseInt(currentFireCoin.getValueYear().substring(i+1));
                                    value = currentFireCoin.getValueYear().substring(0,i);
                                    model_coin = new Model_Coin(year,0,"","","","","",value, bytes,currentFireCoin.getDateTaken());
                                    model_coin.setCoinID(coinID);
                                    db.addCoin(model_coin,allCollections.size()+1);
                                    Model_Firebase model_firebase = new Model_Firebase(model_coin,appContext);
                                    model_firebase.downloadCoinData(appContext);
                                    coinID++;
                                    Log.d("theChange", "VALUE " + value);
                                    break;
                                }
                                Log.d("theChange", "replaceSqlDatabase: " +currentFireCoin.getValueYear() );
                            }

                            Log.d("wehatWeGot", "onSuccess: " + model_coin.getValue());
                        }
                    });

                }
            }
        }

        updateLeaderboard(appContext);
        Log.d("theSync", "population complete ");
            //Populate Coins Table
            //Populate Collection Coins Table
    }

    private void updateLeaderboard(Context context)
    {
        Database_Lite localDB = new Database_Lite(context);
        localDB.deleteLeaderboard();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mDatabase = database.getReference().child("users");
        mDatabase.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful())
                {
                    for (DataSnapshot postSnapshot: task.getResult().getChildren()
                    ) {
                        //Get user specific profile pic
                        //Retrieving User Profile Picture`
                        FirebaseStorage storage = FirebaseStorage.getInstance();
                        StorageReference storageRef = storage.getReference();
                        String fileName = postSnapshot.child("email").getValue(String.class) + ".jpg";
                        Log.d("leaderBoard", "onDataChange: filename "+ fileName );
                        String directory = "ProfilePicture"+"/" + fileName;
                        Log.d("leaderBoard", "onDataChange: UUID "+ directory );
                        try {
                            StorageReference mountainsRef = storageRef.child(directory);
                            final long ONE_MEGABYTE = 1024 * 1024;
                            mountainsRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                @Override
                                public void onSuccess(byte[] bytes) {
                                    int points = Math.round(postSnapshot.child("points").getValue(float.class));
                                    Model_Leaderboard lm = new Model_Leaderboard(postSnapshot.child("userName").getValue(String.class),points, bytes);//(Section, 2021)
                                    localDB.addLeaderboard(lm);
                                }
                            });
                        }catch (Exception ignored)
                        {}
                    }
                }
            }
        });
    }

    public ArrayList<Model_Coin> allCoinsAndCollections(Context appContext, int task, int collectionID)//(GeeksForGeeks,2020)
    {
        db = new Database_Lite(appContext);

        coinsList = new ArrayList<>();


        dbCoins = db.getAllCoins();
        allUserCollections = db.getAllCollections();


        if(task == 0 || task ==2) //All Coins
        {
            allCoins();
        }else//For specific collection
        {
            coinsInSpecificCollection(collectionID);
        }
        return coinsList;
    }

    public void allCoins()//(GeeksForGeeks,2020)
    {
        for (int b=0; b<allUserCollections.size(); b++) {
            collectionCoins = db.getAllCoinsInCollection(allUserCollections.get(b).getCollectionID());
            for (int i = 0; i < collectionCoins.size(); i++) {
                for (int s = 0; s < dbCoins.size(); s++) {
                    if (collectionCoins.get(i) == dbCoins.get(s).getCoinID()) {
                        coinsList.add(dbCoins.get(s));
                        //add final list here
                        break;
                    }
                }
            }
        }

    }


    public void coinsInSpecificCollection(int collectionID)
    {
        collectionCoins = db.getAllCoinsInCollection(collectionID);
        for (int i=0; i<collectionCoins.size(); i++)
        {
            for (int s=0; s<dbCoins.size(); s++) {
                if(collectionCoins.get(i)==dbCoins.get(s).getCoinID())
                {
                    coinsList.add(dbCoins.get(s));
                    //add final list here
                    break;
                }
            }
        }
    }
}
