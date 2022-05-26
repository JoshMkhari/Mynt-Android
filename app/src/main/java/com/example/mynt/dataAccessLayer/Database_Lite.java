package com.example.mynt.dataAccessLayer;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.mynt.collectionsActivity.models.Model_Coin;
import com.example.mynt.collectionsActivity.models.Model_Collections;
import com.example.mynt.collectionsActivity.models.Model_User;
import com.example.mynt.collectionsActivity.models.Model_UserCollection;

import java.io.PrintWriter;
import java.io.StringWriter;
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
    private static final String COLUMN_DATE_TAKEN = "DATE_TAKEN";
    private static final String USER_TABLE = "USER_TABLE";
    private static final String COLUMN_USER_NAME = "USER";
    private static final String COLUMN_PASSWORD = "PASSWORD";
    private  static final String COIN_ID = "ID";
    private  static final String USER_COLLECTIONS_TABLE = "USER_COLLECTION_TABLE";
    private  static final String COLUMN_USER_FK = "USER_ID";
    private static final String COLUMN_STATE = "STATE";


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

        //User Table
        tableStatement = ("CREATE TABLE " + USER_TABLE + "(" + COLUMN_USER_NAME + " TEXT PRIMARY KEY, " +
                COLUMN_STATE + " INTEGER, " + COLUMN_PASSWORD + " TEXT);");
        db.execSQL(tableStatement);

        //Year Table
        tableStatement = ("CREATE TABLE " + YEAR_TABLE + "(" + COLUMN_YEAR_ID + " INTEGER PRIMARY KEY );");
        db.execSQL(tableStatement);

        //Value Table
        tableStatement = ("CREATE TABLE " + VALUE_TABLE + "(" + COLUMN_NAME_VALUE + " TEXT PRIMARY KEY );");
        db.execSQL(tableStatement);
        //Variety Table
        tableStatement = ("CREATE TABLE " + VARIETY_TABLE + "(" + COLUMN_VARIETY_NAME + " TEXT PRIMARY KEY );");
        db.execSQL(tableStatement);

        //Collections Table
        tableStatement = ("CREATE TABLE " + COLLECTION_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_COLLECTION_NAME
                + " TEXT, "+ COLUMN_GOAL + " INTEGER);");
        db.execSQL(tableStatement);

        //UserCollections Table
        tableStatement = ("CREATE TABLE " + USER_COLLECTIONS_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_COLLECTION_FK
                + " INTEGER, " + COLUMN_USER_FK + " INTEGER, "
                + "FOREIGN KEY (" + COLUMN_COLLECTION_FK + ") REFERENCES "+ COLLECTION_TABLE +"(ID) ,"
                + "FOREIGN KEY (" + COLUMN_USER_FK + ") REFERENCES "+ USER_TABLE + "(" + COLUMN_USER_NAME +"));");
        db.execSQL(tableStatement);

        //Coin Table
        tableStatement = ("CREATE TABLE " + COIN_TABLE + "(" + COIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ALT_NAME + " TEXT, "
                + COLUMN_MINTAGE + " INTEGER, " + COLUMN_OBSERVE + " TEXT, " + COLUMN_REVERSE + " TEXT, "+ COLUMN_IMAGE + " TEXT, "
                + COLUMN_DATE_TAKEN + " TEXT, " + COLUMN_VALUE_FK + " REAL, "
                + COLUMN_YEAR_FK + " INTEGER, " + COLUMN_VARIETY_FK + " INTEGER, " + COLUMN_MATERIAL_FK + " INTEGER, "
                + "FOREIGN KEY (" + COLUMN_VALUE_FK + ") REFERENCES "+ VALUE_TABLE +"("+COLUMN_NAME_VALUE+"), "
                + "FOREIGN KEY (" + COLUMN_YEAR_FK + ") REFERENCES "+ YEAR_TABLE +"("+COLUMN_YEAR_ID+"), "
                + "FOREIGN KEY (" + COLUMN_VARIETY_FK + ") REFERENCES "+ VARIETY_TABLE +"("+COLUMN_VARIETY_NAME+"), "
                + "FOREIGN KEY (" + COLUMN_MATERIAL_FK + ") REFERENCES "+ MATERIAL_TABLE +"("+COLUMN_MATERIAL_NAME+"));");
        db.execSQL(tableStatement);



        //Collection_Coin Table
        tableStatement = ("CREATE TABLE " + COLLECTIONS_COIN_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_COLLECTION_FK
                + " INTEGER, " + COLUMN_COIN_FK + " INTEGER, "
                + "FOREIGN KEY (" + COLUMN_COLLECTION_FK + ") REFERENCES "+ COLLECTION_TABLE +"(ID) ,"
                + "FOREIGN KEY (" + COLUMN_COIN_FK + ") REFERENCES "+ COIN_TABLE + "(" + COIN_ID +"));");
        db.execSQL(tableStatement);

        ContentValues cv = new ContentValues();

        //Populate
        //Populate Users Table
            cv.put(COLUMN_USER_NAME,"Default");
            cv.put(COLUMN_STATE,1);
            db.insert(USER_TABLE,null,cv);
            cv.clear();

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
    /*
    tableStatement = ("CREATE TABLE " + USER_COLLECTIONS_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_COLLECTION_FK
                + " INTEGER, " + COLUMN_USER_FK + " INTEGER, "
            + "FOREIGN KEY (" + COLUMN_COLLECTION_FK + ") REFERENCES "+ COLLECTION_TABLE +"(ID) ,"
            + "FOREIGN KEY (" + COLUMN_USER_FK + ") REFERENCES "+ USER_TABLE + "(" + COLUMN_USER_NAME +"));");
        db.execSQL(tableStatement);


     */
    public  ArrayList<Integer> getAllCollectionsForUser(Model_User model_user)
    {
        ArrayList<Integer> collections = new ArrayList<>();
        //SELECT COIN_ID FROM COLLECTION_COIN_TABLE
        // WHERE COLLECTION_ID = 1
        SQLiteDatabase db = this.getReadableDatabase();
            String queryString = "SELECT * FROM " + USER_COLLECTIONS_TABLE;
            Cursor cursor = db.rawQuery(queryString,null);
            try
            {
                if(cursor.moveToFirst())
                {
                    //loop through the cursor result set and create new coin object for each row
                    do{
                        //int userCollectionID = cursor.getInt(0);
                        int collectionID = cursor.getInt(1);
                        String userID = cursor.getString(2);
                        if(userID.equals(model_user.getEmail()))
                        {
                            collections.add(collectionID);
                        }
                    }while (cursor.moveToNext());
                }
                cursor.close();
                return collections;
            }
            catch (Exception e)
            {
                return collections;
            }


    }

    public ArrayList<Model_Collections> getAllCollections() {
        ArrayList<Model_Collections> collectionsList = new ArrayList<>();

        String queryString = "SELECT * FROM " + COLLECTION_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst())
        {
            //loop through the cursor result set and create new coin object for each row
            do{
                int collectionID = cursor.getInt(0);
                String collectionName = cursor.getString(1);
                int goal = cursor.getInt(2);

                Model_Collections collection = new Model_Collections(collectionName,goal);
                collection.setCollectionID(collectionID);

                collectionsList.add(collection);
            }while (cursor.moveToNext());
        }
        else
        {
            //failure means list is empty
        }
        cursor.close();
        return collectionsList;
    }

    public ArrayList<Integer> getAllCoinsWithACollection() {
        ArrayList<Integer> coins = new ArrayList<>();
        //SELECT COIN_ID FROM COLLECTION_COIN_TABLE
        // WHERE COLLECTION_ID = 1
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT " +COLUMN_COIN_FK + " FROM " + COLLECTIONS_COIN_TABLE;
                   // + " WHERE " + COLUMN_COLLECTION_FK + " = " + userCollections.get(i);
        try
        {
            Cursor cursor = db.rawQuery(queryString,null);
            if(cursor.moveToFirst())
            {
                //loop through the cursor result set and create new coin object for each row
                do{
                    int coinID = cursor.getInt(0);

                    coins.add(coinID);
                }while (cursor.moveToNext());
            }
            cursor.close();
            Log.d("allWithColl", "pass: ");
            return coins;
        }catch (Exception e)
        {
            Log.d("allWithColl", "failed: ");
            return coins;
        }


        //}

    }

    public ArrayList<Integer> getAllCoinsInCollection(int collectionID) {
        ArrayList<Integer> coins = new ArrayList<>();
        //SELECT COIN_ID FROM COLLECTION_COIN_TABLE
        // WHERE COLLECTION_ID = 1

        String queryString = "SELECT " +COLUMN_COIN_FK + " FROM " + COLLECTIONS_COIN_TABLE +
                            " WHERE " + COLUMN_COLLECTION_FK + " = " + collectionID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst())
        {
            //loop through the cursor result set and create new coin object for each row
            do{
                int coinID = cursor.getInt(0);

                coins.add(coinID);
            }while (cursor.moveToNext());
        }
        else
        {
            //failure means list is empty
        }
        cursor.close();
        return coins;
    }



    public ArrayList<Integer> getAllYears()
    {
        ArrayList<Integer> years = new ArrayList<>();

        String queryString = "SELECT * FROM " + YEAR_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst())
        {
            //loop through the cursor result set and create
            do{
                int year = cursor.getInt(0);
                years.add(year);
            }while (cursor.moveToNext());
        }
        else
        {
            //failure means list is empty
        }
        cursor.close();
        return years;
    }

    public void deleteCoin(int coinID)
    {
        //String queryString = "DELETE FROM " + COIN_TABLE +
            //    " WHERE ID = " + coinID;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + COIN_TABLE + " WHERE ID = " + coinID);
        //db.delete(COIN_TABLE, "ID = " + coinID, null);
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
                String dateTaken = cursor.getString(6);
                String value = cursor.getString(7);
                int year = cursor.getInt(8);
                String variety = cursor.getString(9);
                String material = cursor.getString(10);


                Model_Coin coin = new Model_Coin(year,mintage,material,altName,observe,reverse,variety,value,image,dateTaken);
                coin.setCoinID(coinID);
                coinsList.add(coin);
            }while (cursor.moveToNext());
        }
        else
        {
            //failure means list is empty
        }
        cursor.close();
        return coinsList;
    }

    public ArrayList<Model_User> getAllUsers()
    {
        ArrayList<Model_User> users = new ArrayList<>();

        String queryString = "SELECT * FROM " + USER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst())
        {
            //loop through the cursor result set and create
            do{
                String email = cursor.getString(0);
                int state  = cursor.getInt(1);
                String password = cursor.getString(2);
                Model_User model_user = new Model_User();
                model_user.setEmail(email);
                model_user.setPassword(password);
                model_user.setState(state);
                users.add(model_user);
            }while (cursor.moveToNext());
        }
        else
        {
            //failure means list is empty
        }
        cursor.close();
        return users;
    }

    public String addCollectionCoin(int collectionID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        int coinID;
            try
            {
                 coinID = getAllCoins().size();
                try
                {
                    //Collections table
                    cv.put(COLUMN_COLLECTION_FK,collectionID);
                    cv.put(COLUMN_COIN_FK,coinID);
                    db.insert(COLLECTIONS_COIN_TABLE,null,cv);
                    cv.clear();
                    return "done";
                }catch (Exception e)
                {
                    return "collections table";
                }
            }catch ( Exception e)
            {
                return "coiniD size";
            }
        }

        public void updateState(Model_User model_user)
        {
            SQLiteDatabase db = this.getWritableDatabase();
            ArrayList<Model_User> users = getAllUsers();

            for (int i=0; i<users.size(); i++)
            {
                if(users.get(i).getState()==1)
                {
                    db.execSQL("UPDATE " + USER_TABLE + " SET " + COLUMN_STATE +" = 0" + " WHERE " + COLUMN_USER_NAME + " = " + users.get(i).getEmail());
                }
            }
            db.execSQL("UPDATE " + USER_TABLE + " SET " + COLUMN_STATE +" = 1" + " WHERE " + COLUMN_USER_NAME + " = " + model_user.getEmail());
        }




        public boolean addUser(Model_User model_user) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            try {
                //Collections table
                cv.put(COLUMN_USER_NAME, model_user.getEmail());
                cv.put(COLUMN_PASSWORD, model_user.getPassword());
                cv.put(COLUMN_STATE,model_user.getState());
                db.insert(USER_TABLE, null, cv);
                cv.clear();
                return true;
            } catch (Exception e) {
                return false;

            }
        }

        public boolean addCollection(Model_Collections model_collections, Model_User model_user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        try {
            //Collections table
            cv.put(COLUMN_COLLECTION_NAME, model_collections.getCollectionName());
            cv.put(COLUMN_GOAL, model_collections.getGoal());
            db.insert(COLLECTION_TABLE, null, cv);
            cv.clear();
            Log.d("addCollection", model_user.getEmail());
        } catch (Exception e) {
            Log.d("addCollection","we failed");
            return false;

        }
        //get all collections
            ArrayList<Model_Collections> model_collectionsArrayList = getAllCollections();
            int newCollectionID = model_collectionsArrayList.get(model_collectionsArrayList.size()-1).getCollectionID();

            try {
                //User Collections table
                cv.put(COLUMN_COLLECTION_FK, newCollectionID);
                cv.put(COLUMN_USER_FK, model_user.getEmail());
                db.insert(USER_COLLECTIONS_TABLE, null, cv);
                cv.clear();
                return true;
            } catch (Exception e) {
                return false;

            }
    }


    public String addCoin(Model_Coin coin, int collectionID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        ArrayList<Integer> years = getAllYears();

        //Year Table
        //Get all from year table, check if year exists if not
        boolean yearFound = false;

        for (int i=0; i<years.size(); i++)
        {
            if(years.get(i) ==coin.getYear())
            {
                yearFound = true;
                break;
            }
        }
        if(!yearFound)
        {
            try
            {
                cv.put(COLUMN_YEAR_ID,coin.getYear());
                db.insert(YEAR_TABLE,null,cv);
                cv.clear();
            }catch (Exception e)
            {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                String sStackTrace = sw.toString();
                Log.d("YEAR", sStackTrace);
                return sStackTrace;
            }
        }



        try
        {
            //Coin Table
            cv.put(COIN_ID, coin.getCoinID());
            cv.put(COLUMN_ALT_NAME,coin.getAlternateName());
            cv.put(COLUMN_DATE_TAKEN,coin.getDateTaken());
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
            return "coin";
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

                }catch (Exception e)
                {
                    return "collection";
                }
            }catch ( Exception e)
            {
                return "coinID";
            }

        }
        else
        {
            return  "collectionid !=0";
        }
        return "done";

        //Collections Coin Table

    }


}
