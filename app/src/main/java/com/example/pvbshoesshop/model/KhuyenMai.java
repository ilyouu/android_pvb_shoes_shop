package com.example.pvbshoesshop.model;

public class KhuyenMai {
    private int id_khuyen_mai;
    private String ten_khuyen_mai;
    private String gia_tri_khuyen_mai;

    public KhuyenMai(int id_khuyen_mai, String ten_khuyen_mai, String gia_tri_khuyen_mai) {
        this.id_khuyen_mai = id_khuyen_mai;
        this.ten_khuyen_mai = ten_khuyen_mai;
        this.gia_tri_khuyen_mai = gia_tri_khuyen_mai;
    }

    public int getId_khuyen_mai() {
        return id_khuyen_mai;
    }

    public void setId_khuyen_mai(int id_khuyen_mai) {
        this.id_khuyen_mai = id_khuyen_mai;
    }

    public String getTen_khuyen_mai() {
        return ten_khuyen_mai;
    }

    public void setTen_khuyen_mai(String ten_khuyen_mai) {
        this.ten_khuyen_mai = ten_khuyen_mai;
    }

    public String getGia_tri_khuyen_mai() {
        return gia_tri_khuyen_mai;
    }

    public void setGia_tri_khuyen_mai(String gia_tri_khuyen_mai) {
        this.gia_tri_khuyen_mai = gia_tri_khuyen_mai;
    }
}

//        "id_khuyen_mai": 4,
//        "ten_khuyen_mai": "Sale cuối năm",
//        "gia_tri_khuyen_mai": "5"