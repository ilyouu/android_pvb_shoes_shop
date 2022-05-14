package com.example.pvbshoesshop.api;

import com.example.pvbshoesshop.model.DanhGia;
import com.example.pvbshoesshop.model.KhuyenMai;
import com.example.pvbshoesshop.model.Shoes;
import com.example.pvbshoesshop.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://pvbshoesshop.000webhostapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ApiService.class);

    @GET("users")
    Call<List<User>> getUsers();

    @GET("users/{id}")
    Call<User> getUser(@Path("id") int id);



    @GET("cua-hang")
    Call<List<Shoes>> getShoes();

    @GET("cua-hang/san-pham={id}")
    Call<Shoes> getShoe(@Path("id") int id);

    @GET("cua-hang/noi-bat")
    Call<List<Shoes>> getShoesNoiBat();

    @GET("cua-hang/moi-nhat")
    Call<List<Shoes>> getShoesMoiNhat();

    @GET("cua-hang/thuong-hieu/{thuonghieu}")
    Call<List<Shoes>> getShoesThuongHieu();

    @GET("cua-hang/dang-giam-gia")
    Call<List<Shoes>> getShoesDangGiamGia();


    @GET("cua-hang/{id}/update/{num}")
    Call<Shoes> updateSeeShoe(@Path("id") int id, @Path("num") int num);


    @GET("khuyen-mai")
    Call<List<KhuyenMai>> getKhuyenMais();

    @GET("khuyen-mai/{id}")
    Call<User> getKhuyenMai(@Path("id") int id);




    @GET("danh-gia")
    Call<List<DanhGia>> getDanhGias();

    @GET("danh-gia/{id}")
    Call<List<DanhGia>> getDanhGia(@Path("id") int id);
}
