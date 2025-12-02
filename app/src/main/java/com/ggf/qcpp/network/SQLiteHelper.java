package com.ggf.qcpp.network;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ggf.qcpp.d_hasilpengamatan.hasilPengamatanOffline.model.OfflineModel;
import com.ggf.qcpp.e_formpengamatan.chopper.model.ChopperModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "pengamatanOffline";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "offlineData";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATA = "data";  // This will store the serialized ChopperModel

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " TEXT PRIMARY KEY, " +
                COLUMN_DATA + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Method to save data to SQLite
    public void saveChopperData(String data, String id) {
        Log.d("datasqlsaved" , new Gson().toJson(data));
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID ,id);
        values.put(COLUMN_DATA, data);  // Insert the string data

        // Insert the data into the database
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // You can add a method to retrieve the saved data if needed
    public String getChopperData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_DATA}, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            String data = cursor.getString(cursor.getColumnIndex(COLUMN_DATA));
            cursor.close();
            return data;
        }

        return null;  // No data found
    }

    public List<String> getAllData() {
        List<String> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_DATA}, null, null, null, null, null);

        if (cursor != null) {
            // Loop through all rows
            if (cursor.moveToFirst()) {
                do {
                    String data = cursor.getString(cursor.getColumnIndex(COLUMN_DATA));
                    dataList.add(data);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return dataList;  // Return list of all serialized data
    }

    public boolean deleteDataById(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME, "id = ?", new String[]{id});
        return result > 0;
    }


}
