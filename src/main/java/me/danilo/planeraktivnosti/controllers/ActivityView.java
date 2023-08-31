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
import me.danilo.planeraktivnosti.models.observers.MessageObserver;
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

    @FXML
    private Button allCompletion;
    @FXML
    private Button completedButton;
    @FXML
    private Button notCompletedButton;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private EditObserver editObserver = EditObserver.getInstance();
    private int counter = 1;
    private boolean showCompleted = true, showNotCompleted = true;
    private MessageObserver messageObserver = MessageObserver.getInstance();

    @FXML
    private VBox activitiesPane;

    public void onAddActivity() {
        editObserver.update();
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

        messageObserver.update("");
        screenController.changeScreen("login");
        clearActivityList();
    }

    public void onAllPriorities() {
        priorityFilter = 0;
        clearPrioritySelections();
        all.setStyle("-fx-border-color: aliceblue");
        showActivities();
    }

    public void onLowPriority() {
        priorityFilter = 1;
        clearPrioritySelections();
        low.setStyle("-fx-border-color: aliceblue");
        showActivities();
    }

    public void onMediumPriority() {
        priorityFilter = 2;
        clearPrioritySelections();
        medium.setStyle("-fx-border-color: aliceblue");
        showActivities();
    }

    public void onHighPriority() {
        priorityFilter = 3;
        clearPrioritySelections();
        high.setStyle("-fx-border-color: aliceblue");
        showActivities();
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
        paneHBox.setSpacing(10);

        Label idLabel = new Label(Integer.toString(counter));
        idLabel.getStyleClass().add("activity-id");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label titleLabel = new Label(activity.getName());
        titleLabel.getStyleClass().add("activity-name");

        Region spacerTwo = new Region();
        HBox.setHgrow(spacerTwo, Priority.ALWAYS);

        Button editActivity = new Button("Izmeni");
        editActivity.getStyleClass().add("activityButtons");
        editActivity.setOnAction(event -> {
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

        paneHBox.getChildren().addAll(idLabel, spacer, titleLabel, spacerTwo, editActivity, deleteActivity);

        HBox secondHBox = new HBox();
        secondHBox.getStyleClass().add("activity-second-panel");
        secondHBox.setSpacing(10);

        Label startDateLabel = new Label("Početak: " + activity.getStartDate().replace('-', '/'));
        Label endDateLabel = new Label("Kraj: " + activity.getEndDate().replace('-', '/'));
        Label priorityValue = getPriority(activity.getPriority());

        Region secondSpacer = new Region();
        HBox.setHgrow(secondSpacer, Priority.ALWAYS);

        Label completedLabel;
        if(activity.isCompleted()) {
            completedLabel = new Label("Završena");
            completedLabel.getStyleClass().add("activity-completed");
        } else {
            completedLabel = new Label("Nije završena");
            completedLabel.getStyleClass().add("activity-not-completed");
        }
        secondHBox.getChildren().addAll(startDateLabel, endDateLabel, priorityValue, secondSpacer, completedLabel);

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
                filterActivities(activity);
            }
        }
    }

    public void showActivities() {
        clearActivityList();
        for(Activity activity : getActivityList()) {
            filterActivities(activity);
        }
    }

    public void filterActivities(Activity activity) {
        if(priorityFilter == 0)
            filterActivitiesByCompletion(activity);
        else if(activity.getPriority() == priorityFilter)
            filterActivitiesByCompletion(activity);
    }

    public void filterActivitiesByCompletion(Activity activity) {
        if(showCompleted && showNotCompleted)
            addDynamicPane(activity);
        else if(showCompleted && !showNotCompleted) {
            if(activity.isCompleted())
                addDynamicPane(activity);
        } else if(!showCompleted && showNotCompleted) {
            if(!activity.isCompleted())
                addDynamicPane(activity);
        }
    }

    public void update() {
        userLabel.setText("Korisnik: " + user.getUsername());

        activitiesPane.getChildren().clear();
        activityService.fetchActivityData();

        addFetchedActivities();
    }

    public Label getPriority(int priority) {
        Label priorityLabel = new Label();
        if(priority == 1) {
            priorityLabel.setText("Nizak prioritet");
            priorityLabel.getStyleClass().add("low-priority");
        }
        else if(priority == 2) {
            priorityLabel.setText("Srednji prioritet");
            priorityLabel.getStyleClass().add("medium-priority");
        }
        else {
            priorityLabel.setText("Visok prioritet");
            priorityLabel.getStyleClass().add("high-priority");
        }

        return priorityLabel;
    }

    @Override
    public void update(Activity activity) {

    }

    @Override
    public void update(String text) {

    }

    public void addFetchedActivities() {
        counter = 1;
        for(Activity activity : getActivityList()) {
            addDynamicPane(activity);
            counter++;
        }
    }

    public void clearPrioritySelections() {
        all.setStyle("-fx-border-color: none");
        low.setStyle("-fx-border-color: none");
        medium.setStyle("-fx-border-color: none");
        high.setStyle("-fx-border-color: none");
    }

    public void clearCompletionSelections() {
        allCompletion.setStyle("-fx-border-color: none");
        completedButton.setStyle("-fx-border-color: none");
        notCompletedButton.setStyle("-fx-border-color: none");
    }

    public void onAllStatus() {
        clearCompletionSelections();
        allCompletion.setStyle("-fx-border-color: aliceblue");

        showCompleted = showNotCompleted = true;
        showActivities();
    }

    public void onCompleted() {
        clearCompletionSelections();
        completedButton.setStyle("-fx-border-color: aliceblue");

        showCompleted = true;
        showNotCompleted = false;
        showActivities();
    }

    public void onNotCompleted() {
        clearCompletionSelections();
        notCompletedButton.setStyle("-fx-border-color: aliceblue");

        showCompleted = false;
        showNotCompleted = true;
        showActivities();
    }

}
