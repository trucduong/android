package com.example.trucduong.myseller.dto;

/**
 * Created by trucduong on 29/08/2016.
 */
public class NhomSanPham extends ComboboxItem<Integer, String> {
    private int id;
    private String name;
    private String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public Integer getKey() {
        return id;
    }

    @Override
    public String getValue() {
        return name;
    }
}
