package com.fjmg.inventory.ui.Register;

import com.fjmg.inventory.base.BasePresenter;
import com.fjmg.inventory.base.OnRepositoryCallback;
import com.fjmg.inventory.data.model.User;
import com.fjmg.inventory.ui.login.LoginContract;

public interface RegisterContract
{
        interface View extends LoginContract.View,OnRepositoryCallback
        {

            void setMessageDontMatch();

            void setUsernameEmptyError();

            void setUsernameError();


        }
        interface Presenter extends OnRepositoryCallback, BasePresenter
        {
            void onEmailEmptyError();

            void onEmailError();

            void onPasswordEmptyError();

            void onPasswordError();

            void onUsernameEmptyError();

            void onUsernameError();

            void onMessageDontMatch();

            void validarSignUp(User user);

        }
        interface Iteractor extends OnRepositoryCallback
        {
            void validarSignUp(User user);
        }
        interface Repository
        {
           void Register(User user);
        }

}
