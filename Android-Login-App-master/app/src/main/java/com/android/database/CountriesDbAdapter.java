package com.android.database;

/**
 * Created by achndrs4 on 12/5/17.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CountriesDbAdapter {

    public static final String KEY_SYMBOL = "Symbol";
    public static final String KEY_FAMILY_NAME = "FamilyCommonName";
    public static final String KEY_NAME = "ScientificName";
    public static final String KEY_EDIBLE = "PalatableHuman";
    public static final String KEY_LIFESPAN = "Lifespan";
    public static final String KEY_STATE = "State";
    public static final String KEY_MIN_TEMP = "TemperatureMinimum";
    public static final String KEY_ROWID = "_id";



    private static final String TAG = "CountriesDbAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    public static PlantsDB pdb;
    private static final String DATABASE_NAME = "userPlants";
    private static final String SQLITE_TABLE = "userplants";
    private static final int DATABASE_VERSION = 1;

    private final Context mCtx;

    private static final String DATABASE_CREATE =
            "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
                    KEY_ROWID + " integer PRIMARY KEY autoincrement," +
                    KEY_EDIBLE + "," +
                    KEY_FAMILY_NAME + "," +
                    KEY_SYMBOL + "," +
                    KEY_LIFESPAN + "," +
                    KEY_NAME + "," +
                    KEY_STATE + "," +
                    KEY_MIN_TEMP + ");";

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.w(TAG, DATABASE_CREATE);
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);

            onCreate(db);
        }
    }

    public CountriesDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    public CountriesDbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }


    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long add(String name, PlantsDB db) {

        ContentValues initialValues = new ContentValues();
        String str = "";
        Cursor c = db.getTemp(name);
        if (c != null && c.moveToFirst()) {
            str = c.getString(0);
        }
        initialValues.put(KEY_MIN_TEMP, str);

        c = db.getName(name);
        if (c != null && c.moveToFirst()) {
            str = c.getString(0);
        }
        initialValues.put(KEY_NAME, str);

        c = db.getLifespan(name);
        if (c != null && c.moveToFirst()) {
            str = c.getString(0);
        }
        initialValues.put(KEY_LIFESPAN, str);

        c = db.getFamilyName(name);
        if (c != null && c.moveToFirst()) {
            str = c.getString(0);
        }
        initialValues.put(KEY_FAMILY_NAME, str);

        c = db.getEdible(name);
        if (c != null && c.moveToFirst()) {
            str = c.getString(0);
        }
        initialValues.put(KEY_EDIBLE, str);

        c = db.getState(name);
        if (c != null && c.moveToFirst()){
            str = c.getString(0);
        }
        String new_str =str.replace("<strong>","");
        String new_new=new_str.replace("</strong>", "");
        initialValues.put(KEY_STATE, new_new.substring(0,3));
        initialValues.put(KEY_ROWID,  (int)(Math.random()*1000));




        c = db.getSymbol(name);
        if (c != null && c.moveToFirst()) {
            str = c.getString(0);
        }
        initialValues.put(KEY_SYMBOL, str);

        return mDb.insert(SQLITE_TABLE, null, initialValues);

    }

    public boolean delete(String inputText) throws SQLException  {

       int doneDelete = mDb.delete(SQLITE_TABLE, KEY_NAME + "=?" , new String[]{inputText});
        return doneDelete>0;


    }


    public boolean updateContact (String str) {

        List<String> elephantList = Arrays.asList(str.split(","))
                ;
        ContentValues contentValues = new ContentValues();
        contentValues.put( KEY_NAME, elephantList.get(0));
        contentValues.put(KEY_STATE, elephantList.get(1));
        contentValues.put(KEY_FAMILY_NAME, elephantList.get(2));
        contentValues.put(KEY_SYMBOL, elephantList.get(3));
        contentValues.put(KEY_EDIBLE, elephantList.get(4));
        contentValues.put(KEY_LIFESPAN,  elephantList.get(5));
        contentValues.put(KEY_MIN_TEMP , elephantList.get(6));


        mDb.update(SQLITE_TABLE,contentValues, KEY_NAME + "=?" , new String[]{elephantList.get(0)});
        return true;
    }

    public Cursor fetchCountriesByName(String inputText) throws SQLException {
        Log.w(TAG, inputText);
        Cursor mCursor = null;
        if (inputText == null || inputText.length() == 0) {
            mCursor = mDb.query(SQLITE_TABLE, new String[]{KEY_NAME,KEY_ROWID,
                            KEY_FAMILY_NAME, KEY_STATE, KEY_LIFESPAN, KEY_MIN_TEMP, KEY_EDIBLE, KEY_SYMBOL},
                    null, null, null, null, null);

        } else {
            mCursor = mDb.query(true, SQLITE_TABLE, new String[]{KEY_NAME,KEY_ROWID,
                            KEY_FAMILY_NAME, KEY_STATE, KEY_LIFESPAN, KEY_MIN_TEMP, KEY_EDIBLE, KEY_SYMBOL},
                    KEY_NAME + " like '%" + inputText + "%'", null,
                    null, null, null, null);
        }
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    public Cursor fetchAllCountries() {

        Cursor mCursor = mDb.query(SQLITE_TABLE, new String[]{KEY_NAME,KEY_ROWID,
                        KEY_FAMILY_NAME, KEY_STATE, KEY_LIFESPAN, KEY_MIN_TEMP, KEY_EDIBLE, KEY_SYMBOL},
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
}



