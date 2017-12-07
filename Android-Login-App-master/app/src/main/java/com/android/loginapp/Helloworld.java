package com.android.loginapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.database.CountriesDbAdapter;
import com.android.database.PlantsDB;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Helloworld extends Activity {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello);


        Button clickButton = (Button) findViewById(R.id.enter);


        clickButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String usertext = ((EditText)findViewById(R.id.editText)).getText().toString();
                Intent mIntent = new Intent(Helloworld.this, AndroidListViewCursorAdaptorActivity.class);

                PlantsDB db= new PlantsDB(Helloworld.this);
                Cursor c=db.getFamilyName(usertext);



                if ((c != null) && (c.getCount() > 0)) {
                    mIntent.putExtra("KEY", usertext);
                    startActivity(mIntent);
                }

            }
        });


        Button clickButton2 = (Button) findViewById(R.id.remove);

        clickButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String userRemove = ((EditText)findViewById(R.id.rm)).getText().toString();

                    Intent mIntent = new Intent(Helloworld.this, AndroidListViewCursorAdaptorActivity.class);
                PlantsDB db= new PlantsDB(Helloworld.this);
                Cursor c=db.getFamilyName(userRemove);
                if((c != null) && (c.getCount() > 0)){
                    mIntent.putExtra("REMOVE", userRemove);
                    startActivity(mIntent);
                }

            }
        });

        Button clickButton5 = (Button) findViewById(R.id.button2);

        clickButton5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent mIntent = new Intent(Helloworld.this, myPlants.class);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                    startActivity(mIntent);
                }

            });


        Button clickButton3 = (Button) findViewById(R.id.button);

        clickButton3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String userUpdate = ((EditText)findViewById(R.id.editText2)).getText().toString();

                Intent mIntent = new Intent(Helloworld.this, AndroidListViewCursorAdaptorActivity.class);
                PlantsDB db= new PlantsDB(Helloworld.this);
                List<String> elephantList = Arrays.asList(userUpdate.split(","));
                String str= elephantList.get(0).toString();
                Log.d("BBBBBBBBBBBBBB",str);
                Cursor c=db.getFamilyName(str);
                if((c != null) && (c.getCount() > 0)){
                    mIntent.putExtra("UPDATE", userUpdate);
                    startActivity(mIntent);
                }

            }
        });

    }
}