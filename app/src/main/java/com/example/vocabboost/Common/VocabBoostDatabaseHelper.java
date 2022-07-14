package com.example.vocabboost.Common;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;
import android.util.Log;

public class VocabBoostDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "vocabboost1"; // the name of our database
    private static final int DB_VERSION = 1;
    private  SQLiteDatabase mydb;
    public VocabBoostDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.d("First:","Constructor");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //mydb=db;
        Log.d("First:","Database created");
        db.execSQL("CREATE TABLE CATAGORY ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "GENRE TEXT);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mydb=db;
    }

}
