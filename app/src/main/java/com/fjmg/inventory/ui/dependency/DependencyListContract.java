package com.fjmg.inventory.ui.dependency;

import com.fjmg.inventory.base.BasePresenter;
import com.fjmg.inventory.base.IProgressView;
import com.fjmg.inventory.base.OnRepositoryListCallback;
import com.fjmg.inventory.data.model.Dependecy;
import com.google.firebase.components.Dependency;

import java.util.ArrayList;

public interface DependencyListContract
{
    interface View extends IProgressView
    {
        void showData(ArrayList list);
        void showNoData();
        void onDeleteSuccess(String message);
        void onUndoSuccess(String message);
        void onUpdateSuccess(String message);
    }
    interface Presenter extends BasePresenter
    {
        void load();
        void delete(Dependecy depedency);
        void undo(Dependecy dependency);
        void update(Dependecy dependency);
    }
    interface Repository
    {
        void getList();
        void delete(Dependecy dependency);
        void undo(Dependecy dependency);
        void update(Dependecy dependency);
    }
    interface Interactor
    {
        void load();
        void delete(Dependecy dependency);
        void undo(Dependecy dependency);
        void update(Dependecy dependency);
    }
    interface onInteractorListener extends OnRepositoryListCallback
    {

    }
    interface Adapter
    {
        void delete(Dependecy dependecy);
        void undo(Dependecy dependecy);
        void order();
        interface onManageDepedencyLister
        {
            void OnEditDepedency(Dependecy dependency);
            void OnDeleteDepedency(Dependecy dependency);
        }
    }

}
