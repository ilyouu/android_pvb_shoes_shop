package com.example.pvbshoesshop.activity.user;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pvbshoesshop.R;
import com.example.pvbshoesshop.activity.SignInActivity;
import com.example.pvbshoesshop.activity.SignUpActivity;
import com.example.pvbshoesshop.databinding.FragmentUserBinding;
import com.example.pvbshoesshop.utilities.Constants;
import com.example.pvbshoesshop.utilities.PreferenceManager;

import java.util.HashMap;

public class UserFragment extends Fragment {

    private FragmentUserBinding binding;
    private PreferenceManager preferenceManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUserBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        preferenceManager = new PreferenceManager(getContext());
        setListeners();

        if (preferenceManager.getString(Constants.KEY_SIGN_IN) != null) {
            setData();
        }

    }

    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void setListeners() {
        binding.signIn.setOnClickListener(v -> startActivity(new Intent(getActivity(), SignInActivity.class)));
        binding.signUp.setOnClickListener(v -> startActivity(new Intent(getActivity(), SignUpActivity.class)));
        binding.signOut.setOnClickListener(v -> signOut());
    }

    private void setData() {
        binding.imageProfile.setVisibility(View.VISIBLE);
        binding.imageProfile.setImageResource(R.drawable.no_image_profile);

        binding.textName.setVisibility(View.VISIBLE);
        binding.textName.setText(preferenceManager.getString(Constants.KEY_USER_NAME));

        binding.textText.setVisibility(View.VISIBLE);

        binding.signIn.setVisibility(View.GONE);
        binding.signUp.setVisibility(View.GONE);
        binding.signOut.setVisibility(View.VISIBLE);
    }

    private void signOut() {
        showToast("Đăng xuất khỏi tài khoản...");
        preferenceManager.clear();

        binding.imageProfile.setVisibility(View.INVISIBLE);
        binding.textName.setVisibility(View.GONE);
        binding.textText.setVisibility(View.GONE);

        binding.signIn.setVisibility(View.VISIBLE);
        binding.signUp.setVisibility(View.VISIBLE);
        binding.signOut.setVisibility(View.GONE);

    }
}