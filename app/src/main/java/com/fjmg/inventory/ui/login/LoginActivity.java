package com.fjmg.inventory.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fjmg.inventory.R;
import com.fjmg.inventory.data.model.User;
import com.fjmg.inventory.databinding.ActivityLoginBinding;
import com.fjmg.inventory.ui.MainActivity;

public class LoginActivity extends AppCompatActivity implements  LoginContract.View{

    private ActivityLoginBinding binding;
    //Al ser una vista solo podemos acceder a los metodos de la vista
    private LoginContract.Presenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRegisterLogin.setOnClickListener(view -> {
            startActivity(new Intent(this,RegisterActivity.class));
        });
        binding.btnLogin.setOnClickListener(view ->
        {
            presenter.validateCredentials(new User(
                    binding.txtUser.getText().toString(),
                    binding.txtUser.getText().toString()));
        });
        presenter = new LoginPresenter(this);

    }
    //metodos del contrato presenter
    //region
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter = null;
    }

    @Override
    public void setUserEmptyError() {
        binding.txtUser.setError(getString(R.string.errorUserEmpty));
    }

    @Override
    public void setPasswordEmptyError() {
        binding.txtPassword.setError(getString(R.string.errorPasswordEmpty));
    }

    @Override
    public void setAuthenticationEmptyError() {

    }
    //Usuario y contraseña correcta
    @Override
    public void onSuccess() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void showProgressBar() {
    binding.pbLogin.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgressBar() {
        binding.pbLogin.setVisibility(View.INVISIBLE);
    }
    //endregion
}