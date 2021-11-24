package com.fjmg.inventory.ui.login;

import android.os.Handler;
import android.text.TextUtils;

import com.fjmg.inventory.data.RepositoryFirebase;
import com.fjmg.inventory.data.model.User;
import com.fjmg.inventory.utils.CommonUtils;

public class LoginInteractor implements LoginContract.OnLoginListener
{

    private LoginContract.OnIteratorListener listener;
    private LoginContract.Repository repository = RepositoryFirebase.getInstance(this);
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
                    repository.login(user);
            }
        },2000);
    }
    public LoginInteractor(LoginContract.OnIteratorListener listener)
    {
        this.listener =  listener;
    }

    @Override
    public void onSuccess(String msg) { this.listener.onSuccess(msg);}

    @Override
    public void onFail(String msg) { listener.onFail(msg); }
}
