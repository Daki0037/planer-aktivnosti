package me.danilo.planeraktivnosti.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import me.danilo.planeraktivnosti.interfaces.Observer;
import me.danilo.planeraktivnosti.models.Activity;
import me.danilo.planeraktivnosti.models.User;
import me.danilo.planeraktivnosti.models.observers.MessageObserver;
import me.danilo.planeraktivnosti.utils.AuthService;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Login implements Observer {

    ScreenController screenController = ScreenController.getInstance();
    AuthService authService = AuthService.getInstance();
    User user = User.getInstance();
    private MessageObserver messageObserver = MessageObserver.getInstance();

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;
    @FXML
    private Label successfulLabel;

    @FXML
    public void initialize() {
        messageObserver.addListener(this);
    }

    public void onRegisterBtn() {
        successfulLabel.setText("");
        screenController.changeScreen("register");
    }

    public void onLoginBtn() throws NoSuchAlgorithmException, UnsupportedEncodingException {

        String username = usernameField.getText();
        String password = passwordField.getText();

        user.setUsername(username);
        
        authService.authenticateUser(username, password);
        errorLabel.setText(authService.getError());
    }

    @Override
    public void update() {

    }

    @Override
    public void update(Activity activity) {

    }

    @Override
    public void update(String text) {
        successfulLabel.setText(text);
        errorLabel.setText("");
    }
}
