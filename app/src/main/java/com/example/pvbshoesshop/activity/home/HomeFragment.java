package com.example.pvbshoesshop.activity.home;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pvbshoesshop.R;
import com.example.pvbshoesshop.adapter.HomeAdapter;
import com.example.pvbshoesshop.adapter.ImageHomeAdapter;
import com.example.pvbshoesshop.api.ApiService;
import com.example.pvbshoesshop.databinding.FragmentHomeBinding;
import com.example.pvbshoesshop.model.Image;
import com.example.pvbshoesshop.model.KhuyenMai;
import com.example.pvbshoesshop.model.Shoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private FragmentHomeBinding binding;
    private List<Shoes> listShoesNoiBat;
    private List<Shoes> listShoesMoiNhat;
    private List<Shoes> listShoesGiamGia;
    private List<KhuyenMai> khuyenMaisList;
    private List<Image> imageList;
    private Timer timer;
    private Context context;
    private ImageHomeAdapter imageAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        context = container.getContext();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageList = getListImage();
        imageAdapter = new ImageHomeAdapter(context, imageList);
        binding.viewPager.setAdapter(imageAdapter);

        binding.circleIndicator.setViewPager(binding.viewPager);
        imageAdapter.registerDataSetObserver(binding.circleIndicator.getDataSetObserver());

        autoCarousel();

        binding.swipeRefreshLayout.setOnRefreshListener(this::onRefresh);
        binding.swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.color_main));
        binding.swipeRefreshLayout.setRefreshing(true);

        getListKhuyenMai();

    }

    private List<Image> getListImage() {
        List<Image> list = new ArrayList<>();
        list.add(new Image("banner1.jpg"));
        list.add(new Image("banner2.jpg"));
        list.add(new Image("banner3.jpg"));
        list.add(new Image("banner4.jpg"));
        list.add(new Image("banner5.jpg"));
        return list;
    }

    private void autoCarousel() {
        if (timer == null) {
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int here = binding.viewPager.getCurrentItem();
                        int total = imageList.size() - 1;
                        if (here < total) {
                            here++;
                            binding.viewPager.setCurrentItem(here);
                        } else {
                            binding.viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 500, 3000);
    }

    private void getListShoesNoiBat() {
        ApiService.apiService.getShoesNoiBat().enqueue(new Callback<List<Shoes>>() {
            @Override
            public void onResponse(Call<List<Shoes>> call, Response<List<Shoes>> response) {
                listShoesNoiBat = response.body();
                setAdapterGiayNoiBat();
                getListShoesMoiNhat();
            }

            @Override
            public void onFailure(Call<List<Shoes>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Không lấy được dữ liệu giày!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getListShoesMoiNhat() {
        ApiService.apiService.getShoesMoiNhat().enqueue(new Callback<List<Shoes>>() {
            @Override
            public void onResponse(Call<List<Shoes>> call, Response<List<Shoes>> response) {
                listShoesMoiNhat = response.body();
                setAdapterGiayMoiNhat();
            }

            @Override
            public void onFailure(Call<List<Shoes>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Không lấy được dữ liệu giày!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getListShoesDangGiamGia() {
        ApiService.apiService.getShoesDangGiamGia().enqueue(new Callback<List<Shoes>>() {
            @Override
            public void onResponse(Call<List<Shoes>> call, Response<List<Shoes>> response) {
                listShoesGiamGia = response.body();
                setAdapterGiayDangGiamGia();
                getListShoesNoiBat();
            }

            @Override
            public void onFailure(Call<List<Shoes>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Không lấy được dữ liệu giày!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getListKhuyenMai() {
        ApiService.apiService.getKhuyenMais().enqueue(new Callback<List<KhuyenMai>>() {
            @Override
            public void onResponse(Call<List<KhuyenMai>> call, Response<List<KhuyenMai>> response) {
                khuyenMaisList = response.body();
                getListShoesDangGiamGia();
            }

            @Override
            public void onFailure(Call<List<KhuyenMai>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Không lấy được dữ liệu khuyến mãi!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapterGiayNoiBat() {
        binding.giayNoiBatRecyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));

        binding.giayNoiBatprogressBar.setVisibility(View.INVISIBLE);
        HomeAdapter homeAdapter = new HomeAdapter(listShoesNoiBat, khuyenMaisList);
        binding.giayNoiBatRecyclerView.setAdapter(homeAdapter);
        binding.giayNoiBatRecyclerView.setVisibility(View.VISIBLE);
    }

    private void setAdapterGiayMoiNhat() {
        binding.giayMoiNhatRecyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));

        binding.giayMoiNhatprogressBar.setVisibility(View.INVISIBLE);
        HomeAdapter homeAdapter = new HomeAdapter(listShoesMoiNhat, khuyenMaisList);
        binding.giayMoiNhatRecyclerView.setAdapter(homeAdapter);
        binding.giayMoiNhatRecyclerView.setVisibility(View.VISIBLE);

        binding.swipeRefreshLayout.setRefreshing(false);
    }

    private void setAdapterGiayDangGiamGia() {
        binding.giayGiamGiaRecyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));

        binding.giayGiamGiaprogressBar.setVisibility(View.INVISIBLE);
        HomeAdapter homeAdapter = new HomeAdapter(listShoesGiamGia, khuyenMaisList);
        binding.giayGiamGiaRecyclerView.setAdapter(homeAdapter);
        binding.giayGiamGiaRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public void onRefresh() {
        getListKhuyenMai();

    }
}