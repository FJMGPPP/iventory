package com.fjmg.inventory.ui.sections.DependencyAdapterMVP;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DependencyAdapterSectionsPresenter implements DependencyAdapterSectionsContract.Presenter {
    DependencyAdapterSectionsContract.View view;
    private DependencyAdapterSectionsContract.Interactor interactor;

    public DependencyAdapterSectionsPresenter(DependencyAdapterSectionsContract.View view) {
    this.view = view;
    this.interactor = new DependencyAdapterSectionsInteractor(this);
    }

    @Override
    public void OnDestroy() {
        view = null;
        interactor = null;
    }

    @Override
    public void load() throws ExecutionException, InterruptedException {
        interactor.load();
    }

    @Override
    public void onSucess(ArrayList list) {
        if(list.size() == 0)
        {
            view.showNoData();
        }else
            {
                view.showData(list);
            }
    }
}
