package com.example.yazlab2_2;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper2 extends SQLiteOpenHelper {




    public static final String DBNAME = "Durations4.db";
        public DBHelper2(Context context) {
            super(context, "Durations4.db", null, 1);
        }

        @Override
        public void onCreate (SQLiteDatabase MyDB){
            MyDB.execSQL("create Table durations(duration TEXT,kisi INTEGER)"); // duration name
        }

        @Override
        public void onUpgrade (SQLiteDatabase MyDB,int i, int i1){
            MyDB.execSQL("drop Table if exists durations");
        }

    public boolean CheckIsInDB(String selected) {
        String selectQuery = "SELECT  * FROM durations WHERE duration=" +"'"+selected +"'";
       // SELECT * FROM Customers
        //WHERE Country='Mexico';
        final SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() <= 0) {
          //  System.out.println("<=0 false");
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
    public void getCount(String durak, int kisiSayisi) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("UPDATE durations"  + " SET kisi "  + "=" + "kisi+" + kisiSayisi
                + " WHERE duration " + "='" + durak + "'");
        database.close();
    }
    public void updatedata(String sehir,int kisi2){

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cr2  = database.rawQuery("SELECT kisi FROM durations WHERE duration=" + sehir , null);
        cr2.moveToFirst();
        int test = cr2.getInt(0);
        System.out.println("test1="+test);
        test += kisi2;
        System.out.println("test1="+test);
        Cursor cr3  = database.rawQuery(" UPDATE durations SET kisi = test WHERE duration="+sehir,null );
       // database.update("durations","kisi","kisi"+"= ?",new String[] { String.valueOf(sehir) });
       // database.execSQL( "UPDATE " + "durations" + " SET " + "kisi" + "="  + test +  " + ?"+ " WHERE " + "duration" + "=?",
       //new String[] { String.valueOf(sehir) });

        //database.update("durations",test,"kisi"+"= ?",new String[]{id});

    }


        public Boolean insertData(String drname){
            SQLiteDatabase MyDB = this.getWritableDatabase();
            ContentValues contentValues= new ContentValues();
            if(drname.equals("seciniz...")) {
            }
            else {
                contentValues.put("duration", drname);
                contentValues.put("kisi",0);

            }
            long result = MyDB.insert("durations", null, contentValues);
            if(result==-1)
                return false;
            else
                return true;
        }


    @SuppressLint("Range")
    public ArrayList<String> getRowsAsArrayList(String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList <String> durations = new ArrayList<>();
        durations.add(0,"aa");
        Log.d(TAG, "getTableAsString called");
        String tableString = String.format("Table %s:\n", tableName);
        Cursor allRows  = db.rawQuery("SELECT * FROM " + tableName, null);
        if (allRows.moveToFirst() ){
            //String columnNames = "duration";
            do {

                    durations.add(allRows.getString(allRows.getColumnIndex("duration")));
                    //System.out.println(allRows.getString(allRows.getColumnIndex(name)));
                   // tableString += String.format("%s: %s\n", name,
                      //      allRows.getString(allRows.getColumnIndex(name)));


            } while (allRows.moveToNext());
        }

        return durations;
    }


}
