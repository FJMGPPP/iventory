package com.fjmg.inventory.data;

import com.fjmg.inventory.base.OnRepositoryListCallback;
import com.fjmg.inventory.data.dao.DependecyDao;
import com.fjmg.inventory.data.dao.SectionDAO;
import com.fjmg.inventory.data.model.Dependecy;
import com.fjmg.inventory.data.model.Section;
import com.fjmg.inventory.ui.dependency.FormDepedencyContract;
import com.fjmg.inventory.ui.sections.DependencyAdapterMVP.DependencyAdapterSectionsContract;
import com.fjmg.inventory.ui.sections.DependencyAdapterMVP.DependencyAdapterSectionsInteractor;
import com.fjmg.inventory.ui.sections.SectionsListContract;
import com.fjmg.inventory.ui.sections.SectionsMVP.SectionsFragmentMVPContract;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SectionRepositoryDataBase implements SectionsFragmentMVPContract.Repository , SectionsListContract.Repository {
    static SectionRepositoryDataBase instance;
    private SectionsFragmentMVPContract.Interactor interactor;
    SectionDAO sectionDAO;
    OnRepositoryListCallback callback;

    SectionRepositoryDataBase()
    {
        sectionDAO = DataBase.getDatabase().sectionDAO();
    }
    public static SectionRepositoryDataBase getInstance(SectionsFragmentMVPContract.Interactor interactor)
    {
        if (instance == null)
        {
            instance = new SectionRepositoryDataBase();
        }
        instance.interactor = interactor;
        return  instance;

    }
    public static SectionRepositoryDataBase getInstance(OnRepositoryListCallback callback)
    {
        if (instance == null)
        {
            instance = new SectionRepositoryDataBase();
        }
        instance.callback = callback;
        return  instance;

    }

    @Override
    public boolean existsName(String sectionName) {
        int exist = 0;
        try {
            exist = (Integer) DataBase.databaseWriteExecutor.submit(()->
                    sectionDAO.selectForName(sectionName)
            ).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (exist != 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean existsShortName(String sectionShortName) {
        int exist = 0;
        try {
            exist = (Integer) DataBase.databaseWriteExecutor.submit(()->
                    sectionDAO.selectForShortName(sectionShortName)
            ).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (exist != 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void add(Section section) {
        String name = "";
        try {
            name = (String) DataBase.databaseWriteExecutor.submit(()->
                    sectionDAO.selectDepedencyName(section.getIdDepedency())
            ).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        section.setDependencyName(name);
        DataBase.databaseWriteExecutor.submit(()-> {
            sectionDAO.insert(section);
        });
        interactor.onSucess("Agregado");
    }
    @Override
    public void update(Section section) {
        DataBase.databaseWriteExecutor.submit(()-> {
            sectionDAO.update(section);
        });
        interactor.onSucess("Actualizado");
    }

    @Override
    public void getList() throws ExecutionException, InterruptedException {
        callback.onSuccess ((ArrayList<Section>) DataBase.databaseWriteExecutor.submit(()->
                sectionDAO.select()
        ).get());

    }

    @Override
    public void delete(Section section) {
        DataBase.databaseWriteExecutor.submit(()-> {
            sectionDAO.delete(section);
        });
        interactor.onSucess("Eliminado");
    }

    @Override
    public void undo(Section section) {
        DataBase.databaseWriteExecutor.submit(()-> {
            sectionDAO.insert(section);
        });
        interactor.onSucess("introducido");
    }
}
