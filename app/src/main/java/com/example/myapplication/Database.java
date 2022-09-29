package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
// change name
    private Context context;
    private static final String DATABASE_NAME = "Expense.db";
    private static final int DATABASE_VERSION = 1 ;
    // create table data
    private static final String TABLE_NAME = "trip_Expensse" ;
    private static final String COLUMN_ID = "_id" ;
    private static final String COLUMN_NAME = "name" ;
    private static final String COLUMN_DESTINATION = "DESTINATION" ;
    private static final String COLUMN_DATE = "date" ;
    private static final String COLUMN_RICKS = "date" ;
    private static final String COLUMN_DESCRIPTION = "description" ;

    // Database == MyDatabaseHelper
    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
        this.context = context;
    }
// change name
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_DESTINATION + " TEXT, " +
                        COLUMN_DATE + " INTEGER, " +
                        COLUMN_RICKS + " TEXT, " +
                        COLUMN_DESCRIPTION + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
