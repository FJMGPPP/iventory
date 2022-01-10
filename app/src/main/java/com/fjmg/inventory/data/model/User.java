package com.fjmg.inventory.data.model;

public class User
{

    public static final String TAG ="User" ;
    String email;
    String password;
    String matchPassword;
    String username;
    public User(String email, String password)
    {
        this.email = email;
        this.password = password;
    }
    public User(String email, String password , String matchPassword,  String username)
    {
        this.email = email;
        this.password = password;
        this.matchPassword = matchPassword;
        this.username = username;
    }
    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getMatchPassword() {
        return matchPassword;
    }

    public String getUsername() {
        return username;
    }
}
