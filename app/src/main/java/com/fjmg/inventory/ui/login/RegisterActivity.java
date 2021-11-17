package com.fjmg.inventory.ui.login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.fjmg.inventory.databinding.ActivityLoginBinding;
import com.fjmg.inventory.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnRegister.setOnClickListener(view -> onBackPressed());
    }
}
