package com.example.mynt.dataAccessLayer;

import android.content.Context;
import android.util.Log;

import com.example.mynt.collectionsActivity.models.Model_Fire_Base_Coin;
import com.example.mynt.collectionsActivity.models.Model_Coin;
import com.example.mynt.collectionsActivity.models.Model_Collections;
import com.example.mynt.collectionsActivity.models.Model_User;
import com.example.mynt.collectionsActivity.models.Model_User_Data;
import com.google.android.gms.tasks.OnSuccessListener;
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
        Database_Lite db = new Database_Lite(appContext);
        //Delete all data within all Tables
        db.removeUserData();
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
        Log.d("wehatWeGot", "replaceSqlDatabase: collection size" + Model_User_Data.currentUser.getCollections().size());
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
        Log.d("theSync", "population complete ");
            //Populate Coins Table
            //Populate Collection Coins Table
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
