package com.fjmg.inventory.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.fjmg.inventory.data.model.Dependecy;
import com.fjmg.inventory.data.model.Section;

import java.util.List;
@Dao
public interface SectionDAO
{


    @Insert
    long insert(Section section);

    @Update
    void update(Section section);

    @Delete
    void delete(Section section);

    @Query("delete from section")
    void deleteAll();

    @Query("select * from section")
    List<Section> select();

    @Query("select count(id) from section where name = :sectionName")
    int selectForName(String sectionName);
    @Query("select count(id) from section where name = :sectionShortName")
    int selectForShortName(String sectionShortName);
    @Query("select name from dependecy where id = :id")
    String selectDepedencyName(int id);
}
