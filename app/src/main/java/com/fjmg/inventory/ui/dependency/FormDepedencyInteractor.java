package com.fjmg.inventory.ui.dependency;

import com.fjmg.inventory.data.DependecyRepositoryDataBase;
import com.fjmg.inventory.data.model.Dependecy;
import com.fjmg.inventory.utils.CommonUtils;

public class FormDepedencyInteractor implements FormDepedencyContract.Interactor
{
    FormDepedencyContract.Presenter presenter;
    FormDepedencyContract.Repository repository;
    public FormDepedencyInteractor(FormDepedencyContract.Presenter presenter)
    {
        this.presenter = presenter;
        this.repository = DependecyRepositoryDataBase.getInstance(this);
    }

    @Override
    public boolean verify(Dependecy dependecy,boolean update)
    {
        if (dependecy.getName().trim().isEmpty())
        {
            presenter.setNameFail();
            return false;
        }
        if (!update) {
            if (repository.existsName(dependecy.getName())) {
                presenter.setNameDuplicate();
                return false;
            }
        }
        presenter.setNameValid();
        if (    dependecy.getShortname().trim().isEmpty() ||
                dependecy.getShortname().trim().length() < 3 ||
                !CommonUtils.isDependecyShortNameValid(dependecy.getShortname()))
        {
            presenter.setShortNameFail();
            return false;
        }
        if (!update) {
            if (repository.existsShortName(dependecy.getShortname())) {
                presenter.setShortNameDuplicate();
                return false;
            }
        }
        presenter.setShortNameValid();
        if (dependecy.getDescription().trim().isEmpty())
        {
            presenter.setDescripcionInvalid();
            return  false;
        }
        presenter.setDescriptionValid();
        return  true;
    }

    @Override
    public void add(Dependecy dependecy) {
        if (verify(dependecy,false))
        {
            repository.add(dependecy);
        }
        else {
                onFail("fail");
             }
    }

    @Override
    public void update(Dependecy dependecy) {

        if (verify(dependecy,true))
        {
            repository.update(dependecy);
        }
        else {
            onFail("fail");
        }
    }

    @Override
    public void onSucess(String msg) {
        presenter.onSucess(msg);
    }

    @Override
    public void onFail(String msg) {
        presenter.onFail(msg);
    }
}
