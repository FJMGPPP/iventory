package com.fjmg.inventory.ui.dependency;

import com.fjmg.inventory.data.DependeciesRepositoryStatic;
import com.fjmg.inventory.data.model.Dependecy;

import java.util.List;

public class DependencyInteractor implements DependencyListContract.onInteractorListener , DependencyListContract.Interactor
{
    DependencyListContract.onInteractorListener listener;
    DependeciesRepositoryStatic repostory;
    public DependencyInteractor(DependencyListContract.onInteractorListener listener)
    {
        this.listener = listener;
        repostory = DependeciesRepositoryStatic.getInstance(this);
    }
    @Override
    public void onFailure(String message) {

    }

    @Override
    public <T> void onSuccess(List<T> List) {
        listener.onSuccess(List);
    }

    @Override
    public void onDeleteSuccess(String message) {
            listener.onDeleteSuccess(message);
    }

    @Override
    public void onUndoSuccess(String message) {
        listener.onUndoSuccess(message);
    }

    @Override
    public void onUpdateSuccess(String message) {
        listener.onUpdateSuccess(message);
    }

    @Override
    public void load()
    {
        repostory.getList();
    }

    @Override
    public void delete(Dependecy dependency) {
        repostory.delete(dependency);
    }

    @Override
    public void undo(Dependecy dependency) {
        repostory.undo(dependency);
    }

    @Override
    public void update(Dependecy dependency) {
        repostory.update(dependency);
    }
}
