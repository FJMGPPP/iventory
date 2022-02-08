package com.fjmg.inventory.ui.sections;

import com.fjmg.inventory.base.BasePresenter;
import com.fjmg.inventory.base.IProgressView;
import com.fjmg.inventory.base.OnRepositoryListCallback;
import com.fjmg.inventory.data.model.Section;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public interface SectionsListContract
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
        void delete(Section section);
        void undo(Section section);
    }
    interface Repository
    {
        void getList() throws ExecutionException, InterruptedException;
        void delete(Section section);
        void undo(Section section);
    }
    interface Interactor
    {
        void load() throws ExecutionException, InterruptedException;
        void delete(Section section);
        void undo(Section section);
    }
    interface onInteractorListener extends OnRepositoryListCallback
    {

    }
    interface Adapter
    {
        void update(ArrayList list);
        void update(Section section);
        void delete(Section section);
        void undo(Section section);
        void order();
        interface onManagesectionLister
        {
            void OnEditSection(Section section);
            void OnDeleteSection(Section section);
        }
    }

}
