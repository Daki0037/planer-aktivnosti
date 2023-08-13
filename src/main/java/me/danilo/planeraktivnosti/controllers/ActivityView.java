package me.danilo.planeraktivnosti.controllers;

public class ActivityView {

    private ScreenController screenController = ScreenController.getInstance();

    public void onAddActivity() {
        screenController.changeScreen("add");
    }

    public void onLogout() {
        System.out.println("logged out");
    }

}
