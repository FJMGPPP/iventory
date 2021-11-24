package com.fjmg.inventory.ui.Register;

import com.fjmg.inventory.data.model.User;

public interface RegisterContract
{
        interface View extends Bus
        {
            void setEmailEmptyError();

            void setEmailError();

            void setPasswordEmptyError();

            void setPasswordError();

            void setMessageDontMatch();

            void setUsernameEmptyError();

            void setUsernameError();


        }
        interface Presenter extends Bus
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
        interface Iteractor extends Bus
        {
            void validarSignUp(User user);
        }
        interface Repository
        {
           void Register(User user);
        }

        interface Bus
        {
            void onSucces(String msg);
            void onFail(String msg);
        }
}
