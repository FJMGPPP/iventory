package com.fjmg.inventory.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.fjmg.inventory.data.model.Dependecy;

import java.util.List;

@Dao
public interface DependecyDao
{
    @Insert
    long insert(Dependecy dependecy);

    @Update
    void update(Dependecy dependecy);

    @Delete
    void delete(Dependecy dependecy);

    @Query("delete from dependecy")
    void deleteAll();

    @Query("select * from dependecy")
    List<Dependecy> select();

    @Query("select count(id) from dependecy where name = :depedencyName")
    int selectForName(String depedencyName);
    @Query("select count(id) from dependecy where name = :depedencyShortName")
    int selectForShortName(String depedencyShortName);
}
