package com.fjmg.inventory.ui.Register;

import android.service.controls.actions.CommandAction;

import com.fjmg.inventory.data.RepositoryFirebase;
import com.fjmg.inventory.data.RepositoryStatic;
import com.fjmg.inventory.data.model.User;
import com.fjmg.inventory.utils.CommonUtils;

public class RegisterInteractor implements RegisterContract.Iteractor {
    private final RegisterPresenter registerPresenter;
    private final RegisterContract.Repository repository= RepositoryFirebase.getInstance(this);
    public RegisterInteractor(RegisterPresenter registerPresenter) {
        this.registerPresenter = registerPresenter;
    }

    @Override
    public void validarSignUp(User user)
    {   //                  Username Valid
        //-------------------------------------------------
        if (user.getUsername().trim().isEmpty())
        {
            registerPresenter.onUsernameEmptyError();
            return;
        }
        if (!CommonUtils.isUsernameValid(user.getUsername()))
        {
            registerPresenter.onUsernameError();
            return;
        }
        //-------------------------------------------------
        //                  Email Valid
        //-------------------------------------------------
        if (user.getEmail().trim().isEmpty())
        {
            registerPresenter.onEmailEmptyError();
            return;
        }
        if (!CommonUtils.isEmailValid(user.getEmail()))
        {
            registerPresenter.onEmailError();
            return;
        }
        //                  Password Valid
        //-------------------------------------------------
        if (user.getPassword().trim().isEmpty())
        {
            registerPresenter.onPasswordEmptyError();
            return;
        }
        if (!CommonUtils.isPasswordValid(user.getPassword()))
        {
            registerPresenter.onPasswordError();
            return;
        }
        //-------------------------------------------------
        //                 Match Password Valid
        //-------------------------------------------------
        if (!user.getPassword().equals(user.getMatchPassword()))
        {
            registerPresenter.onMessageDontMatch();
            return;
        }
        //-------------------------------------------------
        repository.Register(user);
    }

    @Override
    public void onSuccess(String msg) {
        registerPresenter.onSuccess(msg);
    }

    @Override
    public void onFailure(String msg) {
        registerPresenter.onFailure(msg);
    }
}
