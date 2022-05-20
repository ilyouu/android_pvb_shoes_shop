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
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://pvbshoesshop.000webhostapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ApiService.class);



    @GET("users")
    Call<List<User>> getUsers();

    @POST("users/create/{name}/{email}/{sdt}/{nameSign}/{pass}")
    Call<List<User>> addUser(@Path("name") String name,
                             @Path("email") String email,
                             @Path("sdt") String sdt,
                             @Path("nameSign") String nameSign,
                             @Path("pass") String pass);



    @GET("cua-hang")
    Call<List<Shoes>> getShoes();

    @GET("cua-hang/noi-bat")
    Call<List<Shoes>> getShoesNoiBat();

    @GET("cua-hang/moi-nhat")
    Call<List<Shoes>> getShoesMoiNhat();

    @GET("cua-hang/dang-giam-gia")
    Call<List<Shoes>> getShoesDangGiamGia();

    @GET("cua-hang/{id}/update/{num}")
    Call<Shoes> updateSeeShoe(@Path("id") int id, @Path("num") int num);



    @GET("khuyen-mai")
    Call<List<KhuyenMai>> getKhuyenMais();



    @GET("danh-gia/{id}")
    Call<List<DanhGia>> getDanhGia(@Path("id") int id);
}
