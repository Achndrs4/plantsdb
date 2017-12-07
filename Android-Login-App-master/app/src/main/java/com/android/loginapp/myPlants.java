package com.android.loginapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.database.CountriesDbAdapter;
import com.android.database.PlantsDB;

import java.util.Random;

import static android.R.id.list;

/**
 * Created by achndrs4 on 11/17/17.
 */

public class
        myPlants extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.check);
        CountriesDbAdapter dbHelper = new CountriesDbAdapter(this);
        PlantsDB pdb= new PlantsDB(this);
        dbHelper.open();

        String[] plants={"Coccoloba",
                "Coccotrema",
                "Coccotrema pocillarium",
                "Brya",
                "Opuntia cochenillifera",
                "Cochleanthes",
                "Ramalina cochlearis",
                "Cochlearia tridactylites"
,                "Cochlospermum",
                "Celosia",
                "Erythrina eggersii",
                "Xanthium",
                "Hypocenomyce",
                "Hypocenomyce castaneocinerea",
                "Hypocenomyce leucococca",
                "Hypocenomyce praestabilis",
                "Hypocenomyce scalaris",
                "Hypocenomyce sorophora",

                "Hypocenomyce xanthococca",
                "Solanum capsicoides",
                "Haplophyton crooksii",
                "Onobrychis caput-galli",
                "Echinochloa haploclada",
                "Echinochloa spiralis",
                "Echinochloa",
                "Crataegus crus-galli",
                "Opuntia pusilla",
                "Chrysobalanus icaco",
                "Colocasia esculenta",
                "Colocasia esculenta",
                "Colocasia esculenta var. esculenta",
                "Solanum hyporhodium",
                "Solanum sessiliflorum",
                "Cocos",
                };
        EditText usertext = ((EditText)findViewById(R.id.textView5));
        int r = (int) (Math.random()*plants.length);
        usertext.setText(plants[r]);







    }
}


















