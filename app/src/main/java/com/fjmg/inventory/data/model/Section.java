package com.fjmg.inventory.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(foreignKeys = @ForeignKey(entity = Dependecy.class,parentColumns = "id", childColumns = "idDepedency"))
public class Section implements Serializable {
    public static final String TAG ="SECTION" ;
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private int idDepedency;
    @NonNull
    private String name;
    @NonNull
    private String shortname;
    @NonNull
    private String description;
    @NonNull
    private String dependencyName;

    public Section(int id, int idDepedency, @NonNull String name, @NonNull String shortname, @NonNull String description, @NonNull String dependencyName) {
        this.id = id;
        this.idDepedency = idDepedency;
        this.name = name;
        this.shortname = shortname;
        this.description = description;
        this.dependencyName = dependencyName;
    }

    @Ignore
    public Section(int idDepedency, @NonNull String name, @NonNull String shortname, @NonNull String description) {
        this.idDepedency = idDepedency;
        this.name = name;
        this.shortname = shortname;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDepedency() {
        return idDepedency;
    }

    public void setIdDepedency(int idDepedency) {
        this.idDepedency = idDepedency;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getShortname() {
        return shortname;
    }

    public void setShortname(@NonNull String shortname) {
        this.shortname = shortname;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    @NonNull
    public String getDependencyName() {
        return dependencyName;
    }

    public void setDependencyName(@NonNull String dependencyName) {
        this.dependencyName = dependencyName;
    }
}
