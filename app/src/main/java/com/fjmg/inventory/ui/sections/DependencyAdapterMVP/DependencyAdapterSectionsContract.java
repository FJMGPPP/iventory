package com.fjmg.inventory.ui.sections.DependencyAdapterMVP;

import com.fjmg.inventory.base.BasePresenter;
import com.fjmg.inventory.base.IProgressView;
import com.fjmg.inventory.base.OnRepositoryListCallback;
import com.fjmg.inventory.data.model.Dependecy;
import com.fjmg.inventory.ui.dependency.FormDepedencyContract;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public interface DependencyAdapterSectionsContract
{
    interface View
    {
        void showData(ArrayList list);
        void showNoData();
    }
    interface Presenter extends BasePresenter
    {
        void load() throws ExecutionException, InterruptedException;
        void onSucess(ArrayList list);
    }
    interface Interactor
    {
        void load() throws ExecutionException, InterruptedException;
        void onSucess(ArrayList list);
    }
    interface Repository
    {
        void getList();
    }
    interface Adapter
    {
        void update(ArrayList list);
    }

}
