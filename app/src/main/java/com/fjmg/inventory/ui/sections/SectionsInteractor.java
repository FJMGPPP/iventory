package com.fjmg.inventory.ui.sections;

import com.fjmg.inventory.data.SectionRepositoryDataBase;
import com.fjmg.inventory.data.model.Section;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class SectionsInteractor implements SectionsListContract.onInteractorListener , SectionsListContract.Interactor
{
    SectionsListContract.onInteractorListener listener;
    SectionRepositoryDataBase repostory;
    public SectionsInteractor(SectionsListContract.onInteractorListener listener)
    {
        this.listener = listener;
        repostory = SectionRepositoryDataBase.getInstance(this);
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
    public void load() throws ExecutionException, InterruptedException {
        repostory.getList();
    }

    @Override
    public void delete(Section section) {
        repostory.delete(section);
    }

    @Override
    public void undo(Section section) {
        repostory.undo(section);
    }
}
