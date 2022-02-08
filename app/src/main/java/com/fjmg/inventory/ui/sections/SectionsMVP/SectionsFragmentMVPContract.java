package com.fjmg.inventory.ui.sections.SectionsMVP;

import com.fjmg.inventory.data.model.Section;

public interface SectionsFragmentMVPContract
{
    interface View
    {
        void add(Section section);
        void update(Section section);
        void onSucess(String msg);
        void onFail(String msg);
        void onNameFail();
        void onShortNameFail();
        void onDescriptionFail();
        void onIdDependencyNoSelect();
        void onNameDuplicate();
        void onShortDuplicate();
        void onValidName();
        void onValidShortName();
        void onValidDescription();

    }
    interface Presenter
    {
        void add(Section section);
        void update(Section section);
        void onSucess(String msg);
        void onFail(String msg);
        void setNameFail();
        void setShortNameFail();
        void setDescriptionFail();
        void setIdDependencyInvalid();
        void setNameDuplicate();
        void setShortDuplicate();
        void setValidName();
        void setValidShortName();
        void setValidDescription();
    }
    interface Interactor
    {
        boolean verify(Section section,boolean update);
        void add(Section section);
        void update(Section section);
        void onSucess(String msg);
        void onFail(String msg);
    }
    interface Repository
    {
        void add(Section section);
        void update(Section section);
        boolean existsName(String name);
        boolean existsShortName(String shortname);
    }
}
