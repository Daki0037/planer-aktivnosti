package me.danilo.planeraktivnosti;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.danilo.planeraktivnosti.controllers.ScreenController;
import me.danilo.planeraktivnosti.models.observers.FetchObserver;

import java.io.IOException;

public class Main extends Application  {

    ScreenController screenController = ScreenController.getInstance();
    FetchObserver fetchObserver = FetchObserver.getInstance();

    public void initializeScreens() throws IOException {
        screenController.addScreen("login", FXMLLoader.load(getClass().getResource("/me/danilo/planeraktivnosti/view/Login.fxml")));
        screenController.addScreen("register", FXMLLoader.load(getClass().getResource("/me/danilo/planeraktivnosti/view/Register.fxml")));
        screenController.addScreen("main", FXMLLoader.load(getClass().getResource("/me/danilo/planeraktivnosti/view/ActivityView.fxml")));
        screenController.addScreen("add", FXMLLoader.load(getClass().getResource("/me/danilo/planeraktivnosti/view/NewActivity.fxml")));
        screenController.addScreen("edit", FXMLLoader.load(getClass().getResource("/me/danilo/planeraktivnosti/view/EditActivity.fxml")));
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Planer Aktivnosti - Danilo Obradović 27/2020");
        Parent root = FXMLLoader.load(getClass().getResource("/me/danilo/planeraktivnosti/view/Login.fxml"));
        Scene scene = new Scene(root, 1024, 768);
        screenController.setScene(scene);
        initializeScreens();

        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
