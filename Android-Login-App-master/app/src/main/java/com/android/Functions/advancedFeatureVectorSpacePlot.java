package com.android.Functions;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static android.R.attr.value;
import static java.util.Map.*;

/**
 * Created by achndrs4 on 12/6/17.
 */

enum PalatableHuman {YES , NO}
enum BloomPeriod {LATE_SPRING, MID_SUMMER, SPRING, EARLY_SPRING, FALL, INDETERMINATE, LATE_SUMMER;}
enum Lifespan {SHORT, MODERATE, LONG;}
enum GrowthRate {MODERATE, RAPID, SLOW};

@SuppressWarnings("deprecation")
public class advancedFeatureVectorSpacePlot {
    public int getPopularElement(Integer[] a) {
        int count = 1, tempCount;
        int popular = a[0];
        int temp = 0;
        for (int i = 0; i < (a.length - 1); i++) {
            temp = a[i];
            tempCount = 0;
            for (int j = 1; j < a.length; j++) {
                if (temp == a[j])
                    tempCount++;
            }
            if (tempCount > count) {
                popular = temp;
                count = tempCount;
            }
        }
        return popular;
    }

    public static double dotProd(Integer[] a, int[] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("The dimensions have to be equal!");
        }
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] * b[i];
        }
        return sum;
    }

    public String[] findSimilar(SQLiteDatabase db)

    {
        String[] sciNames = new String[100]; //100 is just some constant


        Cursor cur = db.rawQuery("SELECT * FROM " + "userplants", null);
        ArrayList temp = new ArrayList();
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    temp.add(cur.getString(cur.getColumnIndex("ScientificName"))); // "Title" is the field name(column) of the Table
                } while (cur.moveToNext());
            }
        }

        Integer[] phArr = new Integer[100];
        Integer[] bpArr = new Integer[100];
        Integer[] lsArr = new Integer[100];
        Integer[] grArr = new Integer[100];



        Integer[] userVector = new Integer[4];

        userVector[0] = getPopularElement(phArr);
        userVector[1] = getPopularElement(bpArr);
        userVector[2] = getPopularElement(lsArr);
        userVector[3] = getPopularElement(grArr);

        int[][] allVectors = new int[60000][5];


        cur = db.rawQuery("SELECT * FROM " + "userplants", null);
       // ArrayList temp = new ArrayList();
        int i=0;
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    allVectors[i][0] =Integer.parseInt((cur.getString(cur.getColumnIndex("ScientificName"))));
                    allVectors[i][1] =  Integer.parseInt(String.valueOf((PalatableHuman.valueOf(cur.getString(cur.getColumnIndex("PalatableHuman"))))));
                    allVectors[i][2] = Integer.parseInt(String.valueOf((BloomPeriod.valueOf(cur.getString(cur.getColumnIndex("BloomPeriod"))))));
                    allVectors[i][3] = Integer.parseInt(String.valueOf((GrowthRate.valueOf(cur.getString(cur.getColumnIndex("Growthrate"))))));
                    i++;
                } while (cur.moveToNext());
            }
        }



        double normUser = Math.sqrt(userVector[0] + userVector[1] + userVector[2] + userVector[3]);

        HashMap<Integer, Double> map = new HashMap<>();


        List<Method> list = new ArrayList<Method>();
        for (int[] vec : allVectors) {
                double dotProduct = dotProd(userVector, vec);
                double normComp = Math.sqrt(vec[1] + vec[2] + vec[3] + vec[4]);
                double angle = dotProduct / (normComp * normUser);
                map.put(vec[0], angle);

        }



        String[] bestMatch = new String[5];
        String maxEntry = "";
        int j=0;

        for(; j < 5; j++) {
            Double maxValue = Collections.min(map.values());
            for (Entry entry : map.entrySet())
                    maxEntry = new String((byte[]) entry.getKey());


                }
            bestMatch[i] = maxEntry;
            map.remove(maxEntry);


        return bestMatch;

    }
}
