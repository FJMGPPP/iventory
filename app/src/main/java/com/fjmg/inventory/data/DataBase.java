package com.fjmg.inventory.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.fjmg.inventory.data.dao.DependecyDao;
import com.fjmg.inventory.data.dao.SectionDAO;
import com.fjmg.inventory.data.model.Dependecy;
import com.fjmg.inventory.data.model.Section;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Definir Atributo
@Database(entities = {Dependecy.class, Section.class},version = 1)
public abstract class DataBase extends RoomDatabase {
    public abstract SectionDAO sectionDAO();
    public abstract DependecyDao dependecyDao();
    private static volatile DataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    static DataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DataBase.class, "inventory")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

  
    public static void create(final Context context) {
        if (INSTANCE == null) {
            synchronized (DataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DataBase.class, "inventory")
                            .build();
                }
            }
        }
    }

    public static DataBase getDatabase() {
        return INSTANCE;

    }
}
