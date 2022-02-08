package com.fjmg.inventory.ui.sections;

import com.fjmg.inventory.data.model.Section;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SectionsPresenter implements SectionsListContract.Presenter, SectionsListContract.onInteractorListener{
    SectionsInteractor interactor;
    SectionsListContract.View view;
    public SectionsPresenter(SectionsListContract.View view)
    {
        this.view = view;
        this.interactor= new SectionsInteractor(this);
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
    public void delete(Section section) {
        interactor.delete(section);
    }

    @Override
    public void undo(Section dependency) {
        interactor.undo(dependency);
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public <T> void onSuccess(List<T> List)
    {

        if (List.size()>0){
            view.showData((ArrayList<Section>)List);
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
