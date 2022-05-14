package com.example.pvbshoesshop.activity.shoes;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.pvbshoesshop.R;
import com.example.pvbshoesshop.adapter.ShoesAdapter;
import com.example.pvbshoesshop.api.ApiService;
import com.example.pvbshoesshop.databinding.FragmentShoesBinding;
import com.example.pvbshoesshop.model.KhuyenMai;
import com.example.pvbshoesshop.model.Shoes;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private FragmentShoesBinding binding;
    private List<Shoes> listShoes;
    private List<KhuyenMai> khuyenMaisList;
    private ShoesAdapter shoesAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentShoesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.container.setOnRefreshListener(this::onRefresh);
        binding.container.setColorSchemeColors(getResources().getColor(R.color.color_main));
        binding.container.setRefreshing(true);

        setData();
    }

    private void setData() {
        ApiService.apiService.getShoes().enqueue(new Callback<List<Shoes>>() {
            @Override
            public void onResponse(Call<List<Shoes>> call, Response<List<Shoes>> response) {
                listShoes = response.body();
                binding.textALL.setText("Có tất cả "+response.body().size()+ " mẫu giày «");

                ApiService.apiService.getKhuyenMais().enqueue(new Callback<List<KhuyenMai>>() {
                    @Override
                    public void onResponse(Call<List<KhuyenMai>> call, Response<List<KhuyenMai>> response) {
                        khuyenMaisList = response.body();

                        binding.shoesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

                        binding.container.setRefreshing(false);
                        binding.progressBar.setVisibility(View.INVISIBLE);
                        shoesAdapter = new ShoesAdapter(listShoes, khuyenMaisList);
                        binding.shoesRecyclerView.setAdapter(shoesAdapter);
                        binding.shoesRecyclerView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onFailure(Call<List<KhuyenMai>> call, Throwable t) {
                        Toast.makeText(getActivity().getApplicationContext(), "Không lấy được dữ liệu khuyến mãi!", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Shoes>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Không lấy được dữ liệu giày!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRefresh() {
        setData();
    }


}