package com.example.trucduong.myseller.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by trucduong on 30/08/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "myseller.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE nhom_san_pham (id INTEGER PRIMARY KEY, name TEXT, note TEXT)");
        db.execSQL("CREATE TABLE san_pham ( id INTEGER PRIMARY KEY, name TEXT, count INTEGER, in_price INTEGER, out_price INTEGER, note TEXT, nhom_sp INTEGER)");

        db.execSQL("insert into nhom_san_pham (id, name, note) values(1, 'nhom 1', 'nhom 1')");
        db.execSQL("insert into nhom_san_pham (id, name, note) values(2, 'nhom 2', 'nhom 2')");
        db.execSQL("insert into nhom_san_pham (id, name, note) values(3, 'nhom 3', 'nhom 3')");
        db.execSQL("insert into nhom_san_pham (id, name, note) values(4, 'nhom 4', 'nhom 4')");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        StringBuilder query = new StringBuilder();
        query.append("DROP TABLE IF EXISTS nhom_san_pham;");
        query.append("DROP TABLE IF EXISTS san_pham;");

        db.execSQL(query.toString());
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
