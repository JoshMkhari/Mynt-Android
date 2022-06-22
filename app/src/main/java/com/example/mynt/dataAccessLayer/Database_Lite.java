package com.example.mynt.dataAccessLayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.mynt.collectionsActivity.models.Model_Coin;
import com.example.mynt.collectionsActivity.models.Model_Collections;
import com.example.mynt.collectionsActivity.models.Model_User;

import java.util.ArrayList;

public class Database_Lite extends SQLiteOpenHelper {

    //Coin Table

    public static final String COLUMN_MATERIAL = "MATERIAL";
    //Material Table
    public static final String COLUMN_YEAR = "YEAR";
    public static final String COLUMN_VALUE = "VALUE";
    public static final String COLUMN_VARIETY = "VARIETY";
    public static final String COIN_TABLE = "COIN_TABLE";
    public static final String COLUMN_ALT_NAME = "ALT_NAME";
    public static final String COLUMN_MINTAGE = "MINTAGE";
    public static final String COLUMN_OBSERVE = "OBSERVE";
    public static final String COLUMN_REVERSE = "REVERSE";
    public static final String COLUMN_IMAGE = "IMAGE";
    private static final String COLUMN_COIN = "COIN_ID";
    private static final String COLUMN_COLLECTION = "COLLECTION_ID";
    private static final String COLLECTIONS_COIN_TABLE = "COLLECTION_COIN_TABLE";
    private static final String COLLECTION_TABLE = "COLLECTION_TABLE";
    private static final String COLUMN_COLLECTION_NAME = "NAME";
    private static final String COLUMN_GOAL = "GOAL";
    private static final String COLUMN_DATE_TAKEN = "DATE_TAKEN";
    private static final String USER_TABLE = "USER_TABLE";
    private static final String COLUMN_PASSWORD = "PASSWORD";
    private static final String COLUMN_LastSync = "SYNC";
    private  static final String COIN_ID = "ID";
    private static final String COLUMN_STATE = "THEME";
    private static final String COLUMN_USER_EMAIL = "EMAIL";
    private static final String COLUMN_USER_NAME = "USER_NAME";
    private static final String COLUMN_USER_PROFILE_PIC = "PROFILE_PIC";

    //Year Table
    //public static final String COLUMN_MATERIAL_NAME = "NAME";

    public Database_Lite(@Nullable Context context) { //(freecodecamp,2020)
        super(context, "user.db", null, 1);
    }

    //First time a database is created
    @Override
    public void onCreate(SQLiteDatabase db) {//(freecodecamp,2020)

        //Table Creation Statements

        //User Table
        String         //User Table
                tableStatement = ("CREATE TABLE " + USER_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_EMAIL + " TEXT, " +
                COLUMN_STATE + " INTEGER, " + COLUMN_PASSWORD + " TEXT, " + COLUMN_LastSync + " TEXT);");
        db.execSQL(tableStatement);

        //Collections Table
        tableStatement = ("CREATE TABLE " + COLLECTION_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_COLLECTION_NAME
                + " TEXT, "+ COLUMN_GOAL + " INTEGER);");
        db.execSQL(tableStatement);

        //Coin Table
        tableStatement = ("CREATE TABLE " + COIN_TABLE + "(" + COIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ALT_NAME + " TEXT, "
                + COLUMN_MINTAGE + " INTEGER, " + COLUMN_OBSERVE + " TEXT, " + COLUMN_REVERSE + " TEXT, "+ COLUMN_IMAGE + " BLOB, "
                + COLUMN_DATE_TAKEN + " TEXT, " + COLUMN_VALUE + " REAL, "
                + COLUMN_YEAR + " INTEGER, " + COLUMN_VARIETY + " INTEGER, " + COLUMN_MATERIAL + " INTEGER);");
        db.execSQL(tableStatement);

        //Collection_Coin Table
        tableStatement = ("CREATE TABLE " + COLLECTIONS_COIN_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_COLLECTION
                + " INTEGER, " + COLUMN_COIN + " INTEGER, "
                + "FOREIGN KEY (" + COLUMN_COLLECTION + ") REFERENCES "+ COLLECTION_TABLE +"(ID) ,"
                + "FOREIGN KEY (" + COLUMN_COIN + ") REFERENCES "+ COIN_TABLE + "(" + COIN_ID +"));");
        db.execSQL(tableStatement);

        ContentValues cv = new ContentValues();

        //Populate
        //Populate Users Table
            cv.put(COLUMN_USER_EMAIL,"DefaultUser");
            cv.put(COLUMN_STATE,1);//0 Means Light Mode, 1 Means dark mode
            db.insert(USER_TABLE,null,cv);
            cv.clear();

    }

