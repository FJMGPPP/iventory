package com.fjmg.inventory.base;

import com.fjmg.inventory.data.model.Dependecy;

import java.util.Comparator;

public class DependencyOrdenator implements Comparator {
    @Override
    public int compare(Object o, Object t1) {
       return ((Dependecy) o).compareTo(t1);
    }
}
