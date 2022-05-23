package com.example.mynt.dataAccessLayer;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mynt.collectionsActivity.models.Model_Coin;
import com.example.mynt.collectionsActivity.models.Model_UserCoin;
import com.example.mynt.collectionsActivity.models.Model_Collections;
import com.example.mynt.userActivity.Model_User;

import java.util.ArrayList;

public class Database_Lite extends SQLiteOpenHelper {

    public Resources res;
    public Context context;
    //Coin Table

    public static final String COLUMN_MATERIAL_FK = "MATERIAL";
    //Material Table
    public static final String MATERIAL_TABLE = COLUMN_MATERIAL_FK + "_TABLE";
    public static final String COLUMN_NAME_VALUE = "NAME";
    public static final String COLUMN_MATERIAL_NAME = COLUMN_MATERIAL_FK + "_" + COLUMN_NAME_VALUE;
    public static final String COLUMN_YEAR_FK = "YEAR";
    public static final String YEAR_TABLE = COLUMN_YEAR_FK + "_TABLE";
    public static final String COLUMN_YEAR_ID = COLUMN_YEAR_FK + "_ID";
    public static final String COLUMN_VALUE_FK = "VALUE";
    public static final String VALUE_TABLE = COLUMN_VALUE_FK + "_TABLE";
    public static final String COLUMN_VARIETY_FK = "VARIETY";
    private static final String VARIETY_TABLE = COLUMN_VARIETY_FK + "_TABLE";
    private static final String COLUMN_VARIETY_NAME = "NAME";
    public static final String COIN_TABLE = "COIN_TABLE";
    public static final String COLUMN_ALT_NAME = "ALT_NAME";
    public static final String COLUMN_MINTAGE = "MINTAGE";
    public static final String COLUMN_OBSERVE = "OBSERVE";
    public static final String COLUMN_REVERSE = "REVERSE";
    public static final String COLUMN_IMAGE = "IMAGE";
    private static final String COLUMN_COIN_FK = "COIN_ID";
    private static final String COLUMN_COLLECTION_FK = "COLLECTION_ID";
    private static final String COLLECTIONS_COIN_TABLE = "COLLECTION_COIN_TABLE";
    private static final String COLLECTION_TABLE = "COLLECTION_TABLE";
    private static final String COLUMN_COLLECTION_NAME = "NAME";
    private static final String COLUMN_GOAL = "GOAL";

    //Year Table
    //public static final String COLUMN_MATERIAL_NAME = "NAME";

    public Database_Lite(@Nullable Context context) {
        super(context, "user.db", null, 1);
    }

