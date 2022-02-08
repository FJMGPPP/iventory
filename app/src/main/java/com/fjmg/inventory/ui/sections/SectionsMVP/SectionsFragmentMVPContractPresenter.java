package com.fjmg.inventory.ui.sections.SectionsMVP;

import com.fjmg.inventory.data.model.Section;
import com.fjmg.inventory.ui.sections.DependencyAdapterMVP.DependencyAdapterSectionsContract;

public class SectionsFragmentMVPContractPresenter implements SectionsFragmentMVPContract.Presenter
{
    SectionsFragmentMVPContract.View view;
    SectionsFragmentMVPContract.Interactor interactor;
    public SectionsFragmentMVPContractPresenter(SectionsFragmentMVPContract.View view)
    {
        this.view = view;
        interactor = new SectionsFragmentMVPContractInteractor(this);
    }

    @Override
    public void add(Section section) {
        interactor.add(section);
    }

    @Override
    public void update(Section section) {
        interactor.update(section);
    }

    @Override
    public void onSucess(String msg) {
        view.onSucess(msg);
    }

    @Override
    public void onFail(String msg) {
        view.onFail(msg);
    }

    @Override
    public void setNameFail() {
        view.onNameFail();
    }

    @Override
    public void setShortNameFail() {
        view.onShortNameFail();
    }

    @Override
    public void setDescriptionFail() {
        view.onDescriptionFail();
    }

    @Override
    public void setIdDependencyInvalid() {
        view.onIdDependencyNoSelect();
    }

    @Override
    public void setNameDuplicate() {
        view.onNameDuplicate();
    }

    @Override
    public void setShortDuplicate() {
        view.onShortDuplicate();
    }

    @Override
    public void setValidName() {
        view.onValidName();
    }

    @Override
    public void setValidShortName() {
        view.onValidShortName();
    }

    @Override
    public void setValidDescription() {
        view.onValidDescription();
    }
}
