package me.danilo.planeraktivnosti.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import me.danilo.planeraktivnosti.interfaces.Observer;
import me.danilo.planeraktivnosti.models.Activity;
import me.danilo.planeraktivnosti.models.User;
import me.danilo.planeraktivnosti.models.builders.ActivityBuilder;
import me.danilo.planeraktivnosti.models.observers.UsernameObserver;

import java.text.SimpleDateFormat;


public class ActivityView implements Observer {

    private ScreenController screenController = ScreenController.getInstance();

    private ActivityBuilder builder = new ActivityBuilder();
    private User user = User.getInstance();
    @FXML
    private Label userLabel;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @FXML
    private VBox activitiesPane;

    public void onAddActivity() {
        screenController.changeScreen("add");
    }

    @FXML
    public void initialize() {
        UsernameObserver usernameObserver = UsernameObserver.getInstance();
        usernameObserver.addListener(this);
    }

    public void onLogout() {
        User user = null;
        screenController.changeScreen("login");
        clearActivityList();
    }

    public void onAllPriorities() {

    }

    public void onLowPriority() {

    }

    public void onMediumPriority() {

    }

    public void onHighPriority() {

    }

    private void addDynamicPane(Activity activity) {
        VBox activityComponent = createDynamicPane(activity);
        activityComponent.getStyleClass().add("activity-pane");

        activitiesPane.getChildren().add(activityComponent);
    }

    private VBox createDynamicPane(Activity activity) {
        VBox pane = new VBox();
        HBox paneHBox = new HBox();

        Label idLabel = new Label(Integer.toString(activity.getId()));
        idLabel.getStyleClass().add("activity-id");
        Label titleLabel = new Label(activity.getName());
        titleLabel.getStyleClass().add("activity-name");

        Button editActivity = new Button("Izmeni");
        Button deleteActivity = new Button("Izbriši");

        paneHBox.getChildren().addAll(idLabel, titleLabel, editActivity, deleteActivity);

        HBox secondHBox = new HBox();
        Label startDateLabel = new Label(dateFormat.format(activity.getStartDate()));
        Label priorityLabel = new Label(Integer.toString(activity.getPriority()));
        secondHBox.getChildren().addAll(startDateLabel, priorityLabel);


        Label descriptionLabel = new Label(activity.getDescription());

        pane.getChildren().addAll(paneHBox, secondHBox, descriptionLabel);

        return pane;
    }

    public void clearActivityList() {
        activitiesPane.getChildren().clear();
    }

    public void update() {
        userLabel.setText("Korisnik: " + user.getUsername());
    }

}
