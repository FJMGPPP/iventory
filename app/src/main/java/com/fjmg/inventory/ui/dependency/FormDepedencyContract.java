package com.fjmg.inventory.ui.dependency;

import com.fjmg.inventory.data.model.Dependecy;

public interface FormDepedencyContract
{
    interface View extends CallBacks
    {
       void onNameFail();
       void onShortNameFail();
       void onShortNameDuplicate();
       void onNameDuplicate();
       void onDescripcionInvalid();
       void onNameValid();
       void onShortNameValid();
       void onDescriptionValid();

    }
    interface Presenter extends CallBacks
    {
        void setNameFail();
        void setShortNameFail();
        void setShortNameDuplicate();
        void setNameDuplicate();
        void setDescripcionInvalid();
        void add(Dependecy dependecy);
        void update(Dependecy dependecy);
        void setNameValid();
        void setShortNameValid();
        void setDescriptionValid();
    }
    interface Interactor extends CallBacks
    {
        boolean verify(Dependecy dependecy,boolean update);
        void add(Dependecy dependecy);
        void update(Dependecy dependecy);
    }
    interface Repository
    {
        boolean existsName(String dependecyName);
        boolean existsShortName(String dependecyShortName);
        void add(Dependecy dependecy);
        void update(Dependecy dependecy);
    }
    interface CallBacks
    {
        void onSucess(String msg);
        void onFail(String msg);
    }
}