    //In the event a database version number changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//(freecodecamp,2020)

    }
    /*
    tableStatement = ("CREATE TABLE " + USER_COLLECTIONS_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_COLLECTION_FK
                + " INTEGER, " + COLUMN_USER_FK + " INTEGER, "
            + "FOREIGN KEY (" + COLUMN_COLLECTION_FK + ") REFERENCES "+ COLLECTION_TABLE +"(ID) ,"
            + "FOREIGN KEY (" + COLUMN_USER_FK + ") REFERENCES "+ USER_TABLE + "(" + COLUMN_USER_NAME +"));");
        db.execSQL(tableStatement);
     */


    public void removeUserData()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("delete from "+ USER_TABLE);
        db.execSQL("delete from "+ COLLECTIONS_COIN_TABLE);
        db.execSQL("delete from "+ COIN_TABLE);
        db.execSQL("delete from "+ COLLECTION_TABLE);

    }

    public ArrayList<Model_Collections> getAllCollections() {//(freecodecamp,2020)
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
        //failure means list is empty

        cursor.close();
        return collectionsList;
    }

    public ArrayList<Integer> getAllCoinsWithACollection() {//(freecodecamp,2020)
        ArrayList<Integer> coins = new ArrayList<>();
        //SELECT COIN_ID FROM COLLECTION_COIN_TABLE
        // WHERE COLLECTION_ID = 1
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT " + COLUMN_COIN + " FROM " + COLLECTIONS_COIN_TABLE;
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
            return coins;
        }catch (Exception e)
        {
            return coins;
        }


        //}

    }

    public ArrayList<Integer> getAllCoinsInCollection(int collectionID) {
        ArrayList<Integer> coins = new ArrayList<>();
        //SELECT COIN_ID FROM COLLECTION_COIN_TABLE
        // WHERE COLLECTION_ID = 1

        String queryString = "SELECT " + COLUMN_COIN + " FROM " + COLLECTIONS_COIN_TABLE +
                            " WHERE " + COLUMN_COLLECTION + " = " + collectionID;
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
        //failure means list is empty

        cursor.close();
        return coins;
    }

    public void deleteCoin(int coinID)//(freecodecamp,2020)
    {
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
                byte[] image = cursor.getBlob(5);
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
        //failure means list is empty

        cursor.close();
        return coinsList;
    }
