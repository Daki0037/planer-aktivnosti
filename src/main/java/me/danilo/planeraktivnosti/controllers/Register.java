package me.danilo.planeraktivnosti.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import me.danilo.planeraktivnosti.utils.AuthService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

public class Register {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    ScreenController screenController = ScreenController.getInstance();
    AuthService authService = AuthService.getInstance();

    public void onReturnToLoginBtn() {
        screenController.changeScreen("login");
    }

    public void onRegisterBtn() throws NoSuchAlgorithmException {
        String username = usernameField.getText(),
                password = passwordField.getText();

        if(!authService.isUsernameValid(username))
            return;

        password = authService.getHashedPassword(password);

        authService.registerUser(username, password);
    }



}
