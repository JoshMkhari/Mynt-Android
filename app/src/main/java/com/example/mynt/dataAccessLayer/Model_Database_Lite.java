package com.example.mynt.dataAccessLayer;

import android.content.Context;
import android.util.Log;

import com.example.mynt.collectionsActivity.models.Model_Coin;
import com.example.mynt.collectionsActivity.models.Model_Collections;
import com.example.mynt.collectionsActivity.models.Model_User;
import com.example.mynt.collectionsActivity.models.User_Data;

import java.util.ArrayList;

public class Model_Database_Lite extends Thread {

    private ArrayList<Model_Collections>  allUserCollections;
    private ArrayList<Integer> collectionCoins;
    private Database_Lite db;
    private ArrayList<Model_Coin> dbCoins;
    private ArrayList<Model_Coin> coinsList;

    public static void replaceSqlDatabase(Context appContext)
    {
        Database_Lite db = new Database_Lite(appContext);
        //Delete all data within all Tables

        //Populate User Table

        //Populate Collections Table
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
