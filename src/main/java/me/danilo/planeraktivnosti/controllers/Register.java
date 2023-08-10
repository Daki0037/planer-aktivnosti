package me.danilo.planeraktivnosti.controllers;

public class Register {

    ScreenController screenController = ScreenController.getInstance();

    public void onReturnToLoginBtn() {
        screenController.changeScreen("login");
    }

    public void onRegisterBtn() {
        // Register logika
    }

}
