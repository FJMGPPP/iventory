package com.fjmg.inventory.data;

import android.util.Log;
import android.widget.Toast;

import com.fjmg.inventory.data.model.TipoSuccesAndFails;
import com.fjmg.inventory.data.model.User;
import com.fjmg.inventory.ui.Register.RegisterContract;
import com.fjmg.inventory.ui.login.LoginContract;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class RepositoryFirebase implements LoginContract.Repository , RegisterContract.Repository
{
    private LoginContract.OnLoginListener loginListener;
    private RegisterContract.Bus registerListener;
    private  static RepositoryFirebase instance;
    private ArrayList<User> users;
    private RepositoryFirebase()
    {
        users = new ArrayList<>();
    }

    @Override
    public void login(User user)
    {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener((OnCompleteListener<AuthResult>) task -> {
                    String TAG = "Login firebase";

                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success");
                        loginListener.onSuccess(TipoSuccesAndFails.INICIO_SESION);
                    }
                    else
                        {
                            loginListener.onFail(TipoSuccesAndFails.INICIO_SESION);
                        }
                });
    }

    @Override
    public void Register(User user)
    {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener((OnCompleteListener<AuthResult>) task -> {
                    String TAG = "Register firebase";
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "RegisterWithEmail:success");
                        registerListener.onSucces(TipoSuccesAndFails.CUENTA_CREADA);
                    }
                    else
                    {
                        registerListener.onFail(TipoSuccesAndFails.EMAIL_EXISTENTE);
                    }
                });
    }

    public static RepositoryFirebase getInstance(LoginContract.OnLoginListener listener)
    {
        if (instance == null)
        {
            instance = new RepositoryFirebase();
        }
        instance.loginListener = listener;
        return  instance;

    }
    public static RepositoryFirebase getInstance(RegisterContract.Bus listener)
    {
        if (instance == null)
        {
            instance = new RepositoryFirebase();
        }
        instance.registerListener = listener;
        return  instance;

    }


}
