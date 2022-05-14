package com.example.pvbshoesshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.pvbshoesshop.R;
import com.example.pvbshoesshop.model.Image;

import java.util.List;

public class ImageHomeAdapter extends PagerAdapter {

    private Context context;
    private List<Image> imageList;

    public ImageHomeAdapter(Context context, List<Image> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_image_home_carousel, container, false);
        ImageView imageView = view.findViewById(R.id.image_carousel_home);

        Image image = imageList.get(position);
        if (image != null) {
            Glide.with(context).load("https://pvbshoesshop.000webhostapp.com/storage/images/"+image.getPathImage()).placeholder(R.drawable.banner).into(imageView);
        }

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
