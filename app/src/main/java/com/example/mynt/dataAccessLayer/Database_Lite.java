package com.example.mynt.dataAccessLayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.mynt.coinsActivity.models.Model_Coin;
import com.example.mynt.collectionsActivity.Model_Collections;

import java.util.ArrayList;

public class Database_Lite extends SQLiteOpenHelper {

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

        //Material Table
        tableStatements.add("CREATE TABLE " + MATERIAL_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_MATERIAL_NAME + " TEXT);");
        //Year Table
        tableStatements.add("CREATE TABLE " + YEAR_TABLE + "(" + COLUMN_YEAR_ID + " INTEGER PRIMARY KEY );");
        //Value Table
        tableStatements.add("CREATE TABLE " + VALUE_TABLE + "(" + COLUMN_VALUE_ID + " REAL PRIMARY KEY, " + COLUMN_NAME_VALUE + " TEXT);");
        //Variety Table
        tableStatements.add("CREATE TABLE " + VARIETY_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_VARIETY_NAME + " TEXT);");
        //Coin Table
        tableStatements.add("CREATE TABLE " + COIN_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ALT_NAME + " TEXT, "
                + COLUMN_MINTAGE + " INTEGER, " + COLUMN_OBSERVE + " TEXT, " + COLUMN_REVERSE + " TEXT, " + COLUMN_VALUE_FK + " REAL, "
                + COLUMN_YEAR_FK + " INTEGER, " + COLUMN_VARIETY_FK + " INTEGER, " + COLUMN_MATERIAL_FK + " INTEGER, "
                + "FOREIGN KEY (" + COLUMN_VALUE_FK + ") REFERENCES "+ VALUE_TABLE +"("+COLUMN_VALUE_ID+"), "
                + "FOREIGN KEY (" + COLUMN_YEAR_FK + ") REFERENCES "+ YEAR_TABLE +"("+COLUMN_YEAR_ID+"), "
                + "FOREIGN KEY (" + COLUMN_VARIETY_FK + ") REFERENCES "+ VARIETY_TABLE +"(ID) , "
                + "FOREIGN KEY (" + COLUMN_MATERIAL_FK + ") REFERENCES "+ MATERIAL_TABLE +"(ID));");
        //Collections Table
        tableStatements.add("CREATE TABLE " + COLLECTION_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_COLLECTION_NAME
                + " TEXT, "+ COLUMN_GOAL + " INTEGER);");
        //Collection_Coin Table
        tableStatements.add("CREATE TABLE " + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "+ COLLECTIONS_COIN_TABLE + "(" + COLUMN_COLLECTION_FK
                + " INTEGER, " + COLUMN_COIN_FK + " INTEGER, " + COLUMN_IMAGE + "FOREIGN KEY ("
                + COLUMN_COIN_FK + ") REFERENCES "+ COIN_TABLE +"(ID), " + COLUMN_IMAGE + "FOREIGN KEY (" + COLUMN_COLLECTION_FK
                + ") REFERENCES "+ COLLECTION_TABLE +"(ID));");

        //Create tables in
        for (int i =0; i<tableStatements.size();i++)
        {
            db.execSQL(tableStatements.get(i));
        }

    }

    //In the event a database version number changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }


    public void populateCoins(Model_Collections collection)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        db.insert(COIN_TABLE,null,cv);
        cv.clear();
    }


}
