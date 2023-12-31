package me.danilo.planeraktivnosti.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import me.danilo.planeraktivnosti.models.observers.EditObserver;
import me.danilo.planeraktivnosti.models.observers.MessageObserver;
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
    @FXML
    private Label errorLabel;

    private EditObserver editObserver = EditObserver.getInstance();

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

        if(!authService.isPasswordValid(password))
            return;

        authService.registerUser(username, password);

        usernameField.setText("");
        passwordField.setText("");

        errorLabel.setText(authService.getError());
        editObserver.update();

        screenController.changeScreen("login");
    }



}