    //First time a database is created
    @Override
    public void onCreate(SQLiteDatabase db) {

        //Table Creation Statements

        //Material Table
        String tableStatement = ("CREATE TABLE " + MATERIAL_TABLE + "(" + COLUMN_MATERIAL_NAME + " TEXT PRIMARY KEY );");
        db.execSQL(tableStatement);

        //Year Table
        tableStatement = ("CREATE TABLE " + YEAR_TABLE + "(" + COLUMN_YEAR_ID + " TEXT PRIMARY KEY );");
        db.execSQL(tableStatement);
        //Value Table
        tableStatement = ("CREATE TABLE " + VALUE_TABLE + "(" + COLUMN_NAME_VALUE + " TEXT PRIMARY KEY );");
        db.execSQL(tableStatement);
        //Variety Table
        tableStatement = ("CREATE TABLE " + VARIETY_TABLE + "(" + COLUMN_VARIETY_NAME + " TEXT PRIMARY KEY );");
        db.execSQL(tableStatement);
        //Coin Table

        tableStatement = ("CREATE TABLE " + COIN_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ALT_NAME + " TEXT, "
                + COLUMN_MINTAGE + " INTEGER, " + COLUMN_OBSERVE + " TEXT, " + COLUMN_REVERSE + " TEXT, "+ COLUMN_IMAGE + " TEXT, " + COLUMN_VALUE_FK + " REAL, "
                + COLUMN_YEAR_FK + " INTEGER, " + COLUMN_VARIETY_FK + " INTEGER, " + COLUMN_MATERIAL_FK + " INTEGER, "
                + "FOREIGN KEY (" + COLUMN_VALUE_FK + ") REFERENCES "+ VALUE_TABLE +"("+COLUMN_NAME_VALUE+"), "
                + "FOREIGN KEY (" + COLUMN_YEAR_FK + ") REFERENCES "+ YEAR_TABLE +"("+COLUMN_YEAR_ID+"), "
                + "FOREIGN KEY (" + COLUMN_VARIETY_FK + ") REFERENCES "+ VARIETY_TABLE +"("+COLUMN_VARIETY_NAME+"), "
                + "FOREIGN KEY (" + COLUMN_MATERIAL_FK + ") REFERENCES "+ MATERIAL_TABLE +"("+COLUMN_MATERIAL_NAME+"));");
        db.execSQL(tableStatement);

        //Collections Table
        tableStatement = ("CREATE TABLE " + COLLECTION_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_COLLECTION_NAME
                + " TEXT, "+ COLUMN_GOAL + " INTEGER);");
        db.execSQL(tableStatement);

        //Collection_Coin Table
        tableStatement = ("CREATE TABLE " + COLLECTIONS_COIN_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_COLLECTION_FK
                + " INTEGER, " + COLUMN_COIN_FK + " INTEGER, "
                + "FOREIGN KEY (" + COLUMN_COLLECTION_FK + ") REFERENCES "+ COLLECTION_TABLE +"(ID) ,"
                + "FOREIGN KEY (" + COLUMN_COIN_FK + ") REFERENCES "+ COIN_TABLE +"(ID));");
        db.execSQL(tableStatement);

        ContentValues cv = new ContentValues();

        //Populate Materials Table
            cv.put(COLUMN_MATERIAL_NAME,"Bimetallic");
            db.insert(MATERIAL_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_MATERIAL_NAME,"Silver");
            db.insert(MATERIAL_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_MATERIAL_NAME,"Nickel-plated copper");
            db.insert(MATERIAL_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_MATERIAL_NAME,"Nickel");
            db.insert(MATERIAL_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_MATERIAL_NAME,"Bronze-plated Steel");
            db.insert(MATERIAL_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_MATERIAL_NAME,"Copper-plated Steel");
            db.insert(MATERIAL_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_MATERIAL_NAME,"Bronze");
            db.insert(MATERIAL_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_MATERIAL_NAME,"Brass");
            db.insert(MATERIAL_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_MATERIAL_NAME,"Aluminium-Bronze");
            db.insert(MATERIAL_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_MATERIAL_NAME,"Gold");
            db.insert(MATERIAL_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_MATERIAL_NAME,"Platinum");
            db.insert(MATERIAL_TABLE,null,cv);
            cv.clear();

        //Populate Value Table
            cv.put(COLUMN_NAME_VALUE,"Half Cent");
            db.insert(VALUE_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_NAME_VALUE,"One Cent");
            db.insert(VALUE_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_NAME_VALUE,"Two and a Half Cent");
            db.insert(VALUE_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_NAME_VALUE,"Five Cent");
            db.insert(VALUE_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_NAME_VALUE,"Ten Cent");
            db.insert(VALUE_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_NAME_VALUE,"Twenty Cent");
            db.insert(VALUE_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_NAME_VALUE,"Fifty Cent");
            db.insert(VALUE_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_NAME_VALUE,"One Rand");
            db.insert(VALUE_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_NAME_VALUE,"Two Rand");
            db.insert(VALUE_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_NAME_VALUE,"Five Rand");
            db.insert(VALUE_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_NAME_VALUE,"Fifty Rand");
            db.insert(VALUE_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_NAME_VALUE,"Tenth Ounce");
            db.insert(VALUE_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_NAME_VALUE,"Twentieth Ounce");
            db.insert(VALUE_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_NAME_VALUE,"Quarter Ounce");
            db.insert(VALUE_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_NAME_VALUE,"Fiftieth Ounce");
            db.insert(VALUE_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_NAME_VALUE,"Half Ounce");
            db.insert(VALUE_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_NAME_VALUE,"Ounce");
            db.insert(VALUE_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_NAME_VALUE,"Two Ounce");
            db.insert(VALUE_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_NAME_VALUE,"Five Ounce");
            db.insert(VALUE_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_NAME_VALUE,"Kilo");
            db.insert(VALUE_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_NAME_VALUE,"Penny");
            db.insert(VALUE_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_NAME_VALUE,"Three Pence");
            db.insert(VALUE_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_NAME_VALUE,"Six Pence");
            db.insert(VALUE_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_NAME_VALUE,"Shilling");
            db.insert(VALUE_TABLE,null,cv);
            cv.clear();


        //Populate Variety Table
            cv.put(COLUMN_VARIETY_NAME,"Proof");
            db.insert(VARIETY_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_VARIETY_NAME,"Brilliant CW privy mark");
            db.insert(VARIETY_TABLE,null,cv);
            cv.clear();
            cv.put(COLUMN_VARIETY_NAME,"Brilliant Uncirculated");
            db.insert(VARIETY_TABLE,null,cv);
            cv.clear();

    }

    //In the event a database version number changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    public ArrayList<Model_Collections> getAllCollections() {
        ArrayList<Model_Collections> collectionsList = new ArrayList<>();

