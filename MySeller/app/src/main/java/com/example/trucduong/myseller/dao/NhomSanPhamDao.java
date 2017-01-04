package com.example.trucduong.myseller.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.trucduong.myseller.dto.NhomSanPham;
import com.example.trucduong.myseller.dto.SanPham;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by trucduong on 30/08/2016.
 */
public class NhomSanPhamDao {

    public Map<Integer, String> getAllForCMB(SQLiteDatabase db) {
        Map<Integer, String> maps = new HashMap<Integer, String>();
        String query = "select id, name, note from nhom_san_pham";
        query = query + " order by name asc";
        Cursor cursor = db.rawQuery(query, new String[] {});
        if (cursor.moveToFirst() ) {
            do {
                maps.put(cursor.getInt(0), cursor.getString(1));
            } while (cursor.moveToNext());
        }

        return maps;
    }

    public List<NhomSanPham> getAll(SQLiteDatabase db) {
        List<NhomSanPham> items = new ArrayList<NhomSanPham>();
        String query = "select id, name, note from nhom_san_pham";
        query = query + " order by name asc";
        Cursor cursor = db.rawQuery(query, new String[] {});
        if (cursor.moveToFirst() ) {
            do {
                NhomSanPham item = new NhomSanPham();
                item.setId(cursor.getInt(0));
                item.setName(cursor.getString(1));
                item.setNote(cursor.getString(2));

                items.add(item);
            } while (cursor.moveToNext());
        }

        return items;
    }

    public NhomSanPham findById(SQLiteDatabase db, int id) {
        NhomSanPham item = null;
        String query = "select id, name, note from nhom_san_pham where id = ?";
        Cursor cursor = db.rawQuery(query, new String[] {String .valueOf(id)});
        if (cursor.moveToFirst() ) {
            item = new NhomSanPham();
            item.setId(cursor.getInt(0));
            item.setName(cursor.getString(1));
            item.setNote(cursor.getString(2));
        }

        return item;
    }

    public int create(SQLiteDatabase db, NhomSanPham item) {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put("name", item.getName());
        values.put("note", item.getNote());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert("nhom_san_pham", null, values);
        return (int)newRowId;
    }

    public int update(SQLiteDatabase db, NhomSanPham item) {

        // New value for one column
        ContentValues values = new ContentValues();
        values.put("name", item.getName());
        values.put("note", item.getNote());

        // Which row to update, based on the title
        String selection = "id = ?";
        String[] selectionArgs = { String.valueOf(item.getId()) };

        int count = db.update(
                "nhom_san_pham",
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int delete(SQLiteDatabase db, int id) {
        // Define 'where' part of query.
        String selection = "id = ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { String.valueOf(id) };
        // Issue SQL statement.
        int count = db.delete("nhom_san_pham", selection, selectionArgs);
        return count;
    }
}
