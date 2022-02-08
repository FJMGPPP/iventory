package com.fjmg.inventory.ui.dependency;

import com.fjmg.inventory.base.BasePresenter;
import com.fjmg.inventory.base.IProgressView;
import com.fjmg.inventory.base.OnRepositoryListCallback;
import com.fjmg.inventory.data.model.Dependecy;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

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
        void load() throws ExecutionException, InterruptedException;
        void delete(Dependecy depedency);
        void undo(Dependecy dependency);
    }
    interface Repository
    {
        void getList() throws ExecutionException, InterruptedException;
        void delete(Dependecy dependency);
        void undo(Dependecy dependency);
    }
    interface Interactor
    {
        void load() throws ExecutionException, InterruptedException;
        void delete(Dependecy dependency);
        void undo(Dependecy dependency);
    }
    interface onInteractorListener extends OnRepositoryListCallback
    {

    }
    interface Adapter
    {
        void update(ArrayList list);
        void update(Dependecy dependency);
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
