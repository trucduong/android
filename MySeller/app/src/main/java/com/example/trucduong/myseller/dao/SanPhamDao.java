package com.example.trucduong.myseller.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.trucduong.myseller.dto.SanPham;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trucduong on 30/08/2016.
 */
public class SanPhamDao {

    public List<SanPham> findByName(SQLiteDatabase db, String name) {
        List<SanPham> sanPhams = new ArrayList<SanPham>();
        String query = "select id, name, count, in_price, out_price, note, nhom_sp from san_pham";
        String[] params = new String[] {};
        if (name != null && name.length() > 0) {
            query = query + " where name like '%"+name+"%'";
        }

        query = query + " order by name asc";
        Cursor cursor = db.rawQuery(query, params);
        if (cursor.moveToFirst() ) {
            do {
                SanPham sanPham = new SanPham();
                sanPham.setId(cursor.getInt(0));
                sanPham.setName(cursor.getString(1));
                sanPham.setCount(cursor.getInt(2));
                sanPham.setInPrice(cursor.getInt(3));
                sanPham.setOutPrice(cursor.getInt(4));
                sanPham.setNote(cursor.getString(5));
                sanPham.setNhomSP(cursor.getInt(6));

                sanPhams.add(sanPham);
            } while (cursor.moveToNext());
        }

        return sanPhams;
    }


    public List<SanPham> findByNhom(SQLiteDatabase db, int nhomSP) {
        List<SanPham> sanPhams = new ArrayList<SanPham>();
        String query = "select id, name, count, in_price, out_price, note, nhom_sp from san_pham where nhom_sp = ?";
        query = query + " order by name asc";
        Cursor cursor = db.rawQuery(query, new String[] {String.valueOf(nhomSP)});
        if (cursor.moveToFirst() ) {
            do {
                SanPham sanPham = new SanPham();
                sanPham.setId(cursor.getInt(0));
                sanPham.setName(cursor.getString(1));
                sanPham.setCount(cursor.getInt(2));
                sanPham.setInPrice(cursor.getInt(3));
                sanPham.setOutPrice(cursor.getInt(4));
                sanPham.setNote(cursor.getString(5));
                sanPham.setNhomSP(cursor.getInt(6));

                sanPhams.add(sanPham);
            } while (cursor.moveToNext());
        }

        return sanPhams;
    }

    public List<SanPham> getAll(SQLiteDatabase db) {
        List<SanPham> sanPhams = new ArrayList<SanPham>();
        String query = "select id, name, count, in_price, out_price, note, nhom_sp from san_pham";
        query = query + " order by name asc";
        Cursor cursor = db.rawQuery(query, new String[] {});
        if (cursor.moveToFirst() ) {
            do {
                SanPham sanPham = new SanPham();
                sanPham.setId(cursor.getInt(0));
                sanPham.setName(cursor.getString(1));
                sanPham.setCount(cursor.getInt(2));
                sanPham.setInPrice(cursor.getInt(3));
                sanPham.setOutPrice(cursor.getInt(4));
                sanPham.setNote(cursor.getString(5));
                sanPham.setNhomSP(cursor.getInt(6));

                sanPhams.add(sanPham);
            } while (cursor.moveToNext());
        }

        return sanPhams;
    }

    public SanPham findById(SQLiteDatabase db, int id) {
        SanPham sanPham = null;
        String query = "select id, name, count, in_price, out_price, note, nhom_sp from san_pham where id = ?";
        Cursor cursor = db.rawQuery(query, new String[] {String .valueOf(id)});
        if (cursor.moveToFirst() ) {
            sanPham = new SanPham();
            sanPham.setId(cursor.getInt(0));
            sanPham.setName(cursor.getString(1));
            sanPham.setCount(cursor.getInt(2));
            sanPham.setInPrice(cursor.getInt(3));
            sanPham.setOutPrice(cursor.getInt(4));
            sanPham.setNote(cursor.getString(5));
            sanPham.setNhomSP(cursor.getInt(6));
        }

        return sanPham;
    }

    public int create(SQLiteDatabase db, SanPham sanPham) {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put("name", sanPham.getName());
        values.put("count", sanPham.getCount());
        values.put("in_price", sanPham.getInPrice());
        values.put("out_price", sanPham.getOutPrice());
        values.put("note", sanPham.getNote());
        values.put("nhom_sp", sanPham.getNhomSP());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert("san_pham", null, values);
        return (int)newRowId;
    }

    public int update(SQLiteDatabase db, SanPham sanPham) {

        // New value for one column
        ContentValues values = new ContentValues();
        values.put("name", sanPham.getName());
        values.put("count", sanPham.getCount());
        values.put("in_price", sanPham.getInPrice());
        values.put("out_price", sanPham.getOutPrice());
        values.put("note", sanPham.getNote());
        values.put("nhom_sp", sanPham.getNhomSP());

        // Which row to update, based on the title
        String selection = "id = ?";
        String[] selectionArgs = { String.valueOf(sanPham.getId()) };

        int count = db.update(
                "san_pham",
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
        int count = db.delete("san_pham", selection, selectionArgs);
        return count;
    }
}
