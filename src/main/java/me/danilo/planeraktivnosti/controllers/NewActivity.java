package me.danilo.planeraktivnosti.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import me.danilo.planeraktivnosti.interfaces.Observer;
import me.danilo.planeraktivnosti.models.Activity;
import me.danilo.planeraktivnosti.models.builders.ActivityBuilder;
import me.danilo.planeraktivnosti.models.observers.EditObserver;
import me.danilo.planeraktivnosti.models.observers.FetchObserver;
import me.danilo.planeraktivnosti.utils.ActivityService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class NewActivity implements Observer {

    private ScreenController screenController = ScreenController.getInstance();

    @FXML
    private TextField title;
    @FXML
    private TextArea description;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private CheckBox completed;
    @FXML
    private RadioButton low;
    @FXML
    private RadioButton medium;
    @FXML
    private RadioButton high;
    @FXML
    private Label errorLabel;

    private String activityName, activityDescription, error;
    private int priority;
    private LocalDate start, end;
    private boolean activityCompletion;

    private EditObserver editObserver = EditObserver.getInstance();

    private ActivityBuilder builder = new ActivityBuilder();
    private ActivityService activityService = new ActivityService();
    private FetchObserver observer = FetchObserver.getInstance();

    @FXML
    public void initialize() {
        startDate.setValue(LocalDate.now());
        endDate.setValue(LocalDate.now());
        editObserver.addListener(this);
    }

    public void onAddActivity() throws IOException {
        if(!isValidActivity()) {
            errorLabel.setText(error);
            return;
        }
        getValues();
        observer.update();
        screenController.changeScreen("main");
    }

    public void onReturnBtn() {
        screenController.changeScreen("main");
    }

    public void getValues() throws IOException {
        activityName = title.getText();
        activityDescription = description.getText();
        activityCompletion = completed.isSelected();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        start = startDate.getValue();
        end = endDate.getValue();
        String startDateText = start.format(formatter);
        String endDateText = end.format(formatter);
        priority = getPriority();

        Activity activity = builder
                .setName(activityName)
                .setDescription(activityDescription)
                .setPriority(priority)
                .setStartDate(startDateText)
                .setEndDate(endDateText)
                .setCompleted(activityCompletion)
                .build();

        activityService.addActivity(activity);
    }

    public int getPriority() {
        boolean lowPriority = low.isSelected();
        boolean mediumPriority = medium.isSelected();
        boolean highPriority = high.isSelected();

        if(lowPriority)
            return 1;
        else if(mediumPriority)
            return 2;
        else
            return 3;
    }

    public boolean isValidActivity() {
        if(title.getText().isBlank()) {
            error = "Aktivnost mora imati naziv!";
            return false;
        } else if(title.getText().length() > 150) {
            error = "Naslov aktivnosti ne sme biti duži od 150 karaktera!";
            return false;
        } else if(description.getText().length() > 500) {
            error = "Deskripcija ne sme biti duža od 500 karaktera!";
            return false;
        } else if(startDate.getValue() == null || endDate.getValue() == null) {
            error = "Početni i krajnji datum moraju imati vrednosti!";
            return false;
        } else if(endDate.getValue().isBefore(startDate.getValue())) {
            error = "Krajnji datum ne može biti pre početnog datuma!";
            return false;
        }
        return true;
    }

    @Override
    public void update() {
        error = "";
        errorLabel.setText("");
    }

    @Override
    public void update(Activity activity) {

    }

    @Override
    public void update(String text) {

    }
}
