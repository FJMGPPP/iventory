package com.fjmg.inventory.ui.login;

import android.os.Handler;
import android.text.TextUtils;

import com.fjmg.inventory.data.model.User;
import com.fjmg.inventory.utils.CommonUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginInteractorImpl
{
    private LoginContract.Repository repository;
    private LoginContract.LoginInteractor listener;

    public void validateCredentials(User user)
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                    if (TextUtils.isEmpty(user.getEmail().trim()) && TextUtils.isEmpty(user.getPassword().trim()))
                    {
                        listener.onEmailEmptyError();
                        listener.onPasswordEmptyError();
                        return;
                    }
                    if (TextUtils.isEmpty(user.getEmail().trim()))
                    {
                        listener.onEmailEmptyError();
                        return;
                    }
                    if (TextUtils.isEmpty(user.getPassword().trim()))
                    {
                        listener.onPasswordEmptyError();
                        return;
                    }
                    if(!CommonUtils.isEmailValid(user.getEmail()))
                    {
                        listener.onEmailError();
                        return;
                    }
                    if(!CommonUtils.isEmailValid(user.getEmail()))
                    {
                        listener.onEmailError();
                        return;
                    }
                    if(!CommonUtils.isPasswordValid(user.getPassword()))
                    {
                        listener.onPasswordError();
                        return;
                    }
                    //Todo Iniciar Instancia
                    repository.get
                    listener.onSuccess("fin");
                    repository.login(user);
            }
        },2000);
    }
    public LoginInteractorImpl(LoginContract.LoginInteractor listener)
    {
       this.listener =  listener;
    }
}