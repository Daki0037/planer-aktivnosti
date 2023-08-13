package me.danilo.planeraktivnosti.controllers;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;

public class ScreenController {

    public static ScreenController instance;

    private HashMap<String, Pane> screens = new HashMap<>();
    private Scene main;

    private ScreenController() {}

    public static ScreenController getInstance() {
        if(instance == null)
            instance = new ScreenController();
        return instance;
    }

    public void setScene(Scene main) {
        this.main = main;
    }

    public void addScreen(String name, Pane pane) {
        screens.put(name, pane);
    }

    public void removeScreens(String name) {
        screens.remove(name);
    }

    public void changeScreen(String name) {
        main.setRoot(screens.get(name));
    }

    public Scene getScene() {
        return main;
    }

}
