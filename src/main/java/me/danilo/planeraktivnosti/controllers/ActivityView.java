package me.danilo.planeraktivnosti.controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import me.danilo.planeraktivnosti.models.Activity;
import me.danilo.planeraktivnosti.models.User;
import me.danilo.planeraktivnosti.models.builders.ActivityBuilder;

import java.util.Date;


public class ActivityView {

    private ScreenController screenController = ScreenController.getInstance();

    private ActivityBuilder builder = new ActivityBuilder();

    @FXML
    private VBox activitiesPane;

    public void onAddActivity() {
        screenController.changeScreen("add");
    }

    public void onLogout() {
        User user = null;
        screenController.changeScreen("login");
        
        Activity activity = builder.setId(1)
                .setName("Ovo je neki test naslov")
                .setDescription("Ovo je neka deskripcija za ovu aktivnost")
                .setPriority(1).build();
        addDynamicPane(activity);
    }

    private void addDynamicPane(Activity activity) {
        HBox activityComponent = createDynamicPane(activity);
        activityComponent.getStyleClass().add("activity-pane");

        activitiesPane.getChildren().add(activityComponent);
    }

    private HBox createDynamicPane(Activity activity) {
        HBox pane = new HBox();

        Label idLabel = new Label(Integer.toString(activity.getId()));
        idLabel.getStyleClass().add("activity-id");
        Label titleLabel = new Label(activity.getName());
        titleLabel.getStyleClass().add("activity-name");
        Label descriptionLabel = new Label(activity.getDescription());
        Label priorityLabel = new Label(Integer.toString(activity.getPriority()));
        Label startDateLabel = new Label(activity.getStartDate().toString());

        pane.getChildren().addAll(idLabel, titleLabel, descriptionLabel, priorityLabel, startDateLabel);

        return pane;
    }

}
