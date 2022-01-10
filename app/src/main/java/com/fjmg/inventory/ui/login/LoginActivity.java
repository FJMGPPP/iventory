package com.fjmg.inventory.ui.login;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fjmg.inventory.R;
import com.fjmg.inventory.data.model.TipoSuccesAndFails;
import com.fjmg.inventory.data.model.User;
import com.fjmg.inventory.databinding.ActivityLoginBinding;
import com.fjmg.inventory.ui.MainActivity;
import com.fjmg.inventory.ui.Register.RegisterActivity;
import com.fjmg.inventory.utils.CommonUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class LoginActivity extends AppCompatActivity implements  LoginContract.View{

    private ActivityLoginBinding binding;
    //Al ser una vista solo podemos acceder a los metodos de la vista
    private LoginContract.Presenter presenter;
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRegisterLogin.setOnClickListener(view -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
        binding.btnLogin.setOnClickListener(view ->
        {
            presenter.validateCredentials(new User(
                    binding.txtEmail.getText().toString(),
                    binding.txtPassword.getText().toString()));
        });

        binding.txtEmail.addTextChangedListener(new LoginTextWatcher(binding.txtEmail));
        binding.txtPassword.addTextChangedListener(new LoginTextWatcher(binding.txtPassword));
        presenter = new LoginPresenter(this);
        EventBus.getDefault().register(this);
        if (isSesionStart())
        {
            startApp();
        }
    }

    private boolean isSesionStart()
    {
        return  true;
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    @Override
    public void onEvent(Event event)
    {
        hideProgress();
        onFailure(event.getMessage());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.OnDestroy();
        presenter = null;
        EventBus.getDefault().unregister(this);
    }
    //metodos del contrato presenter
    //region


    @Override
    public void setEmailEmptyError() {
        hideProgress();
        binding.txtEmail.setError(getString(R.string.errorEmailEmpty));
    }

    @Override
    public void setPasswordEmptyError() {
        hideProgress();
        binding.txtPassword.setError(getString(R.string.errorPasswordEmpty));
    }

    @Override
    public void setEmailError() {
        hideProgress();
        binding.txtEmail.setError(getString(R.string.errorEmailInvalid));
    }

    @Override
    public void setPasswordError()
    {
        hideProgress();
        binding.txtPassword.setError(getString(R.string.errorPasswordInvalid));
    }


    @Override
    public void onSuccess(String msg) {
        if (msg.equals(TipoSuccesAndFails.INICIO_SESION)) {
            addLogin();
            startApp();
        }
    }

    private void addLogin()
    {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        editor.putString(User.TAG,binding.txtEmail.getText().toString());
        editor.apply();

    }
    private void startApp()
    {
        startActivity(new Intent(this, MainActivity.class));
    }
    @Override
    public void onFailure(String msg) {
        hideProgress();
        switch (msg)
        {
            case TipoSuccesAndFails.INICIO_SESION:
                Toast.makeText(getApplicationContext(),getString(R.string.errorLoginOnFail) , Toast.LENGTH_LONG).show();
                break;
            case TipoSuccesAndFails.PERDIDA_CONEXION:
                Toast.makeText(getApplicationContext(),getString(R.string.error) , Toast.LENGTH_LONG).show();
                break;
        }

    }

    @Override
    public void hideProgress() {
        binding.pbLogin.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showProgress() {
        binding.pbLogin.setVisibility(View.VISIBLE);
    }

    //endregion
    class LoginTextWatcher implements TextWatcher
    {
        View view;
        public LoginTextWatcher(View view)
        {
            super();
            this.view = view;
        }
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId())
            {
                case R.id.txtEmail:
                    validateEmail(((EditText)view).getText().toString());
                    break;
                case R.id.txtPassword:
                    validatePassword(((EditText)view).getText().toString());
                    break;

            }
        }
        void validateEmail(String email)
        {
            if (email.isEmpty())
            {
                binding.txtEmail.setError(getString(R.string.errorEmailEmpty));
                return;
            }
            if (!CommonUtils.isEmailValid(email))
            {
                binding.txtEmail.setError(getString(R.string.errorEmailInvalid));
                return;
            }
            binding.txtEmail.setError(null);
        }
        void validatePassword(String password)
        {
            if (password.isEmpty())
            {
                binding.txtPassword.setError(getString(R.string.errorPasswordEmpty));
                return;
            }
            if (!CommonUtils.isPasswordValid(password))
            {
                binding.txtPassword.setError(getString(R.string.errorPasswordInvalid));
                return;
            }
            binding.txtEmail.setError(null);
        }


    }
}