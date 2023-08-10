package me.danilo.planeraktivnosti.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Register {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    ScreenController screenController = ScreenController.getInstance();

    public void onReturnToLoginBtn() {
        screenController.changeScreen("login");
    }

    public void onRegisterBtn() {
        // Register logika
        String username = usernameField.getText(),
                password = passwordField.getText();

        if(!isUsernameValid(username))
            return;

    }

    public boolean isUsernameValid(String username) {
        if(username.isBlank() || username.length() > 15)
            return false;
        return true;
    }

}
