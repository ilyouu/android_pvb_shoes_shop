package com.example.pvbshoesshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pvbshoesshop.R;
import com.example.pvbshoesshop.activity.SignInActivity;
import com.example.pvbshoesshop.activity.shoes.ShoeDetailActivity;
import com.example.pvbshoesshop.api.ApiService;
import com.example.pvbshoesshop.databinding.ItemShoeHomeBinding;
import com.example.pvbshoesshop.model.KhuyenMai;
import com.example.pvbshoesshop.model.Shoes;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.GiayNoiBatViewHolder>{

    private List<Shoes> listShoesNoiBat;
    private List<KhuyenMai> khuyenMaisList;
    private Context context;

    public HomeAdapter(List<Shoes> listShoesNoiBat, List<KhuyenMai> khuyenMaisList) {
        this.listShoesNoiBat = listShoesNoiBat;
        this.khuyenMaisList = khuyenMaisList;
    }

    @NonNull
    @Override
    public HomeAdapter.GiayNoiBatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemShoeHomeBinding itemShoeBinding = ItemShoeHomeBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );

        context = parent.getContext();

        return new HomeAdapter.GiayNoiBatViewHolder(itemShoeBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.GiayNoiBatViewHolder holder, int position) {
        holder.setShoeData(listShoesNoiBat.get(position), khuyenMaisList);
    }

    @Override
    public int getItemCount() {
        return listShoesNoiBat.size();
    }

    public class GiayNoiBatViewHolder extends RecyclerView.ViewHolder {
        ItemShoeHomeBinding binding;
        private String tienKhuyenMai = "";

        GiayNoiBatViewHolder(ItemShoeHomeBinding itemShoeBinding) {
            super(itemShoeBinding.getRoot());
            binding = itemShoeBinding;
        }

        void setShoeData(Shoes shoe, List<KhuyenMai> listKhuyenMais) {
            Glide.with(binding.getRoot()).load("https://pvbshoesshop.000webhostapp.com/storage/images/"+shoe.getHinh_anh_1()).placeholder(R.drawable.giay4).into(binding.imageShoe);
            binding.textShoe.setText(shoe.getTen_giay());

            NumberFormat formatter = new DecimalFormat("#,###");

            float price, giamgia = 0;
            price = Integer.parseInt(shoe.getDon_gia());

            if (listKhuyenMais != null) {
                for (KhuyenMai khuyenMai : listKhuyenMais) {
                    if (khuyenMai.getTen_khuyen_mai().equals(shoe.getTen_khuyen_mai())) {
                        giamgia = Integer.parseInt(khuyenMai.getGia_tri_khuyen_mai());
                        break;
                    }
                }
            }

            tienKhuyenMai = formatter.format(price - price*(giamgia / 100));

            if (price != (price - price*(giamgia / 100))) {
                binding.textCost.setVisibility(View.VISIBLE);
                binding.textCost.setText("₫"+formatter.format(price));
                binding.textCost.setPaintFlags(binding.textCost.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                binding.textCost.setVisibility(View.INVISIBLE);
            }

            binding.textSeeNum.setText(shoe.getSo_luot_xem());
            binding.textSell.setText("Đã bán " +shoe.getSo_luong_mua());

            binding.textPrice.setText("₫"+tienKhuyenMai);
            binding.getRoot().setOnClickListener(v -> {
                Intent intent = new Intent(context, ShoeDetailActivity.class);
                intent.putExtra("id_giay", shoe.getId_giay());
                intent.putExtra("ten_giay", shoe.getTen_giay());
                intent.putExtra("ten_loai_giay", shoe.getTen_loai_giay());
                intent.putExtra("ten_thuong_hieu", shoe.getTen_thuong_hieu());
                intent.putExtra("mo_ta", shoe.getMo_ta());
                intent.putExtra("don_gia", shoe.getDon_gia());
                intent.putExtra("so_luong", shoe.getSo_luong());
                intent.putExtra("hinh_anh_1", shoe.getHinh_anh_1());
                intent.putExtra("hinh_anh_2", shoe.getHinh_anh_2());
                intent.putExtra("hinh_anh_3", shoe.getHinh_anh_3());
                intent.putExtra("hinh_anh_4", shoe.getHinh_anh_4());
                intent.putExtra("ten_khuyen_mai", shoe.getTen_khuyen_mai());
                intent.putExtra("so_luong_mua", shoe.getSo_luong_mua());
                intent.putExtra("so_luong_xem", shoe.getSo_luot_xem());
                intent.putExtra("created_at", shoe.getCreated_at());
                intent.putExtra("updated_at", shoe.getUpdated_at());
                intent.putExtra("tien_khuyen_mai", tienKhuyenMai);

                String giaTriKhuyenMai = "0";
                for (KhuyenMai khuyenMai : listKhuyenMais) {
                    if (khuyenMai.getTen_khuyen_mai().equals(shoe.getTen_khuyen_mai())) {
                        giaTriKhuyenMai = khuyenMai.getGia_tri_khuyen_mai();
                        break;
                    }
                }

                ApiService.apiService.updateSeeShoe(shoe.getId_giay(),Integer.parseInt(shoe.getSo_luot_xem())+1).enqueue(new Callback<Shoes>() {
                    @Override
                    public void onResponse(Call<Shoes> call, Response<Shoes> response) { }
                    @Override
                    public void onFailure(Call<Shoes> call, Throwable t) { }
                });

                intent.putExtra("gia_tri_khuyen_mai", giaTriKhuyenMai);
                context.startActivity(intent);

            });
        }

    }
}
