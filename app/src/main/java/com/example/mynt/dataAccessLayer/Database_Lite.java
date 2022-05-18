package com.example.mynt.dataAccessLayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.mynt.coinsActivity.models.Model_Coin;

public class Database_Lite extends SQLiteOpenHelper {

    public static final String COIN_TABLE = "COIN_TABLE";
    public static final String COLUMN_VALUE = "VALUE";
    public static final String COLUMN_ALTERIOR_NAME = "ALTERIOR_NAME";
    public static final String COLUMN_YEAR = "YEAR";
    public static final String COLUMN_MINTAGE = "MINTAGE";
    public static final String COLUMN_VARIETY = "VARIETY";
    public static final String COLUMN_OBSERVE = "OBSERVE";
    public static final String COLUMN_REVERSE = "REVERSE";
    public static final String COLUMN_CIRCULATION = "CIRCULATION";
    public static final String COLUMN_TYPE = "TYPE";

    public Database_Lite(@Nullable Context context) {
        super(context, "user.db", null, 1);
    }

    //First time a database is created
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE "+COIN_TABLE+ "(CoinID PRIMARY KEY AUTOINCREMENT, " + " TEXT, " + COLUMN_VALUE + " INTEGER, " + COLUMN_ALTERIOR_NAME + " TEXT, " + COLUMN_YEAR + " INTEGER, " + COLUMN_MINTAGE + " INTEGER, " + COLUMN_VARIETY + " TEXT, " + COLUMN_OBSERVE + " TEXT, " + COLUMN_REVERSE + " TEXT, " + COLUMN_CIRCULATION + " BOOL, " + COLUMN_TYPE + " TEXT)";
        //String createTableStatement = "CREATE TABLE COIN_TABLE(CoinID PRIMARY KEY AUTOINCREMENT)";
        db.execSQL(createTableStatement);

    }

    //In the event a database version number changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    public void populateCoins()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_VALUE,"5");
        cv.put(COLUMN_ALTERIOR_NAME,"Presidential Inauguration");
        cv.put(COLUMN_YEAR,"1994");
        cv.put(COLUMN_MINTAGE,"10105000");
        cv.put(COLUMN_VARIETY,"Proof");
        cv.put(COLUMN_OBSERVE,"SOUTH AFRICA SUID-AFRIKA 1994");
        cv.put(COLUMN_REVERSE,"5 RAND");
        cv.put(COLUMN_CIRCULATION,"True");
        cv.put(COLUMN_TYPE,"Copper");

        db.insert(COIN_TABLE,null,cv);
        cv.clear();
    }


}
