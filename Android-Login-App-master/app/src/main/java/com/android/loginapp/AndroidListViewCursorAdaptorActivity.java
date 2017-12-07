package com.android.loginapp;

/**
 * Created by achndrs4 on 12/5/17.
 */

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.android.database.CountriesDbAdapter;
import com.android.database.PlantsDB;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class AndroidListViewCursorAdaptorActivity extends Activity {

    private CountriesDbAdapter dbHelper;
    private SimpleCursorAdapter dataAdapter;
    private PlantsDB pdb;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cmain);

        dbHelper = new CountriesDbAdapter(this);
        pdb= new PlantsDB(this);
        dbHelper.open();

        String value = getIntent().getExtras().getString("KEY");
        if(value!=null)
        {
            dbHelper.add(value, pdb);
        }
        else {
            value = getIntent().getExtras().getString("REMOVE");
            if (value != null) {
                dbHelper.delete(value);
            }
        }

        value=getIntent().getExtras().getString("UPDATE");
        if(value!=null)
        {
            dbHelper.updateContact(value);
        }





         displayListView();



    }

    private void displayListView() {


        Cursor cursor = dbHelper.fetchAllCountries();

        // The desired columns to be bound
        String[] columns = new String[] {
                CountriesDbAdapter.KEY_NAME,
                CountriesDbAdapter.KEY_STATE,
                CountriesDbAdapter.KEY_FAMILY_NAME,
                CountriesDbAdapter.KEY_SYMBOL,
                CountriesDbAdapter.KEY_EDIBLE,
                CountriesDbAdapter.KEY_LIFESPAN,
                CountriesDbAdapter.KEY_MIN_TEMP};


        // the XML defined views which the data will be bound to
        int[] to = new int[] {
                R.id.code,
                R.id.region,
                R.id.continent,
                R.id.name,
                R.id.region3,
                R.id.region4,
                R.id.region2
        };

        // create the adapter using the cursor pointing to the desired data
        //as well as the layout information

        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.country_info,
                cursor,
                columns,
                to,
                0);


        ListView listView = (ListView) findViewById(R.id.listView1);
        if(listView!=null) {
            // Assign adapter to ListView
            listView.setAdapter(dataAdapter);


            listView.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    dbHelper.delete(findViewById(R.id.name).toString());
                }


            });



            EditText myFilter = (EditText) findViewById(R.id.myFilter);
            myFilter.addTextChangedListener(new TextWatcher() {

                public void afterTextChanged(Editable s) {
                }

                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start,
                                          int before, int count) {
                    dataAdapter.getFilter().filter(s.toString());
                }
            });

            dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {
                public Cursor runQuery(CharSequence constraint) {
                    return dbHelper.fetchCountriesByName(constraint.toString());
                }
            });
        }
    }
}
