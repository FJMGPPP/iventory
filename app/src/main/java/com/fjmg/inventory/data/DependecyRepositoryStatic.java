package com.fjmg.inventory.data;

import com.fjmg.inventory.base.DependencyOrdenator;
import com.fjmg.inventory.base.OnRepositoryListCallback;
import com.fjmg.inventory.data.model.Dependecy;
import com.fjmg.inventory.ui.dependency.DependencyListContract;
import com.fjmg.inventory.ui.dependency.FormDepedencyContract;

import java.util.ArrayList;
import java.util.Collections;

public class DependecyRepositoryStatic implements DependencyListContract.Repository , FormDepedencyContract.Repository
{
    static DependecyRepositoryStatic instance;
    Dependecy deleted;
    OnRepositoryListCallback callback;
    ArrayList<Dependecy> dependecies;
    private FormDepedencyContract.Interactor interactor;

    DependecyRepositoryStatic()
    {
        Init();
    }
    public static DependecyRepositoryStatic getInstance(OnRepositoryListCallback callback)
    {
        if (instance == null)
        {
            instance = new DependecyRepositoryStatic();
        }
        instance.callback = callback;
        return  instance;

    }

    public static FormDepedencyContract.Repository getInstance(FormDepedencyContract.Interactor interactor)
    {
        if (instance == null)
        {
            instance = new DependecyRepositoryStatic();
        }
        instance.interactor = interactor;
        return  instance;
    }

    private void Init()
        {
            this.dependecies = new ArrayList<Dependecy>();
            dependecies.add(new Dependecy("Aula de 1CFGS","1CFGS","Esto es un texto prueba",null));
            dependecies.add(new Dependecy("Aula de 1CFGM","1CFGS","Esto es un texto prueba",null));
            dependecies.add(new Dependecy("Aula de 2CFGS","1CFGS","Esto es un texto prueba",null));
            dependecies.add(new Dependecy("Aula de 2CFGM","1CFGS","Esto es un texto prueba",null));
            dependecies.add(new Dependecy("Comedor","cm","Este es el comedor",null));
            dependecies.add(new Dependecy("Departamento de secretaria","DS","Esto es secrataria",null));
            dependecies.add(new Dependecy("BigData","1big","Esto es un texto prueba",null));
        }


    @Override
    public void getList()
    {
        Collections.sort(dependecies, new DependencyOrdenator());
        callback.onSuccess(dependecies);
    }

    @Override
    public void delete(Dependecy dependecies) {
        for (int i = 0; i < this.dependecies.size(); i++) {
            if(this.dependecies.get(i).getName().equals(dependecies.getName()))
            {
                deleted = this.dependecies.get(i);
                this.dependecies.remove(i);
                break;
            }
        }
        Collections.sort(this.dependecies, new DependencyOrdenator());
        callback.onDeleteSuccess("Eliminado");

    }

    @Override
    public void undo(Dependecy dependency) {
        this.dependecies.add(dependency);
        Collections.sort(dependecies, new DependencyOrdenator());
        callback.onUndoSuccess("Se deshizo el cambio");
    }


    @Override
    public boolean existsName(String dependecyName) {
        for (int i = 0; i < this.dependecies.size(); i++) {
            if(this.dependecies.get(i).getName().equals(dependecyName))
            {
               return true;
            }
        }
        return  false;
    }

    @Override
    public boolean existsShortName(String dependecyShortName) {
        for (int i = 0; i < this.dependecies.size(); i++) {
            if(this.dependecies.get(i).getShortname().equals(dependecyShortName))
            {
                return true;
            }
        }
        return  false;
    }

    @Override
    public void add(Dependecy dependecy) {
        dependecies.add(dependecy);
        interactor.onSucess("Agregado");
    }
    @Override
    public void update(Dependecy dependency) {
        for (int i = 0; i < this.dependecies.size(); i++) {
            if(this.dependecies.get(i).getName().equals(dependency.getName()))
            {
                this.dependecies.set(i,dependency);
                break;
            }
            Collections.sort(dependecies, new DependencyOrdenator());
        }
        interactor.onSucess("Actualizado");
    }
}
