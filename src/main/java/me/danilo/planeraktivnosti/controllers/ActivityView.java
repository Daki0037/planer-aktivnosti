package me.danilo.planeraktivnosti.controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class ActivityView {

    private ScreenController screenController = ScreenController.getInstance();

    @FXML
    private VBox activitiesPane;

    public void onAddActivity() {
        screenController.changeScreen("add");
    }

    public void onLogout() {
        System.out.println("logged out");
    }

    private void addDynamicPane() {
        VBox dynamicPane = createDynamicPane("Dynamic Pane");

        activitiesPane.getChildren().add(dynamicPane);
    }

    private VBox createDynamicPane(String title) {
        VBox pane = new VBox();

        Label titleLabel = new Label(title);
        Button actionButton = new Button("Click Me!");
        Label descriptionLabel = new Label("This is a dynamic pane.");

        pane.getChildren().addAll(titleLabel, actionButton, descriptionLabel);

        return pane;
    }

}
