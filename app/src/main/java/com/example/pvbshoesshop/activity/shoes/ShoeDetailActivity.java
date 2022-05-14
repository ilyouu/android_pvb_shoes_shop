package com.example.pvbshoesshop.activity.shoes;

import android.graphics.Paint;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.pvbshoesshop.R;
import com.example.pvbshoesshop.adapter.DanhGiaAdapter;
import com.example.pvbshoesshop.adapter.ImageAdapter;
import com.example.pvbshoesshop.adapter.ImageHomeAdapter;
import com.example.pvbshoesshop.adapter.ShoesAdapter;
import com.example.pvbshoesshop.api.ApiService;
import com.example.pvbshoesshop.databinding.ActivityDetailShoeBinding;
import com.example.pvbshoesshop.model.DanhGia;
import com.example.pvbshoesshop.model.Image;
import com.example.pvbshoesshop.model.KhuyenMai;
import com.example.pvbshoesshop.model.Shoes;
import com.example.pvbshoesshop.model.User;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoeDetailActivity extends AppCompatActivity {

    private ActivityDetailShoeBinding binding;
    private int id_giay;
    private String ten_giay, ten_loai_giay, ten_thuong_hieu, mo_ta, don_gia, so_luong, hinh_anh_1, hinh_anh_2, hinh_anh_3, hinh_anh_4, ten_khuyen_mai, so_luong_mua, so_luong_xem, created_at, updated_at, tien_khuyen_mai, gia_tri_khuyen_mai;
    private List<DanhGia> listDanhGia;
    private List<User> listUser;

    private ViewPager2 viewPager;

    private ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailShoeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getValueShoe();
        setListeners();
        setData();

        imageAdapter = new ImageAdapter(this, getListImage());
        binding.viewPager.setAdapter(imageAdapter);

        binding.circleIndicator.setViewPager(binding.viewPager);
        imageAdapter.registerDataSetObserver(binding.circleIndicator.getDataSetObserver());
    }

    private List<Image> getListImage() {
        List<Image> list = new ArrayList<>();
        list.add(new Image(hinh_anh_1));
        list.add(new Image(hinh_anh_2));
        list.add(new Image(hinh_anh_3));
        list.add(new Image(hinh_anh_4));
        return list;
    }

    private void setListeners() {
        binding.imageBack.setOnClickListener(v -> onBackPressed());
    }

    private void setData() {
        NumberFormat formatter = new DecimalFormat("#,###");

        binding.progressBar.setVisibility(View.INVISIBLE);
        binding.textShoe.setText(ten_giay);
        binding.textPrice.setText("₫"+tien_khuyen_mai);

        if (!ten_khuyen_mai.equals("Không khuyễn mãi")) {
            binding.textCost.setVisibility(View.VISIBLE);
            binding.textCost.setText("₫"+formatter.format(Integer.parseInt(don_gia)));
            binding.textCost.setPaintFlags(binding.textCost.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        binding.textKho.setText(so_luong);
        binding.textDanhGia.setText("Đã bán "+so_luong_mua);
        binding.textLoaiGiay.setText(ten_loai_giay);
        binding.textThuongHieu.setText(ten_thuong_hieu);
        binding.textKhuyenMai.setText(ten_khuyen_mai+" (-" +gia_tri_khuyen_mai+ "%)");

        binding.textMoTa.setText(Html.fromHtml(mo_ta));
        binding.textSee.setText(so_luong_xem+ " lượt xem");

        ApiService.apiService.getDanhGia(id_giay).enqueue(new Callback<List<DanhGia>>() {
            @Override
            public void onResponse(Call<List<DanhGia>> call, Response<List<DanhGia>> response) {
                listDanhGia = response.body();

                if (response.body().isEmpty()) {
                    binding.progressBarDanhGia.setVisibility(View.INVISIBLE);
                    binding.noneDanhGia.setVisibility(View.VISIBLE);
                } else {
                    binding.textDanhGiaBinhLuan.setText(response.body().size()+ " đánh giá");

                    ApiService.apiService.getUsers().enqueue(new Callback<List<User>>() {
                        @Override
                        public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                            listUser = response.body();
                            binding.progressBarDanhGia.setVisibility(View.INVISIBLE);
                            DanhGiaAdapter danhGiaAdapter = new DanhGiaAdapter(listDanhGia, listUser);
                            binding.danhGiaRecyclerView.setAdapter(danhGiaAdapter);
                            binding.danhGiaRecyclerView.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onFailure(Call<List<User>> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Không lấy được dữ liệu đánh giá người dùng!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<DanhGia>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Không lấy được dữ liệu đánh giá sản phẩm!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getValueShoe() {
        id_giay = getIntent().getIntExtra("id_giay", 0);
        ten_giay = getIntent().getStringExtra("ten_giay");
        ten_loai_giay = getIntent().getStringExtra("ten_loai_giay");
        ten_thuong_hieu = getIntent().getStringExtra("ten_thuong_hieu");
        mo_ta = getIntent().getStringExtra("mo_ta");
        don_gia = getIntent().getStringExtra("don_gia");
        so_luong = getIntent().getStringExtra("so_luong");
        hinh_anh_1 = getIntent().getStringExtra("hinh_anh_1");
        hinh_anh_2 = getIntent().getStringExtra("hinh_anh_2");
        hinh_anh_3 = getIntent().getStringExtra("hinh_anh_3");
        hinh_anh_4 = getIntent().getStringExtra("hinh_anh_4");
        ten_khuyen_mai = getIntent().getStringExtra("ten_khuyen_mai");
        so_luong_mua = getIntent().getStringExtra("so_luong_mua");
        so_luong_xem = getIntent().getStringExtra("so_luong_xem");
        created_at = getIntent().getStringExtra("created_at");
        updated_at = getIntent().getStringExtra("updated_at");
        tien_khuyen_mai = getIntent().getStringExtra("tien_khuyen_mai");
        gia_tri_khuyen_mai = getIntent().getStringExtra("gia_tri_khuyen_mai");
    }
}