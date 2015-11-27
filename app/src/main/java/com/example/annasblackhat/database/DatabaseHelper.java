package com.example.annasblackhat.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ANNAS BlackHat on 19/11/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "amcc.db";
    private final String TABLE_NAME = "wallet";
    private final static int DATABASE_VERSION = 1 ;
    private String COLUMN_NIM = "nim";
    private String COLUMN_NAME = "nama";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"" +
                " ("+COLUMN_NIM+" TEXT PRIMARY KEY, " +
                ""+COLUMN_NAME+" TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(Mahasiswa m){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NIM, m.getNim());
        contentValues.put(COLUMN_NAME, m.getNama());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        //INSERT INTO table nim, nama VALUES (m.getNim(), m.getNama());
    }

    public void delete(String nim){
        SQLiteDatabase db = this.getWritableDatabase();
        // DELETE FROM table WHERE nim='' AND ...
        db.delete(TABLE_NAME, COLUMN_NIM + "=?", new String[]{nim});
        db.close();
    }

    public void update(Mahasiswa m){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NIM, m.getNim());
        contentValues.put(COLUMN_NAME, m.getNama());
        db.update(TABLE_NAME, contentValues, COLUMN_NIM+"=?", new String[]{m.getNim()});
    }

    public List<Mahasiswa> getData(){
        List<Mahasiswa> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()){
            Mahasiswa m = new Mahasiswa();
            m.setNim(cursor.getString(cursor.getColumnIndex(COLUMN_NIM)));
            m.setNama(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            list.add(m);
        }
        cursor.close();
        db.close();
        return list;
    }
}
