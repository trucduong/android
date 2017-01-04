package com.example.trucduong.myseller.dto;

/**
 * Created by trucduong on 29/08/2016.
 */
public class SanPham {
    private int id;
    private String name;
    private int count;
    private int inPrice;
    private int outPrice;
    private String note;
    private int nhomSP;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getInPrice() {
        return inPrice;
    }

    public void setInPrice(int inPrice) {
        this.inPrice = inPrice;
    }

    public int getOutPrice() {
        return outPrice;
    }

    public void setOutPrice(int outPrice) {
        this.outPrice = outPrice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getNhomSP() {
        return nhomSP;
    }

    public void setNhomSP(int nhomSP) {
        this.nhomSP = nhomSP;
    }
}
