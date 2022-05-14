package com.example.pvbshoesshop.model;

public class User {

    private int id;
    private String ten_nguoi_dung;
    private String email;
    private String sdt;
    private String Ten_dang_nhap;
    private String password;
    private String id_phan_quyen;

    public User(int id, String ten_nguoi_dung, String email, String sdt, String ten_dang_nhap, String password, String id_phan_quyen) {
        this.id = id;
        this.ten_nguoi_dung = ten_nguoi_dung;
        this.email = email;
        this.sdt = sdt;
        Ten_dang_nhap = ten_dang_nhap;
        this.password = password;
        this.id_phan_quyen = id_phan_quyen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen_nguoi_dung() {
        return ten_nguoi_dung;
    }

    public void setTen_nguoi_dung(String ten_nguoi_dung) {
        this.ten_nguoi_dung = ten_nguoi_dung;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTen_dang_nhap() {
        return Ten_dang_nhap;
    }

    public void setTen_dang_nhap(String ten_dang_nhap) {
        Ten_dang_nhap = ten_dang_nhap;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId_phan_quyen() {
        return id_phan_quyen;
    }

    public void setId_phan_quyen(String id_phan_quyen) {
        this.id_phan_quyen = id_phan_quyen;
    }
}
