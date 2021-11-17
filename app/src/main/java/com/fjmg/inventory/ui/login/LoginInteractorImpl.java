package com.fjmg.inventory.ui.login;

import android.os.Handler;
import android.text.TextUtils;

import com.fjmg.inventory.data.model.User;

public class LoginInteractorImpl
{
    private LoginInteractor listener;
    interface LoginInteractor
    {
        void onUserEmptyError();
        void onPasswordEmptyError();
        void onAuthenticationEmptyError();
        void onSuccess();
    }
    public void validateCredentials(User user)
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                    if (TextUtils.isEmpty(user.getUsername().trim()) && TextUtils.isEmpty(user.getPassword().trim()))
                    {
                        listener.onUserEmptyError();
                        listener.onPasswordEmptyError();
                        return;
                    }
                    if (TextUtils.isEmpty(user.getUsername().trim()))
                    {
                        listener.onUserEmptyError();
                        return;
                    }
                    if (TextUtils.isEmpty(user.getPassword().trim()))
                    {
                        listener.onPasswordEmptyError();
                        return;
                    }
                    listener.onSuccess();

            }
        },2000);
    }
    public LoginInteractorImpl(LoginInteractor listener)
    {
       this.listener =  listener;
    }
}