        String queryString = "SELECT * FROM " + COIN_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst())
        {
            //loop through the cursor result set and create new coin object for each row
            do{
                String collectionName = cursor.getString(1);
                int goal = cursor.getInt(2);

                Model_Collections collection = new Model_Collections(collectionName,goal);

                collectionsList.add(collection);
            }while (cursor.moveToNext());
        }
        else
        {
            //failure means list is empty
        }
        cursor.close();
        //Toast.makeText(context,collectionsList.size()+" size",Toast.LENGTH_LONG).show();
        return collectionsList;
    }
    public ArrayList<Model_Coin> getAllCoins()
    {
        ArrayList<Model_Coin> coinsList = new ArrayList<>();

        String queryString = "SELECT * FROM " + COIN_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst())
        {
            //loop through the cursor result set and create new coin object for each row
            do{
                int coinID = cursor.getInt(0);
                String altName = cursor.getString(1);
                int mintage = cursor.getInt(2);
                String observe = cursor.getString(3);
                String reverse = cursor.getString(4);
                String image = cursor.getString(5);
                String value = cursor.getString(6);
                int year = cursor.getInt(7);
                String variety = cursor.getString(8);
                String material = cursor.getString(9);


                Model_Coin coin = new Model_Coin(year,mintage,material,altName,observe,reverse,variety,value,image);
                coinsList.add(coin);
            }while (cursor.moveToNext());
        }
        else
        {
            //failure means list is empty
        }
        cursor.close();
        //Toast.makeText(context,coinsList.size()+" size",Toast.LENGTH_LONG).show();
        return coinsList;
    }

    public Model_User getUser(String email)
    {
        Model_User user = new Model_User();
        return user;
    }

    public boolean addCollectionCoin()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        int coinID =0;
        int collectionID = 0;
            try
            {
                coinID = getAllCoins().size();
                 collectionID = getAllCollections().size();
                try
                {
                    //Collections table
                    cv.put(COLUMN_COLLECTION_FK,collectionID);
                    cv.put(COLUMN_COIN_FK,coinID);
                    db.insert(COLLECTIONS_COIN_TABLE,null,cv);
                    cv.clear();
                    db.close();
                    return true;
                }catch (Exception e)
                {
                    db.close();;
                    Toast.makeText(context,"Collection Coin Table",Toast.LENGTH_LONG).show();
                    return false;
                }
            }catch ( Exception e)
            {
                Toast.makeText(context,coinID+" size",Toast.LENGTH_LONG).show();
                Toast.makeText(context,collectionID+" size",Toast.LENGTH_LONG).show();
                db.close();;
                return false;
            }
        }

    public boolean addCollection(Model_Collections model_collections) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        try {
            //Collections table
            cv.put(COLUMN_COLLECTION_NAME, model_collections.getCollectionName());
            cv.put(COLUMN_GOAL, model_collections.getGoal());
            db.insert(COLLECTION_TABLE, null, cv);
            cv.clear();
            db.close();
            return true;
        } catch (Exception e) {
            db.close();
            Toast.makeText(context, "Collection Table", Toast.LENGTH_LONG).show();
            return false;

        }
    }

    public boolean addCoin(Model_Coin coin, int collectionID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        /*
        //Year Table
        //Extracting correct coin details for only user
        Model_UserCoin user_coin = collection.getModel_userArrayList().get(collection.getModel_userArrayList().size()-1);
        //Extracting the coin details
        Model_Coin coin = user_coin.getCoin();
        try
        {
            cv.put(COLUMN_YEAR_ID,coin.getYear());
            db.insert(YEAR_TABLE,null,cv);
            cv.clear();
        }catch (Exception e)
        {
            Toast.makeText(context,"Year Table",Toast.LENGTH_LONG).show();
            return false;
        }

         */

        try
        {
            //Coin Table
            cv.put(COLUMN_ALT_NAME,coin.getAlternateName());
            cv.put(COLUMN_MINTAGE,coin.getMintage());
            cv.put(COLUMN_OBSERVE,coin.getObserve());
            cv.put(COLUMN_REVERSE,coin.getReverse());
            cv.put(COLUMN_VALUE_FK,coin.getValue());
            cv.put(COLUMN_YEAR_FK,coin.getYear());
            cv.put(COLUMN_IMAGE,coin.getImageId());
            cv.put(COLUMN_VARIETY_FK,coin.getVariety());
            cv.put(COLUMN_MATERIAL_FK,coin.getMaterial());
            db.insert(COIN_TABLE,null,cv);
            cv.clear();
        }catch (Exception e)
        {
            Toast.makeText(context,"Coin Table",Toast.LENGTH_LONG).show();
            db.close();;
            return false;
        }
        if(collectionID != 0)
        {
            try
            {
                int coinID = getAllCoins().size();
                try
                {
                    //Collections table
                    cv.put(COLUMN_COLLECTION_FK,collectionID);
                    cv.put(COLUMN_COIN_FK,coinID);
                    db.insert(COLLECTIONS_COIN_TABLE,null,cv);
                    cv.clear();
                    db.close();;
                    return true;
                }catch (Exception e)
                {
                    db.close();;
                    Toast.makeText(context,"Collection Table",Toast.LENGTH_LONG).show();
                    return false;
                }
            }catch ( Exception e)
            {
                db.close();;
                return false;
            }

        }
        else
        {
            db.close();;
            return  true;
        }



        //Collections Coin Table

    }


}
