package com.fjmg.inventory.ui.Register;

import com.fjmg.inventory.data.model.User;

public class RegisterPresenter implements RegisterContract.Presenter
{
    RegisterContract.View view;
    RegisterContract.Iteractor iteractor = new RegisterInteractor(this);
    public RegisterPresenter(RegisterContract.View view)
    {
        this.view = view;
    }
    @Override
    public void onEmailEmptyError() {
        view.setEmailEmptyError();
    }

    @Override
    public void onEmailError() {
        view.setEmailError();
    }

    @Override
    public void onPasswordEmptyError() {
        view.setPasswordEmptyError();
    }

    @Override
    public void onPasswordError() {
        view.setPasswordError();
    }

    @Override
    public void onUsernameEmptyError() {
        view.setUsernameEmptyError();
    }

    @Override
    public void onUsernameError() {
        view.setUsernameError();
    }

    @Override
    public void onMessageDontMatch() {
        view.setMessageDontMatch();
    }

    @Override
    public void validarSignUp(User user) {
        iteractor.validarSignUp(user);
    }

    @Override
    public void onSuccess(String msg) {
        view.onSuccess(msg);
    }

    @Override
    public void onFailure(String msg) {
        view.onFailure(msg);
    }

    @Override
    public void OnDestroy() {
        view = null;
        iteractor = null;
    }
}
