package com.example.satourismapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Scanner;


public class database{

    //database name
    String database="touristDB";
    //table name
    String table = "touristattractions";
    //database version
    int version = 1;

    //columns
    String id="id";
    String province="Province";
    String place="Place";

    public String gPlace;
    public String namePlate;

    helper h;
    Context c;

    //stores all sqlite queries
    SQLiteDatabase s;

    public database(MainActivity mainActivity) {

        c = mainActivity;

    }

    public database(PlaceDetails placeDetails) {

    }


    public void open() {
        h = new helper(c);
        s=h.getWritableDatabase();
    }

    //adds places to database
    public void addPlaces(){
        h = new helper(c);
        s = h.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(province, "Gauteng");
        cv.put(place, "UnionBuildings");
        s.insert(table, null, cv);

        cv.put(province, "WesternCape");
        cv.put(place, "TableMountain");
        s.insert(table, null, cv);

        cv.put(province, "KwazuluNatal");
        cv.put(place, "uShakaMarineWorld");
        s.insert(table, null, cv);

        cv.put(province, "EasternCape");
        cv.put(place, "AddoElephantNationalPark");
        s.insert(table, null, cv);

        cv.put(province, "NorthernCape");
        cv.put(place, "AugrabiesFallsNationalPark");
        s.insert(table, null, cv);

        cv.put(province, "Mpumalanga");
        cv.put(place, "KrugerNationalPark");
        s.insert(table, null, cv);

        cv.put(province, "Limpopo");
        cv.put(place, "MapugubweNationalPark");
        s.insert(table, null, cv);

        cv.put(province, "NorthWest");
        cv.put(place, "SunCityResort");
        s.insert(table, null, cv);

        cv.put(province, "FreeState");
        cv.put(place, "FreeStateNationalBotanicalGarden");
        s.insert(table, null, cv);
        s.close();


    }

    public void close() {
        s.close();
    }

    //gets province details
    public String getProvince(){
        h = new helper(c);
        s = h.getReadableDatabase();
        String txt ="";
        String[] col={province,place};
        //fetches all data
        Cursor c = s.query(table, col, null, null, null, null, null);
        c.moveToFirst();
        for(c.moveToFirst(); !c.isAfterLast() ; c.moveToNext()){

            txt = txt+c.getString(0)+",";

            Scanner fromStr = new Scanner(txt);
            namePlate = fromStr.next();

        }

        return namePlate;
    }

    //gets Place details
    public String getPlaceDetails() {
        h = new helper(c);
        s = h.getReadableDatabase();
        String txt1 = "";
        String txt2 = "";
        String[] col = {place, province};
        //fetches all data

        Cursor c = s.query(table, col, null, null, null, null, null);
        if (c.moveToFirst()) {
            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

                txt1 = txt1 + c.getString(0)+",";


                Scanner fromStr = new Scanner(txt1);

                gPlace = fromStr.next();

            }

            return gPlace;
        }else{
            gPlace ="";
            return gPlace;
        }
    }





    public class helper extends SQLiteOpenHelper{

        public helper( Context context) {
            super(context, database, null, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //creates the table for the tourist app
            String query = "CREATE TABLE "+table+"("+id+" INTEGER PRIMARY KEY AUTOINCREMENT, "+province+" TEXT NOT NULL, "+place+" TEXT NOT NULL);";
            //executes the query
            db.execSQL(query);
        }

        @Override
        //drops table if it exists
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+table);
            onCreate(db);
        }


    }
}
