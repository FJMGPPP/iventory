package com.fjmg.inventory.ui.login;

import com.fjmg.inventory.data.model.User;

public interface LoginContract
{
    interface View extends OnLoginListener
    {
        /**
         * Alternativas del caso de uso. set porque se modifica elementos de la vista
         */
        void setEmailEmptyError();

        void setPasswordEmptyError();

        void setEmailError();

        void setPasswordError();

        void showProgressBar();

        void hideProgressBar();
    }
    interface LoginInteractor extends OnLoginListener
    {
        void onEmailEmptyError();
        void onPasswordEmptyError();
        void onEmailError();
        void onPasswordError();
    }
    interface Presenter
    {
        void validateCredentials(User user);
    }
    interface OnLoginListener
    {
        void onSuccess(String msg);
        void onFail(String msg);
    }
    interface Repository
    {
        void login(User user);
    }
}

