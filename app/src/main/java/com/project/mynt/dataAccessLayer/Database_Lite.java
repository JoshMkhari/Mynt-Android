package com.project.mynt.dataAccessLayer;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mynt.R;
import com.project.mynt.coinsActivity.models.Model_Coin;
import com.project.mynt.coinsActivity.models.Model_UserCoin;
import com.project.mynt.collectionsActivity.Model_Collections;
import com.project.mynt.userActivity.Model_User;

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
    public static final String COLUMN_VALUE_ID = COLUMN_VALUE_FK + "_ID";
    public static final String COLUMN_VARIETY_FK = "VARIETY";
    private static final String VARIETY_TABLE = COLUMN_VARIETY_FK + "_TABLE";
    private static final String COLUMN_VARIETY_NAME = "NAME";
    public static final String COIN_TABLE = "COIN_TABLE";
    public static final String COLUMN_ALT_NAME = "ALT_NAME";
    public static final String COLUMN_MINTAGE = "MINTAGE";
    public static final String COLUMN_OBSERVE = "OBSERVE";
    public static final String COLUMN_REVERSE = "REVERSE";
    public static final String COLUMN_IMAGE = "IMAGE";
    private static final String COLUMN_COIN_FK = "COIN";
    private static final String COLUMN_COLLECTION_FK = "ID";
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

        ArrayList<String> tableStatements = new ArrayList<>();

        //Table Creation Statements

        //Material Table
        tableStatements.add("CREATE TABLE " + MATERIAL_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_MATERIAL_NAME + " INTEGER);");
        //Year Table
        tableStatements.add("CREATE TABLE " + YEAR_TABLE + "(" + COLUMN_YEAR_ID + " INTEGER PRIMARY KEY );");
        //Value Table
        tableStatements.add("CREATE TABLE " + VALUE_TABLE + "(" + COLUMN_VALUE_ID + " TEXT PRIMARY KEY, " + COLUMN_NAME_VALUE + " INTEGER);");
        //Variety Table
        tableStatements.add("CREATE TABLE " + VARIETY_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_VARIETY_NAME + " INTEGER);");
        //Coin Table

        tableStatements.add("CREATE TABLE " + COIN_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ALT_NAME + " TEXT, "
                + COLUMN_MINTAGE + " INTEGER, " + COLUMN_OBSERVE + " TEXT, " + COLUMN_REVERSE + " TEXT, "+ COLUMN_IMAGE + " TEXT, " + COLUMN_VALUE_FK + " REAL, "
                + COLUMN_YEAR_FK + " INTEGER, " + COLUMN_VARIETY_FK + " INTEGER, " + COLUMN_MATERIAL_FK + " INTEGER, "
                + "FOREIGN KEY (" + COLUMN_VALUE_FK + ") REFERENCES "+ VALUE_TABLE +"("+COLUMN_VALUE_ID+"), "
                + "FOREIGN KEY (" + COLUMN_YEAR_FK + ") REFERENCES "+ YEAR_TABLE +"("+COLUMN_YEAR_ID+"), "
                + "FOREIGN KEY (" + COLUMN_VARIETY_FK + ") REFERENCES "+ VARIETY_TABLE +"(ID) , "
                + "FOREIGN KEY (" + COLUMN_MATERIAL_FK + ") REFERENCES "+ MATERIAL_TABLE +"(ID));");
        //Collections Table
        tableStatements.add("CREATE TABLE " + COLLECTION_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_COLLECTION_NAME
                + " TEXT, "+ COLUMN_GOAL + " INTEGER);");
        //Collection_Coin Table
        tableStatements.add("CREATE TABLE " + COLLECTIONS_COIN_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_COLLECTION_FK
                + " INTEGER, " + COLUMN_COIN_FK + " INTEGER, " + "FOREIGN KEY ("
                + COLUMN_COIN_FK + ") REFERENCES "+ COIN_TABLE +"(ID), " + "FOREIGN KEY (" + COLUMN_COLLECTION_FK
                + ") REFERENCES "+ COLLECTION_TABLE +"(ID));");

        //Create tables in database
        for (int i =0; i<tableStatements.size();i++)
        {
            db.execSQL(tableStatements.get(i));
        }

        ContentValues cv = new ContentValues();

        //Populate Materials Table
        String[] dataList = res.getStringArray(R.array.Material);
        for (int i =0; i<dataList.length;i++)
        {
            cv.put(COLUMN_MATERIAL_NAME,i);
            db.insert(MATERIAL_TABLE,null,cv);
            cv.clear();
        }

        //Populate Value Table
        dataList = res.getStringArray(R.array.Value);
        for (int i =0; i<dataList.length;i++)
        {
            cv.put(COLUMN_NAME_VALUE,i);
            db.insert(VALUE_TABLE,null,cv);
            cv.clear();
        }

        //Populate Variety Table
        dataList = res.getStringArray(R.array.Variants);
        for (int i =0; i<dataList.length;i++)
        {
            cv.put(COLUMN_VARIETY_NAME,i);
            db.insert(VARIETY_TABLE,null,cv);
            cv.clear();
        }

    }

    //In the event a database version number changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


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
                int value = cursor.getInt(6);
                int year = cursor.getInt(7);
                int variety = cursor.getInt(8);
                int material = cursor.getInt(9);


                Model_Coin coin = new Model_Coin(year,mintage,material,altName,observe,reverse,variety,value,image);
                coinsList.add(coin);
            }while (cursor.moveToNext());
        }
        else
        {
            //failure means list is empty
        }
        cursor.close();
        db.close();;
        return coinsList;
    }

    public Model_User getUser(String email)
    {
        Model_User user = new Model_User();
        return user;
    }
    public boolean addCoin(Model_Collections collection)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        try
        {
            //Collections table
            cv.put(COLUMN_COLLECTION_NAME,collection.getCollectionName());
            cv.put(COLUMN_GOAL,collection.getGoal());
            db.insert(COLLECTION_TABLE,null,cv);
            cv.clear();
        }catch (Exception e)
        {
            Toast.makeText(context,"Collection Table",Toast.LENGTH_LONG).show();
            return false;
        }

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

        try
        {
            //Coin Table
            cv.put(COLUMN_ALT_NAME,coin.getAlternateName());
            cv.put(COLUMN_MINTAGE,coin.getMintage());
            cv.put(COLUMN_OBSERVE,coin.getObserve());
            cv.put(COLUMN_REVERSE,coin.getReverse());
            cv.put(COLUMN_VALUE_FK,coin.getValue());
            cv.put(COLUMN_YEAR_FK,coin.getYear());
            cv.put(COLUMN_VARIETY_FK,coin.getVariety());
            cv.put(COLUMN_MATERIAL_FK,coin.getMaterial());
            db.insert(YEAR_TABLE,null,cv);
            cv.clear();
            return true;
        }catch (Exception e)
        {
            Toast.makeText(context,"Coin Table",Toast.LENGTH_LONG).show();
            return false;
        }



        //Collections Coin Table

    }

}
