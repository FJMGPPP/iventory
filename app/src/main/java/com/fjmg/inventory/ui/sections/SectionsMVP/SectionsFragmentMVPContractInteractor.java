package com.fjmg.inventory.ui.sections.SectionsMVP;

import com.fjmg.inventory.data.SectionRepositoryDataBase;
import com.fjmg.inventory.data.model.Section;
import com.fjmg.inventory.utils.CommonUtils;

public class SectionsFragmentMVPContractInteractor implements SectionsFragmentMVPContract.Interactor {

    SectionsFragmentMVPContract.Presenter presenter;
    SectionsFragmentMVPContract.Repository repository;
    public SectionsFragmentMVPContractInteractor(SectionsFragmentMVPContract.Presenter presenter) {
        this.presenter = presenter;
        repository = SectionRepositoryDataBase.getInstance(this);
    }

    @Override
    public boolean verify(Section section,boolean update)
    {
        if (section.getName().trim().isEmpty())
        {
            presenter.setNameFail();
            return false;
        }
        if (!update)
        {
            if (repository.existsName(section.getName()))
            {
                presenter.setNameDuplicate();
                return false;
            }
        }
        presenter.setValidName();
        if (    section.getShortname().trim().isEmpty() ||
                section.getShortname().trim().length() < 3 ||
                !CommonUtils.isDependecyShortNameValid(section.getShortname()))
        {
            presenter.setShortNameFail();
            return false;
        }
        if (!update) {
            if (repository.existsShortName(section.getShortname())) {
                presenter.setShortNameFail();
                return false;
            }
        }
        presenter.setValidShortName();
        if (section.getDescription().trim().isEmpty())
        {
            presenter.setDescriptionFail();
            return  false;
        }
        presenter.setValidDescription();
        if (section.getIdDepedency() == -1)
        {
            presenter.setIdDependencyInvalid();
            return false;
        }
        return  true;
    }

    @Override
    public void add(Section section) {
        if (verify(section,false))
        {
            repository.add(section);
        }else
            {
                presenter.onFail("fail");
            }
    }

    @Override
    public void update(Section section) {
        if (verify(section,true))
        {
            repository.update(section);
        }else
        {
            presenter.onFail("fail");
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
