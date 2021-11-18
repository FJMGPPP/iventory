package com.fjmg.inventory.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

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
                    binding.txtEmail.getText().toString(),
                    binding.txtEmail.getText().toString()));
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
    public void setEmailEmptyError() {
        binding.txtEmail.setError(getString(R.string.errorEmailEmpty));
    }

    @Override
    public void setPasswordEmptyError() {
        binding.txtPassword.setError(getString(R.string.errorPasswordEmpty));
    }

    @Override
    public void setEmailError() {
        binding.txtEmail.setError(getString(R.string.errorEmailInvalid));
    }

    @Override
    public void setPasswordError()
    {
        //Todo moificar error
        binding.txtPassword.setError(getString(R.string.errorPasswordInvalid));
    }



    @Override
    public void onSuccess(String msg) {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onFail(String msg) {

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