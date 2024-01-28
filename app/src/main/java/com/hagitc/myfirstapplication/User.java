package com.hagitc.myfirstapplication;

public class User {
    private String name;
    private String email;
    private int year;

    public User()
    {


    }
    public User(String name, String email, int year)
    {
        this.name = name;
        this.email = email;
        this.year = year;
    }
    public String getName()
    {
        return this.name;
    }
    public String getEmail()
    {
        return this.email;
    }
    public int getYear()
    {
        return this.year;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public void setYear(int year)
    {
        this.year = year;
    }
}
