package me.danilo.planeraktivnosti.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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

    public void registerUser(String username, String password) {
        try {
            URL url = new URL("http://localhost:8080/api/auth/login");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonInput = "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}";
            try (OutputStream os = connection.getOutputStream()) {
                byte[] inputBytes = jsonInput.getBytes("utf-8");
                os.write(inputBytes, 0, inputBytes.length);
            }

            int responseCode = connection.getResponseCode();

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isUsernameValid(String username) {
        if(username.isBlank() || username.length() > 15)
            return false;
        return true;
    }

}
