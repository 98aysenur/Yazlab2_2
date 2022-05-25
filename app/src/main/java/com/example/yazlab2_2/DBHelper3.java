package com.example.yazlab2_2;

import static com.example.yazlab2_2.AdminActivity.DB2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper3 extends SQLiteOpenHelper {
    public static final String DBNAME = "rota.db";
    static ArrayList<String> allSelected;
    public DBHelper3(Context context) {

        super(context, "rota.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table rota(durak1 TEXT , durak2 TEXT, durak3 TEXT, durak4 TEXT, durak5 TEXT, durak6 TEXT, durak7 TEXT, durak8 TEXT, durak9 TEXT, durak10 TEXT, durak11 TEXT, durak12 TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(){
        System.out.println( "INSERTDATA!!!!!!!");
        allSelected = DB2.getRowsAsArrayList("durations");
        SQLiteDatabase MyDB = getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        if(allSelected.size()==2){
            contentValues.put("durak1", allSelected.get(1));
        }
        if(allSelected.size()==3){
            contentValues.put("durak1", allSelected.get(1));
            contentValues.put("durak2", allSelected.get(2));
        }
        if(allSelected.size()==4){
            contentValues.put("durak1", allSelected.get(1));
            contentValues.put("durak2", allSelected.get(2));
            contentValues.put("durak3", allSelected.get(3));
        }
        if(allSelected.size()==5){
            contentValues.put("durak1", allSelected.get(1));
            contentValues.put("durak2", allSelected.get(2));
            contentValues.put("durak3", allSelected.get(2));
            contentValues.put("durak4", allSelected.get(4));
        }
        if(allSelected.size()==6){
            contentValues.put("durak1", allSelected.get(1));
            contentValues.put("durak2", allSelected.get(2));
            contentValues.put("durak3", allSelected.get(3));
            contentValues.put("durak4", allSelected.get(4));
            contentValues.put("durak5", allSelected.get(5));
        }
        if(allSelected.size()==7){
            contentValues.put("durak1", allSelected.get(1));
            contentValues.put("durak2", allSelected.get(2));
            contentValues.put("durak3", allSelected.get(3));
            contentValues.put("durak4", allSelected.get(4));
            contentValues.put("durak5", allSelected.get(5));
            contentValues.put("durak6", allSelected.get(6));
        }
        if(allSelected.size()==8){
            contentValues.put("durak1", allSelected.get(1));
            contentValues.put("durak2", allSelected.get(2));
            contentValues.put("durak3", allSelected.get(3));
            contentValues.put("durak4", allSelected.get(4));
            contentValues.put("durak5", allSelected.get(5));
            contentValues.put("durak6", allSelected.get(6));
            contentValues.put("durak7", allSelected.get(7));
        }
        if(allSelected.size()==9){
            contentValues.put("durak1", allSelected.get(1));
            contentValues.put("durak2", allSelected.get(2));
            contentValues.put("durak3", allSelected.get(3));
            contentValues.put("durak4", allSelected.get(4));
            contentValues.put("durak5", allSelected.get(5));
            contentValues.put("durak6", allSelected.get(6));
            contentValues.put("durak7", allSelected.get(7));
            contentValues.put("durak8", allSelected.get(8));
        }
        if(allSelected.size()==10){
            contentValues.put("durak1", allSelected.get(1));
            contentValues.put("durak2", allSelected.get(2));
            contentValues.put("durak3", allSelected.get(3));
            contentValues.put("durak4", allSelected.get(4));
            contentValues.put("durak5", allSelected.get(5));
            contentValues.put("durak6", allSelected.get(6));
            contentValues.put("durak7", allSelected.get(7));
            contentValues.put("durak8", allSelected.get(8));
            contentValues.put("durak9", allSelected.get(9));
        }
        if(allSelected.size()==11){
            contentValues.put("durak1", allSelected.get(1));
            contentValues.put("durak2", allSelected.get(2));
            contentValues.put("durak3", allSelected.get(3));
            contentValues.put("durak4", allSelected.get(4));
            contentValues.put("durak5", allSelected.get(5));
            contentValues.put("durak6", allSelected.get(6));
            contentValues.put("durak7", allSelected.get(7));
            contentValues.put("durak8", allSelected.get(8));
            contentValues.put("durak9", allSelected.get(9));
            contentValues.put("durak10", allSelected.get(10));
        }
        if(allSelected.size()==12){
            contentValues.put("durak1", allSelected.get(1));
            contentValues.put("durak2", allSelected.get(2));
            contentValues.put("durak3", allSelected.get(3));
            contentValues.put("durak4", allSelected.get(4));
            contentValues.put("durak5", allSelected.get(5));
            contentValues.put("durak6", allSelected.get(6));
            contentValues.put("durak7", allSelected.get(7));
            contentValues.put("durak8", allSelected.get(8));
            contentValues.put("durak9", allSelected.get(9));
            contentValues.put("durak10", allSelected.get(10));
            contentValues.put("durak11", allSelected.get(11));
        }
        if(allSelected.size()==13){

            contentValues.put("durak1", allSelected.get(1));
            contentValues.put("durak2", allSelected.get(2));
            contentValues.put("durak3", allSelected.get(3));
            contentValues.put("durak4", allSelected.get(4));
            contentValues.put("durak5", allSelected.get(5));
            contentValues.put("durak6", allSelected.get(6));
            contentValues.put("durak7", allSelected.get(7));
            contentValues.put("durak8", allSelected.get(8));
            contentValues.put("durak9", allSelected.get(9));
            contentValues.put("durak10", allSelected.get(10));
            contentValues.put("durak11", allSelected.get(11));
            contentValues.put("durak12", allSelected.get(12));
        }





        long result = MyDB.insert("rota", null, contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public void main(String[] args) {
    insertData();
    }
}
