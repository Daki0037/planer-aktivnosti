package me.danilo.planeraktivnosti.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import me.danilo.planeraktivnosti.models.User;

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

//        authentificateUser(username, hashedPassword);

        User user = User.getInstance();
        user.setId(1);
        user.setUsername("Petar");
        screenController.changeScreen("main");
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

    public void authentificateUser(String username, String password) {
        try {
            URL url = new URL("http://localhost:8080/api/auth/login");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Construct and send the authentication data
            String jsonInput = "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}";
            try (OutputStream os = connection.getOutputStream()) {
                byte[] inputBytes = jsonInput.getBytes("utf-8");
                os.write(inputBytes, 0, inputBytes.length);
            }

            int responseCode = connection.getResponseCode();
            System.out.println("API Response Code: " + responseCode);

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
