package com.example.pvbshoesshop.model;

import java.util.Date;

public class DanhGia {
    private int id_danh_gia;
    private String id_user;
    private String ten_danh_gia;
    private String danh_gia;
    private String danh_gia_binh_luan;
    private String id_giay;
    private String created_at;
    private String updated_at;

    public DanhGia(int id_danh_gia, String id_user, String ten_danh_gia, String danh_gia, String danh_gia_binh_luan, String id_giay, String created_at, String updated_at) {
        this.id_danh_gia = id_danh_gia;
        this.id_user = id_user;
        this.ten_danh_gia = ten_danh_gia;
        this.danh_gia = danh_gia;
        this.danh_gia_binh_luan = danh_gia_binh_luan;
        this.id_giay = id_giay;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId_danh_gia() {
        return id_danh_gia;
    }

    public void setId_danh_gia(int id_danh_gia) {
        this.id_danh_gia = id_danh_gia;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getTen_danh_gia() {
        return ten_danh_gia;
    }

    public void setTen_danh_gia(String ten_danh_gia) {
        this.ten_danh_gia = ten_danh_gia;
    }

    public String getDanh_gia() {
        return danh_gia;
    }

    public void setDanh_gia(String danh_gia) {
        this.danh_gia = danh_gia;
    }

    public String getDanh_gia_binh_luan() {
        return danh_gia_binh_luan;
    }

    public void setDanh_gia_binh_luan(String danh_gia_binh_luan) {
        this.danh_gia_binh_luan = danh_gia_binh_luan;
    }

    public String getId_giay() {
        return id_giay;
    }

    public void setId_giay(String id_giay) {
        this.id_giay = id_giay;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}

//    "id_danh_gia": 1,
//    "id_user": "1",
//    "ten_danh_gia": "Admin",
//    "danh_gia": "4.5",
//    "danh_gia_binh_luan": "cũng ok phết",
//    "id_giay": "1",
//    "created_at": "2022-05-02 08:04:28",
//    "updated_at": "2022-05-02 08:04:28"