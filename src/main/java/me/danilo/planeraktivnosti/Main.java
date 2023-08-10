package me.danilo.planeraktivnosti;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import me.danilo.planeraktivnosti.controllers.ScreenController;

import java.io.IOException;

public class Main extends Application  {

    ScreenController screenController = ScreenController.getInstance();

    public void initializeScreens() throws IOException {
        screenController.addScreen("login", FXMLLoader.load(getClass().getResource("/me/danilo/planeraktivnosti/view/Login.fxml")));
        screenController.addScreen("register", FXMLLoader.load(getClass().getResource("/me/danilo/planeraktivnosti/view/Register.fxml")));
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Planer Aktivnosti - Danilo Obradović 27/2020");
        Parent root = FXMLLoader.load(getClass().getResource("/me/danilo/planeraktivnosti/view/Login.fxml"));
        Scene scene = new Scene(root);
        screenController.setScene(scene);
        initializeScreens();

        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
