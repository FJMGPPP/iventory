package com.fjmg.inventory.ui.dependency;

import com.fjmg.inventory.data.model.Dependecy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DependencyPresenter implements DependencyListContract.Presenter, DependencyListContract.onInteractorListener{
    DependencyInteractor interactor;
    DependencyListContract.View view;
    public DependencyPresenter(DependencyListContract.View view)
    {
        this.view = view;
        this.interactor= new DependencyInteractor(this);
    }
    @Override
    public void OnDestroy() {

    }

    @Override
    public void load() throws ExecutionException, InterruptedException {
        view.showProgress();
        interactor.load();
    }

    @Override
    public void delete(Dependecy depedency) {
        interactor.delete(depedency);
    }

    @Override
    public void undo(Dependecy dependency) {
        interactor.undo(dependency);
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public <T> void onSuccess(List<T> List)
    {

        if (List.size()>0){
            view.showData((ArrayList<Dependecy>)List);
        }
        else {
            view.showNoData();
        }
        view.hideProgress();
    }

    @Override
    public void onDeleteSuccess(String message) {
        view.onDeleteSuccess(message);
    }

    @Override
    public void onUndoSuccess(String message) {
        view.onUndoSuccess(message);
    }

    @Override
    public void onUpdateSuccess(String s) {
        view.onUpdateSuccess(s);
    }
}
