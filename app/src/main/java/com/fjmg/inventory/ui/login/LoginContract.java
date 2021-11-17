package com.fjmg.inventory.ui.login;

import com.fjmg.inventory.data.model.User;

public interface LoginContract
{
    interface View
    {
        /**
         * Alternativas del caso de uso. set porque se modifica elementos de la vista
         */
        void setUserEmptyError();

        void setPasswordEmptyError();

        void setAuthenticationEmptyError();

        void onSuccess();

        void showProgressBar();

        void hideProgressBar();
    }
    interface Presenter
    {
        void validateCredentials(User user);
    }
}

