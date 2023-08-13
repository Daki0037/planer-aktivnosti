package me.danilo.planeraktivnosti.models;

import me.danilo.planeraktivnosti.controllers.ScreenController;

public class User {

    public static User instance;
    private String username;
    private int id;

    private User() {}

    public static User getInstance() {
        if(instance == null)
            instance = new User();
        return instance;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

}
