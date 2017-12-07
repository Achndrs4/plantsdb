package com.android.database;

import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteQueryBuilder;
import android.database.sqlite.SQLiteStatement;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class PlantsDB extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "plants.sqlite";
    private static final int DATABASE_VERSION = 1;

    public PlantsDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Cursor getName(String sci_name) {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();


        Cursor c = db.rawQuery("SELECT CommonName FROM plants WHERE ScientificName=? OR CommonName=?", new String[] {sci_name, sci_name});

        c.moveToFirst();
        return c;

    }

    public Cursor getSymbol(String sci_name) {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();


        Cursor c = db.rawQuery("SELECT AcceptedSymbol FROM plants WHERE ScientificName=? OR CommonName=?", new String[] {sci_name, sci_name});

        c.moveToFirst();
        return c;

    }

    public Cursor getFamilyName(String sci_name) {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();


        Cursor c = db.rawQuery("SELECT FamilyCommonName FROM plants WHERE ScientificName=? OR CommonName=?", new String[] {sci_name, sci_name});

        c.moveToFirst();
        return c;

    }

    public Cursor getState(String sci_name) {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();


        Cursor c = db.rawQuery("SELECT State FROM plants WHERE ScientificName=? OR CommonName=?", new String[] {sci_name, sci_name});

        c.moveToFirst();
        return c;

    }

    public Cursor getTemp(String sci_name) {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();


        Cursor c = db.rawQuery("SELECT TemperatureMinimum FROM plants WHERE ScientificName=? OR CommonName=?", new String[] {sci_name, sci_name});

        c.moveToFirst();
        return c;

    }

    public Cursor getEdible(String sci_name) {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();


        Cursor c = db.rawQuery("SELECT PalatableHuman FROM plants WHERE ScientificName=? OR CommonName=?", new String[] {sci_name, sci_name});

        c.moveToFirst();
        return c;

    }

    public Cursor getLifespan(String sci_name) {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();


        Cursor c = db.rawQuery("SELECT Lifespan FROM plants WHERE ScientificName=? OR CommonName=?", new String[] {sci_name, sci_name});

        c.moveToFirst();
        return c;

    }


    public Cursor rawQuery(String s, String[] strings, PlantsDB db) {
        return db.rawQuery("SELECT Lifespan FROM plants WHERE ScientificName=? OR CommonName=?", new String[] {strings[1], strings[2]},null);
    }


}