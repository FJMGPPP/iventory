package com.fjmg.inventory.ui.login;

import com.fjmg.inventory.data.model.User;

public class LoginPresenter implements LoginContract.Presenter , LoginInteractorImpl.LoginInteractor
{
    private LoginContract.View view;
    private LoginInteractorImpl interactor;
    public LoginPresenter(LoginContract.View view)
    {
        this.interactor = new LoginInteractorImpl(this);
        this.view = view;
    }
    //Region interactor;
    //region
    @Override
    public void validateCredentials(User user)
    {
        interactor.validateCredentials(user);
        view.showProgressBar();
    }

    @Override
    public void onEmailEmptyError() {
        view.hideProgressBar();
        view.setEmailEmptyError();
    }

    @Override
    public void onPasswordEmptyError() {
        view.hideProgressBar();
        view.setPasswordEmptyError();
    }

    @Override
    public void onEmailError() {
        view.hideProgressBar();
        view.setEmailError();
    }

    @Override
    public void onPasswordError() {
        view.hideProgressBar();
        view.setPasswordError();
    }

    @Override
    public void onAuthenticationEmptyError() {
        view.hideProgressBar();
        view.setAuthenticationEmptyError();
    }

    @Override
    public void onSuccess() {
        view.hideProgressBar();
        view.onSuccess();

    }
    //endregion
}