/*
    String tableStatement = ("CREATE TABLE " + USER_TABLE +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_USER_EMAIL + " TEXT, " +
            COLUMN_THEME + " INTEGER, " +
            COLUMN_PASSWORD + " TEXT, " +
            COLUMN_UUID + " TEXT, " +
            COLUMN_USER_NAME + " TEXT, " +
            COLUMN_USER_PROFILE_PIC + " BLOB);");

 */

    public ArrayList<Model_User> getAllUsers()//(freecodecamp,2020)
    {
        ArrayList<Model_User> users = new ArrayList<>();

        String queryString = "SELECT * FROM " + USER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst())
        {
            //loop through the cursor result set and create
            do{
                int userID = cursor.getInt(0);
                String email = cursor.getString(1);
                int state  = cursor.getInt(2);
                String password = cursor.getString(3);
                String lastSync = cursor.getString(4);


                Model_User model_user = new Model_User(email,password,state);
                model_user.setUserID(userID);
                model_user.setLastSync(lastSync);
                model_user.setState(state);
                users.add(model_user);
            }while (cursor.moveToNext());
        }
        //failure means list is empty

        cursor.close();
        return users;
    }

    public void addCollectionCoin(int collectionID)//(freecodecamp,2020)
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
                    cv.put(COLUMN_COLLECTION,collectionID);
                    cv.put(COLUMN_COIN,coinID);
                    db.insert(COLLECTIONS_COIN_TABLE,null,cv);
                    cv.clear();
                }catch (Exception ignored)
                {
                }
            }catch ( Exception ignored)
            {
            }
        }

        public void updateState(Model_User model_user)//(geeksforgeeks, 2021)
        {
            SQLiteDatabase db = this.getWritableDatabase();
            ArrayList<Model_User> users = getAllUsers();
            ContentValues cv = new ContentValues();
            for (int i=0; i<users.size(); i++)
            {
                if(users.get(i).getState()==1)
                {
                    cv.put(COLUMN_STATE, 0);
                    db.update(USER_TABLE,cv, COLUMN_STATE + " = 1",null);
                    cv.clear();
                    break;
                }
            }
            cv.put(COLUMN_STATE, 1);
            db.update(USER_TABLE,cv,COLUMN_USER_EMAIL+" = ",new String[]{model_user.getEmail()});
            cv.clear();
        }

        public String updateUserLastSync(Model_User model_user)
        {
            try {
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put(COLUMN_LastSync, model_user.getLastSync());
                // db.update(USER_TABLE,cv,"ID=1",null);
                db.update(USER_TABLE,cv,COLUMN_USER_EMAIL + "=?",new String[]{model_user.getEmail()});
                cv.clear();
                return "Success";
            }catch (Exception ignored)
            {
                return "Faild";
            }

        }


    public String addUser(Model_User model_user) {//(freecodecamp,2020)
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        ArrayList<Model_User> users = getAllUsers();
        String oldUser ="DefaultUser";

        ArrayList<Model_User> AllUsers = getAllUsers();

        for (int i=0; i<AllUsers.size(); i++)
        {
            if (AllUsers.get(i).getUserID() == model_user.getUserID())
            {
                model_user = AllUsers.get(i);
            }
        }
        try
        {
            if (users.get(0).getEmail().equals(oldUser)) {
                cv.put(COLUMN_USER_EMAIL, model_user.getEmail());
                cv.put(COLUMN_PASSWORD, model_user.getPassword());
                cv.put(COLUMN_LastSync, model_user.getLastSync());
                // db.update(USER_TABLE,cv,"ID=1",null);
                db.update(USER_TABLE,cv,COLUMN_USER_EMAIL + "=?",new String[]{oldUser});
                return "true switch";
            }
            else {
                try {
                    //User table
                    cv.put(COLUMN_USER_EMAIL, model_user.getEmail());
                    cv.put(COLUMN_PASSWORD, model_user.getPassword());
                    cv.put(COLUMN_LastSync, model_user.getLastSync());
                    cv.put(COLUMN_STATE, model_user.getState());
                    db.insert(USER_TABLE, null, cv);
                    cv.clear();

                    return "true";
                } catch (Exception e) {

                    return "false";
                }
            }
        }catch (Exception e)
        {

            return "false switch";
        }
    }


    public void updateCollection(Model_Collections model_collections)//(geeksforgeeks, 2021)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_GOAL, model_collections.getGoal());
        db.update(COLLECTION_TABLE,cv,"ID = " + model_collections.getCollectionID(),null);
        cv.clear();
    }

    public void addCollection(Model_Collections model_collections) {//(Section, 2021)
        SQLiteDatabase db = this.getWritableDatabase();//(freecodecamp,2020)
        ContentValues cv = new ContentValues();
        try {
            //Collections table
            cv.put(COLUMN_COLLECTION_NAME, model_collections.getCollectionName());
            cv.put(COLUMN_GOAL, model_collections.getGoal());
            db.insert(COLLECTION_TABLE, null, cv);
            cv.clear();
        } catch (Exception ignored) {

        }
    }


    public void addCoin(Model_Coin coin, int collectionID)//(freecodecamp,2020)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        try
        {
            //Coin Table
            cv.put(COIN_ID, coin.getCoinID());
            cv.put(COLUMN_ALT_NAME,coin.getAlternateName());
            cv.put(COLUMN_DATE_TAKEN,coin.getDateAcquired());
            cv.put(COLUMN_MINTAGE,coin.getMintage());
            cv.put(COLUMN_OBSERVE,coin.getObserve());
            cv.put(COLUMN_REVERSE,coin.getReverse());
            cv.put(COLUMN_VALUE,coin.getValue());
            cv.put(COLUMN_YEAR,coin.getYear());
            cv.put(COLUMN_IMAGE,coin.getImageId());
            cv.put(COLUMN_VARIETY,coin.getVariety());
            cv.put(COLUMN_MATERIAL,coin.getMaterial());
            db.insert(COIN_TABLE,null,cv);
            cv.clear();
        }catch (Exception e)
        {
            return;
        }
        if(collectionID != 0)
        {
            try
            {
                int coinID = getAllCoins().size();
                try
                {
                    //Collections table
                    cv.put(COLUMN_COLLECTION,collectionID);
                    cv.put(COLUMN_COIN,coinID);
                    db.insert(COLLECTIONS_COIN_TABLE,null,cv);
                    cv.clear();

                }catch (Exception ignored)
                {
                }
            }catch ( Exception ignored)
            {
            }

        }
    }


}
