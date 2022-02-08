package com.fjmg.inventory.data;

import com.fjmg.inventory.base.OnRepositoryListCallback;
import com.fjmg.inventory.data.dao.DependecyDao;
import com.fjmg.inventory.data.model.Dependecy;
import com.fjmg.inventory.ui.dependency.DependencyListContract;
import com.fjmg.inventory.ui.dependency.FormDepedencyContract;
import com.fjmg.inventory.ui.sections.DependencyAdapterMVP.DependencyAdapterSectionsContract;
import com.fjmg.inventory.ui.sections.DependencyAdapterMVP.DependencyAdapterSectionsInteractor;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DependecyRepositoryDataBase implements DependencyListContract.Repository , FormDepedencyContract.Repository , DependencyAdapterSectionsContract.Repository
{
    static DependecyRepositoryDataBase instance;
    Dependecy deleted;
    OnRepositoryListCallback callback;
    private FormDepedencyContract.Interactor interactor;
    DependecyDao dependecyDao;
    private DependencyAdapterSectionsInteractor interactorSections;

    DependecyRepositoryDataBase()
    {
        dependecyDao = DataBase.getDatabase().dependecyDao();
    }
    public static DependecyRepositoryDataBase getInstance(OnRepositoryListCallback callback)
    {
        if (instance == null)
        {
            instance = new DependecyRepositoryDataBase();
        }
        instance.callback = callback;
        return  instance;

    }

    public static FormDepedencyContract.Repository getInstance(FormDepedencyContract.Interactor interactor)
    {
        if (instance == null)
        {
            instance = new DependecyRepositoryDataBase();
        }
        instance.interactor = interactor;
        return  instance;
    }

    public static DependencyAdapterSectionsContract.Repository getInstance(DependencyAdapterSectionsInteractor dependencyAdapterSectionsInteractor) {
        if (instance == null)
        {
            instance = new DependecyRepositoryDataBase();
        }
        instance.interactorSections = dependencyAdapterSectionsInteractor;
        return  instance;
    }




    @Override
    public void getList(){
        ArrayList<Dependecy> dependecies = null;
        try {
            dependecies = (ArrayList<Dependecy>) DataBase.databaseWriteExecutor.submit(()->
                dependecyDao.select()
            ).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (dependecies == null)
        {
            dependecies = new ArrayList<>();
        }
        if (callback != null)
        {
            callback.onSuccess(dependecies);
        }
        if (interactorSections != null)
        {
            interactorSections.onSucess(dependecies);
        }
    }

    @Override
    public void delete(Dependecy dependency) {
        DataBase.databaseWriteExecutor.submit(()-> {
            dependecyDao.delete(dependency);
        });
        callback.onDeleteSuccess("Eliminado");

    }

    @Override
    public void undo(Dependecy dependecy) {
        DataBase.databaseWriteExecutor.submit(()-> {
            dependecyDao.insert(dependecy);
        });
        callback.onUndoSuccess("Se deshizo el cambio");
    }


    @Override
    public boolean existsName(String dependecyName) {
        int exist = 0;
        try {
            exist = (Integer) DataBase.databaseWriteExecutor.submit(()->
                    dependecyDao.selectForName(dependecyName)
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
    public boolean existsShortName(String dependecyShortName) {
        int exist = 0;
        try {
            exist = (Integer) DataBase.databaseWriteExecutor.submit(()->
                    dependecyDao.selectForShortName(dependecyShortName)
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
    public void add(Dependecy dependecy) {
        DataBase.databaseWriteExecutor.submit(()-> {
            dependecyDao.insert(dependecy);
        });
        interactor.onSucess("Agregado");
    }
    @Override
    public void update(Dependecy dependency) {
       DataBase.databaseWriteExecutor.submit(()-> {
            dependecyDao.update(dependency);
        });
        interactor.onSucess("Actualizado");
    }


}
