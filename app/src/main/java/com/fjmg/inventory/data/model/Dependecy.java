package com.fjmg.inventory.data.model;

import java.io.Serializable;

public class Dependecy implements Comparable , Serializable
{
    public static final  String TAG = "Depedency";
    private String name;
    private String shortname;
    private String description;
    private String image;

    public Dependecy(String name, String shortname, String description, String image) {
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
}
