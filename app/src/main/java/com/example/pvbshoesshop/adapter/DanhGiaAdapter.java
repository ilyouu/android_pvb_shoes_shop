package com.example.pvbshoesshop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pvbshoesshop.databinding.ItemDanhGiaBinding;
import com.example.pvbshoesshop.model.DanhGia;
import com.example.pvbshoesshop.model.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DanhGiaAdapter extends RecyclerView.Adapter<DanhGiaAdapter.DanhGiaViewHolder>{

    private List<DanhGia> listDanhGia;
    private List<User> listUser;

    public DanhGiaAdapter(List<DanhGia> listDanhGia, List<User> listUser) {
        this.listDanhGia = listDanhGia;
        this.listUser = listUser;
    }

    @NonNull
    @Override
    public DanhGiaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDanhGiaBinding itemDanhGiaBinding = ItemDanhGiaBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new DanhGiaViewHolder(itemDanhGiaBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhGiaViewHolder holder, int position) {
        holder.setDanhGiaData(listDanhGia.get(position), listUser);
    }

    @Override
    public int getItemCount() {
        return listDanhGia.size();
    }

    public class DanhGiaViewHolder extends RecyclerView.ViewHolder {
        ItemDanhGiaBinding binding;

        public DanhGiaViewHolder(ItemDanhGiaBinding itemDanhGiaBinding) {
            super(itemDanhGiaBinding.getRoot());
            binding = itemDanhGiaBinding;
        }

        void setDanhGiaData(DanhGia danhgia, List<User> listUser) {
            
            binding.textComment.setText(danhgia.getDanh_gia_binh_luan());
            binding.textDate.setText(danhgia.getUpdated_at());

            if (!listUser.isEmpty()) {
                for (User user : listUser) {
                    if (user.getId() == Integer.parseInt(danhgia.getId_user())) {
                        binding.textUser.setText(user.getTen_nguoi_dung());
                        break;
                    }
                }
            }

        }

    }
}
