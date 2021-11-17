package com.fjmg.inventory.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fjmg.inventory.databinding.ActivitySplashBinding;
import com.fjmg.inventory.ui.login.LoginActivity;

public class SplashActivity extends AppCompatActivity
{
    private  static  final  long TimeSleep = 3000;//ms (micro seconds)
    ActivitySplashBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /**
     * Vamos a simular que duerme 3 segundos y cuando despierte se ejecuta la activity login
     */
    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                        startLogin();
            }
        },TimeSleep);
    }

    private  void startLogin()
    {
        startActivity(new Intent(this,LoginActivity.class));
        //Voy  a llmar de forma explicita el metodo fish de una Activity para eliminar esta actividad
        // de la pila de actividades porque si el usuario pulsa back
        //no queremos que se visualice
        finish();
    }
}
