package com.example.pvbshoesshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.pvbshoesshop.api.ApiService;
import com.example.pvbshoesshop.databinding.ActivitySignInBinding;
import com.example.pvbshoesshop.model.User;
import com.example.pvbshoesshop.utilities.Constants;
import com.example.pvbshoesshop.utilities.PreferenceManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    private ActivitySignInBinding binding;
    private List<User> listUser;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferenceManager = new PreferenceManager(getApplicationContext());

        setListeners();

    }

    private void setListeners() {
        binding.textCreateNewAccount.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), SignUpActivity.class)));

        binding.buttonSignIn.setOnClickListener(v -> {
            if (isValidSignInDetails()) {
                signIn();
            }
        });
    }

    private void signIn() {
        loading(true);

        ApiService.apiService.getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                listUser = response.body();

                int isSignIn = 0;

                for (User user : listUser) {
                    if ((binding.inputEmail.getText().toString().equals(user.getEmail()) || binding.inputEmail.getText().toString().equals(user.getTen_dang_nhap())) && binding.inputPassword.getText().toString().equals(user.getPassword())) {
                        loading(false);
                        isSignIn = 1;
                        preferenceManager.putString(Constants.KEY_SIGN_IN, "signIn");
                        preferenceManager.putString(Constants.KEY_USER_NAME, user.getTen_nguoi_dung());
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                }

                if (isSignIn == 0) {
                    loading(false);
                    showToast("Tài khoản hoặc mật khẩu không đúng!");
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                showToast("Không lấy được dữ liệu người dùng");
            }
        });
    }

    private void loading(Boolean isLoading) {
        if (isLoading) {
            binding.buttonSignIn.setVisibility(View.INVISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.INVISIBLE);
            binding.buttonSignIn.setVisibility(View.VISIBLE);
        }
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private Boolean isValidSignInDetails() {
        if (binding.inputEmail.getText().toString().trim().isEmpty()) {
            showToast("Nhập tên người dùng hoặc email");
            return false;
        } else if (binding.inputPassword.getText().toString().trim().isEmpty()) {
            showToast("Nhập mật khẩu");
            return false;
        } else {
            return true;
        }
    }
}