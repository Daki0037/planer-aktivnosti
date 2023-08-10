package me.danilo.planeraktivnosti.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login {

    ScreenController screenController = ScreenController.getInstance();

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    public void onRegisterBtn() {
        screenController.changeScreen("register");
    }

    public void onLoginBtn() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //Login logika
        String username = usernameField.getText();
        String password = passwordField.getText();

        if(!isUsernameValid(username))
            return;

        byte[] bytesOfMessage = password.getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] theMD5digest = md.digest(bytesOfMessage);
        System.out.println(theMD5digest);


    }

    public boolean isUsernameValid(String username) {
        if(username.isBlank())
            return false;
        return true;
    }
}
