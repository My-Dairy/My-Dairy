package com.example.mydairy.Details;

public class User {

    private  String name , surname , location;

    public User()
    {
        this.name = "";
        this.surname = "";
        this.location = "";
    }

    public User(String name, String surname, String location) {
        this.name = name;
        this.surname = surname;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLocation() {
        return location;
    }
}
