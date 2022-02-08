package com.fjmg.inventory.ui.dependency;


import com.fjmg.inventory.data.model.Dependecy;

public class FormDepedencyPresenter implements FormDepedencyContract.Presenter {
    FormDepedencyContract.View view;
    FormDepedencyContract.Interactor interactor;
    public FormDepedencyPresenter(FormDepedencyContract.View view)
    {
        this.view = view;
        this.interactor = new FormDepedencyInteractor(this);
    }

    @Override
    public void setNameFail()
    {
        view.onNameFail();
    }

    @Override
    public void setShortNameFail()
    {
        view.onShortNameFail();
    }

    @Override
    public void setShortNameDuplicate()
    {
        view.onShortNameDuplicate();

    }

    @Override
    public void setNameDuplicate()
    {
        view.onNameDuplicate();

    }

    @Override
    public void setDescripcionInvalid()
    {
        view.onDescripcionInvalid();
    }

    @Override
    public void add(Dependecy dependecy)
    {
        interactor.add(dependecy);
    }

    @Override
    public void update(Dependecy dependecy) {
        interactor.update(dependecy);
    }

    @Override
    public void setNameValid() {
        view.onNameValid();
    }

    @Override
    public void setShortNameValid() {
        view.onShortNameValid();
    }

    @Override
    public void setDescriptionValid() {
        view.onDescriptionValid();
    }

    @Override
    public void onSucess(String msg) {
        view.onSucess(msg);
    }

    @Override
    public void onFail(String msg) {
        view.onFail(msg);
    }
}
