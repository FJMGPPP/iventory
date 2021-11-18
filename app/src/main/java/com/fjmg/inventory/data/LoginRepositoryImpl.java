package com.fjmg.inventory.data;

import com.fjmg.inventory.data.model.User;
import com.fjmg.inventory.ui.login.LoginContract;

public class LoginRepositoryImpl implements LoginContract.Repository
{
    private LoginContract.OnLoginListener listener;
    private  static LoginRepositoryImpl instance;
    private LoginRepositoryImpl()
    {
       listener = null;
    }
    @Override
    public void login(User user)
    {


    }
    public static LoginRepositoryImpl getInstance(LoginContract.OnLoginListener listener)
    {
        if (instance == null)
        {
            instance = new LoginRepositoryImpl();
        }
        instance.listener = listener;
        return  instance;

    }
}
