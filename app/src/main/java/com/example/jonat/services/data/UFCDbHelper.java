package com.example.jonat.services.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by jonat on 10/16/2017.
 */

public class UFCDbHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "ufc.db";
    private static final String LOG_TAG = UFCDbHelper.class.getSimpleName();
    private static final int DATABASE_VERSION = 3;


    public UFCDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create database table here
        final String SQL_CREATE_FAV_TABLE = "CREATE TABLE " + UFCContract.UFCEntry.TABLE_NAME + " ( " +
                UFCContract.UFCEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                UFCContract.UFCEntry.COLUMN_EVENT_ID + " TEXT UNIQUE NOT NULL, " +
                UFCContract.UFCEntry.COLUMN_EVENT_DATE + " TEXT NOT NULL, " +
                UFCContract.UFCEntry.COLUMN_FEATURE_IMAGE + " TEXT NOT NULL, " +
                UFCContract.UFCEntry.COLUMN_SECONDARY_IMAGE + " TEXT NOT NULL, " +
                UFCContract.UFCEntry.COLUMN_TICKET_IMAGE + " TEXT NOT NULL, " +
                UFCContract.UFCEntry.COLUMN_EVENTS_TIME + " TEXT NOT NULL, " +
                UFCContract.UFCEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
                UFCContract.UFCEntry.COLUMN_END_EVENT + " TEXT NOT NULL, " +
                UFCContract.UFCEntry.COLUMN_TICKET_URL + " TEXT NOT NULL, " +
                UFCContract.UFCEntry.COLUMN_TICKET_SELLER + " TEXT NOT NULL, " +
                UFCContract.UFCEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                UFCContract.UFCEntry.COLUMN_TITLE_TAG + " TEXT NOT NULL, " +
                UFCContract.UFCEntry.COLUMN_TICKET + " TEXT NOT NULL, " +
                UFCContract.UFCEntry.COLUMN_SUBTITLE + " TEXT NOT NULL, " +
                UFCContract.UFCEntry.COLUMN_EVENTS_STATUS + " TEXT NOT NULL, " +
                UFCContract.UFCEntry.COLUMN_CORNER_AUDIO + " TEXT NOT NULL, " +
                UFCContract.UFCEntry.COLUMN_ARENA + " TEXT NOT NULL, " +
                UFCContract.UFCEntry.COLUMN_LOCATION + " TEXT NOT NULL " + ");";
        //gotta do logging
        Log.d(LOG_TAG, SQL_CREATE_FAV_TABLE);

        db.execSQL(SQL_CREATE_FAV_TABLE);

        Log.d(LOG_TAG, "all tables created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //this will be invoked when we will change DATABASE_VERSION aka schema of database.
        // if we upgrade schema user will lost his fav. collection
        //comment this out if you don't want this to happen
        db.execSQL("DROP TABLE IF EXISTS " + UFCContract.UFCEntry.TABLE_NAME);
        onCreate(db);
    }
}


