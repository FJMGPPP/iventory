package com.fjmg.inventory.ui.sections.DependencyAdapterMVP;

import com.fjmg.inventory.data.DependecyRepositoryDataBase;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DependencyAdapterSectionsInteractor implements DependencyAdapterSectionsContract.Interactor {
    private DependencyAdapterSectionsContract.Repository repository;
    private DependencyAdapterSectionsContract.Presenter presenter;

    public DependencyAdapterSectionsInteractor(DependencyAdapterSectionsContract.Presenter presenter)
    {
        this.presenter = presenter;
        repository = DependecyRepositoryDataBase.getInstance(this);
    }

    @Override
    public void load() throws ExecutionException, InterruptedException {
        repository.getList();
    }

    @Override
    public void onSucess(ArrayList list) {
        presenter.onSucess(list);
    }
}
