package com.fjmg.inventory.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class Dependecy implements Comparable , Serializable
{
    public static final  String TAG = "Dependecy";
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String shortname;
    @NonNull
    private String description;
    private String image;
    @Ignore
    public Dependecy(String name, String shortname, String description, String image) {
        this.name = name;
        this.shortname = shortname;
        this.description = description;
        this.image = image;
    }

    public Dependecy(int id,String name, String shortname, String description, String image) {
        this.id = id;
        this.name = name;
        this.shortname = shortname;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int compareTo(Object o) {
        Dependecy comp = (Dependecy) o;
        if (comp.name.compareTo(name) == 0)
        {
            return  comp.compareTo(description);
        };
        return comp.name.compareTo(name) ;
    }

    public int getId() {
        return id;
    }
}
