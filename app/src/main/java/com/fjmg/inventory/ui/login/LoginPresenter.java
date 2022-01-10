package com.fjmg.inventory.ui.login;

import com.fjmg.inventory.data.model.User;

public class LoginPresenter implements LoginContract.Presenter , LoginContract.OnIteratorListener
{
    private LoginContract.View view;
    private LoginInteractor interactor;
    public LoginPresenter(LoginContract.View view)
    {
        this.interactor = new LoginInteractor(this);
        this.view = view;
    }
    //Region interactor;
    //region
    @Override
    public void validateCredentials(User user)
    {
        interactor.validateCredentials(user);
        view.showProgress();
    }

    @Override
    public void onEmailEmptyError() {
        view.showProgress();
        view.setEmailEmptyError();
    }

    @Override
    public void onPasswordEmptyError() {
        view.showProgress();
        view.setPasswordEmptyError();
    }

    @Override
    public void onEmailError() {
        view.showProgress();
        view.setEmailError();
    }

    @Override
    public void onPasswordError() {
        view.hideProgress();
        view.setPasswordError();
    }


    @Override
    public void onSuccess(String msg) {
        view.hideProgress();
        view.onSuccess(msg);
    }

    @Override
    public void onFailure(String msg) {
        view.hideProgress();
        view.onFailure(msg);
    }

    @Override
    public void OnDestroy() {
        this.view = null;
        this.interactor = null;
    }
    //endregion
}
