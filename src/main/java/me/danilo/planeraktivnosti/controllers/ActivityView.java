package me.danilo.planeraktivnosti.controllers;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import me.danilo.planeraktivnosti.interfaces.Observer;
import me.danilo.planeraktivnosti.models.Activity;
import me.danilo.planeraktivnosti.models.User;
import me.danilo.planeraktivnosti.models.builders.ActivityBuilder;
import me.danilo.planeraktivnosti.models.observers.EditObserver;
import me.danilo.planeraktivnosti.models.observers.FetchObserver;
import me.danilo.planeraktivnosti.utils.ActivityService;

import java.io.IOException;
import java.text.SimpleDateFormat;


public class ActivityView extends ActivityList implements Observer {

    private ScreenController screenController = ScreenController.getInstance();

    private ActivityBuilder builder = new ActivityBuilder();
    private User user = User.getInstance();
    private ActivityService activityService = new ActivityService();
    private int priorityFilter = 0;

    @FXML
    private Label userLabel;
    @FXML
    private TextField searchBar;
    @FXML
    private Button all;
    @FXML
    private Button low;
    @FXML
    private Button medium;
    @FXML
    private Button high;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @FXML
    private VBox activitiesPane;

    public void onAddActivity() {
        screenController.changeScreen("add");
    }

    @FXML
    public void initialize() {
        FetchObserver fetchObserver = FetchObserver.getInstance();
        fetchObserver.addListener(this);
        searchBar.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String name = searchBar.getText();
                showActivitiesWithName(name);
            }
        });
    }

    public void onLogout() {
        User user = null;
        screenController.changeScreen("login");
        clearActivityList();
    }

    public void onAllPriorities() {
        priorityFilter = 0;
        clearAllSelections();
        all.setStyle("-fx-border-color: aliceblue");
        clearActivityList();
        for(Activity activity : this.getActivityList()) {
            addDynamicPane(activity);
        }
    }

    public void onLowPriority() {
        priorityFilter = 1;
        clearAllSelections();
        low.setStyle("-fx-border-color: aliceblue");
        setActivitiesWithPriority(1);
    }

    public void onMediumPriority() {
        priorityFilter = 2;
        clearAllSelections();
        medium.setStyle("-fx-border-color: aliceblue");
        setActivitiesWithPriority(2);
    }

    public void onHighPriority() {
        priorityFilter = 3;
        clearAllSelections();
        high.setStyle("-fx-border-color: aliceblue");
        setActivitiesWithPriority(3);
    }

    public void setActivitiesWithPriority(int priority) {
        clearActivityList();
        for(Activity activity : this.getActivityList()) {
            if(activity.getPriority() == priority)
                addDynamicPane(activity);
        }
    }

    private void addDynamicPane(Activity activity) {
        VBox activityComponent = createDynamicPane(activity);
        activityComponent.getStyleClass().add("activity-pane");

        activitiesPane.getChildren().add(activityComponent);
    }

    private VBox createDynamicPane(Activity activity) {
        VBox pane = new VBox();
        pane.getStyleClass().add("activity-main-panel");

        HBox paneHBox = new HBox();
        paneHBox.getStyleClass().add("activity-first-panel");

        Label idLabel = new Label(Integer.toString(activity.getId()));
        idLabel.getStyleClass().add("activity-id");
        Label titleLabel = new Label(activity.getName());
        titleLabel.getStyleClass().add("activity-name");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button editActivity = new Button("Izmeni");
        editActivity.getStyleClass().add("activityButtons");
        editActivity.setOnAction(event -> {
            EditObserver editObserver = EditObserver.getInstance();
            editObserver.update(activity);
            screenController.changeScreen("edit");
        });

        Button deleteActivity = new Button("Izbriši");
        deleteActivity.getStyleClass().add("activityButtons");
        deleteActivity.setOnAction(event -> {
            try {
                activityService.deleteActivity(activity);
                update();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        paneHBox.getChildren().addAll(idLabel, titleLabel, spacer, editActivity, deleteActivity);

        HBox secondHBox = new HBox();
        secondHBox.getStyleClass().add("activity-second-panel");

        Label startDateLabel = new Label(activity.getStartDate());
        Label endDateLabel = new Label(activity.getEndDate());
        Label priorityLabel = new Label(Integer.toString(activity.getPriority()));
        secondHBox.getChildren().addAll(startDateLabel, endDateLabel, priorityLabel);

        HBox thirdHBox = new HBox();
        thirdHBox.getStyleClass().add("activity-third-panel");

        Label descriptionLabel = new Label(activity.getDescription());
        thirdHBox.getChildren().add(descriptionLabel);

        pane.getChildren().addAll(paneHBox, secondHBox, thirdHBox);

        return pane;
    }

    public void clearActivityList() {
        activitiesPane.getChildren().clear();
    }

    public void showActivitiesWithName(String name) {
        clearActivityList();
        for(Activity activity : getActivityList()) {
            if(activity.getName().contains(name)) {
                if(priorityFilter == 0) {
                    addDynamicPane(activity);
                    return;
                } else if(activity.getPriority() == priorityFilter)
                    addDynamicPane(activity);
            }
        }
    }

    public void update() {
        userLabel.setText("Korisnik: " + user.getUsername());

        activitiesPane.getChildren().clear();
        activityService.fetchActivityData();

        addFetchedActivities();
    }

    @Override
    public void update(Activity activity) {

    }

    public void addFetchedActivities() {
        for(Activity activity : getActivityList()) {
            addDynamicPane(activity);
        }
    }

    public void clearAllSelections() {
        all.setStyle("-fx-border-color: none");
        low.setStyle("-fx-border-color: none");
        medium.setStyle("-fx-border-color: none");
        high.setStyle("-fx-border-color: none");
    }

}
