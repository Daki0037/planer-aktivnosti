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
        String username = usernameField.getText();
        String password = passwordField.getText();

        if(!isUsernameValid(username))
            return;

        String hashedPassword = getHashedPassword(password);

        screenController.changeScreen("activityview");
    }

    public boolean isUsernameValid(String username) {
        if(username.isBlank())
            return false;
        return true;
    }

    public String getHashedPassword(String password) throws NoSuchAlgorithmException {
        String hashedPassword = "";

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] bytes = md.digest();

        hashedPassword = javax.xml.bind.DatatypeConverter.printHexBinary(bytes);

        return hashedPassword;
    }
}
