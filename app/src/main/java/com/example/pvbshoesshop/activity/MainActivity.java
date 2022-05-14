package com.example.pvbshoesshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pvbshoesshop.R;
import com.example.pvbshoesshop.activity.cart.CartFragment;
import com.example.pvbshoesshop.activity.home.HomeFragment;
import com.example.pvbshoesshop.activity.shoes.ShoesFragment;
import com.example.pvbshoesshop.activity.user.UserFragment;
import com.example.pvbshoesshop.api.ApiService;
import com.example.pvbshoesshop.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    ShoesFragment shoesFragment = new ShoesFragment();
    UserFragment userFragment = new UserFragment();
    CartFragment cartFragment = new CartFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.action_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                    return true;
                case R.id.action_shoes:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, shoesFragment).commit();
                    return true;
                case R.id.action_cart:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, cartFragment).commit();
                    return true;
                case R.id.action_user:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, userFragment).commit();
                    return true;
            }
            return false;
        });

    }
}