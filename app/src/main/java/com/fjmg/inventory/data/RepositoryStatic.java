package com.fjmg.inventory.data;

import com.fjmg.inventory.data.model.TipoSuccesAndFails;
import com.fjmg.inventory.data.model.User;
import com.fjmg.inventory.ui.Register.RegisterContract;
import com.fjmg.inventory.ui.login.LoginContract;

import java.util.ArrayList;

public class RepositoryStatic implements LoginContract.Repository , RegisterContract.Repository
{
    private LoginContract.OnLoginListener loginListener;
    private RegisterContract.Bus registerListener;
    private  static RepositoryStatic instance;
    private ArrayList<User> users;
    private RepositoryStatic()
    {
        users = new ArrayList<>();
        initialice();
    }

    private void initialice()
    {
        users.add(new User("prueba@gmail.com","PruebaBelica21!"));
        users.add(new User("prueba2@gmail.com","PruebaBelica21!"));
        users.add(new User("prueba3@gmail.com","PruebaBelica21!"));
        users.add(new User("prueba4@gmail.com","PruebaBelica21!"));

    }

    @Override
    public void login(User user)
    {
        for (User userMemory : users)
        {
            if (user.getEmail().equals(userMemory.getEmail()) && user.getPassword().equals(userMemory.getPassword()))
            {
                loginListener.onSuccess(TipoSuccesAndFails.INICIO_SESION);
                return;
            }
        }
        loginListener.onFail(TipoSuccesAndFails.INICIO_SESION);
    }
    public static RepositoryStatic getInstance(LoginContract.OnLoginListener listener)
    {
        if (instance == null)
        {
            instance = new RepositoryStatic();
        }
        instance.loginListener = listener;
        return  instance;

    }
    public static RepositoryStatic getInstance(RegisterContract.Bus listener)
    {
        if (instance == null)
        {
            instance = new RepositoryStatic();
        }
        instance.registerListener = listener;
        return  instance;

    }

    @Override
    public void Register(User user) {
        for (User userMemory : users)
        {
            if (user.getEmail().equals(userMemory.getEmail()))
            {
                registerListener.onFail(TipoSuccesAndFails.EMAIL_EXISTENTE);
                return;
            }
            if (user.getUsername().equals(userMemory.getUsername()))
            {
                registerListener.onFail(TipoSuccesAndFails.USUARIO_EXISTENTE);
                return;
            }
        }
        users.add(user);
        registerListener.onSucces(TipoSuccesAndFails.CUENTA_CREADA);
    }
}
