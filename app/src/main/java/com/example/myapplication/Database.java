package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    // change name
    private Context context;
    private static final String DATABASE_NAME = "Trip.db";
    private static final int DATABASE_VERSION = 1;
    // create table data of trip
    private static final String TABLE_TRIP = "my_trip";
    private static final String COLUMN_ID = "trip_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESTINATION = "destination";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_RISKS = "risks";
    private static final String COLUMN_DESCRIPTION = "description";

    // create table data of expense
    private static final String TABLE_EXPENSE = "my_expense";
    private static final String EXPENSE_ID = "expense_id";
    private static final String EXPENSE_ID_TRIP = "expense_trip_id";
    private static final String EXPENSE_TYPE = "type";
    private static final String EXPENSE_AMOUNT = "amount";
    private static final String EXPENSE_TIME = "time";

    // Database == MyDatabaseHelper
    Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    // 
    @Override
    // table of trip
    public void onCreate(SQLiteDatabase db) {

        String queryTableTrip = "CREATE TABLE " + TABLE_TRIP +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DESTINATION + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_RISKS + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT);";
        db.execSQL(queryTableTrip);

        String queryTableExpense = "CREATE TABLE " + TABLE_EXPENSE +
                " (" + EXPENSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EXPENSE_TYPE + " TEXT, " +
                EXPENSE_AMOUNT + " TEXT, " +
                EXPENSE_TIME + " TEXT, " +
                EXPENSE_ID_TRIP + " INTEGER,  " +
                " FOREIGN KEY (" + EXPENSE_ID_TRIP + ") REFERENCES "
                + TABLE_TRIP + "( " + COLUMN_ID + " ))";
        db.execSQL(queryTableExpense);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIP);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSE );
        onCreate(db);
    }
    //******** ADD TRIP **********//
    void addDataTrip(String name, String destination, String date, String risks, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DESTINATION, destination);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_RISKS, risks);
        cv.put(COLUMN_DESCRIPTION, description);
        long result = db.insert(TABLE_TRIP, null, cv);
        // báo kết quả
        if (result == 0) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show();
        return;
        }
    }
    Cursor readAllDataTrip(){
        String query = "SELECT * FROM " + TABLE_TRIP;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null ;
        if(db !=null){
            cursor =db.rawQuery(query, null);
        }
        return cursor;
    }
    //******** ADD EXPENSE **********//
    void addDataExpense(String expense_trip_id, String type, String amount, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(EXPENSE_ID_TRIP, expense_trip_id);
        cv.put(EXPENSE_TYPE, type);
        cv.put(EXPENSE_AMOUNT, amount);
        cv.put(EXPENSE_TIME, time);
        long result = db.insert(TABLE_EXPENSE, null, cv);
        // báo kết quả
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show();
            return;
        }
    }
    Cursor readAllDataExpense(String trip_Id){
        String query = "SELECT * FROM " + TABLE_EXPENSE + " WHERE " + EXPENSE_ID_TRIP + " = " + trip_Id ;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null ;
        if(db !=null){
            cursor =db.rawQuery(query, null);
        }
        return cursor;
    }
    //******** ADD EXPENSE **********//

    //*********************************** UPDATE ***********************************//
    void updateDataTrip(String row_id, String name, String destination, String date, String risks, String description){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DESTINATION, destination);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_RISKS, risks);
        cv.put(COLUMN_DESCRIPTION, description);

        long result = db.update(TABLE_TRIP, cv, "trip_id=?", new String[]{row_id});

        if(result == -1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        }else  {
            Toast.makeText(context, "Successfully Update ! ", Toast.LENGTH_SHORT).show();

        }
    }
    //*********************************** DELETE *********************************** //
    void deleteDataTrip(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_TRIP, "trip_id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Failed to Deleted.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_TRIP);
    }
}
