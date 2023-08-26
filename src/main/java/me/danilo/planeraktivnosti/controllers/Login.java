package me.danilo.planeraktivnosti.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import me.danilo.planeraktivnosti.models.User;
import me.danilo.planeraktivnosti.utils.AuthService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login {

    ScreenController screenController = ScreenController.getInstance();
    AuthService authService = AuthService.getInstance();
    User user = User.getInstance();

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;

    public void onRegisterBtn() {
        screenController.changeScreen("register");
    }

    public void onLoginBtn() throws NoSuchAlgorithmException, UnsupportedEncodingException {

        String username = usernameField.getText();
        String password = passwordField.getText();

        user.setUsername(username);
        
        authService.authenticateUser(username, password);
        errorLabel.setText(authService.getError());
    }


}
