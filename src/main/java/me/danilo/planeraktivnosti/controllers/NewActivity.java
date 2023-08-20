package me.danilo.planeraktivnosti.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import me.danilo.planeraktivnosti.models.Activity;
import me.danilo.planeraktivnosti.models.builders.ActivityBuilder;
import me.danilo.planeraktivnosti.utils.ActivityService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class NewActivity extends ActivityList {

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

    private String activityName, activityDescription, error;
    private int priority;
    private LocalDate start, end;
    private boolean activityCompletion;

    private ActivityBuilder builder = new ActivityBuilder();
    private ActivityService activityService = new ActivityService();

    public void onAddActivity() throws IOException {
        getValues();
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
        if(activityName.isBlank()) {
            error = "Aktivnost mora imati naziv!";
            return false;
        } else if(activityName.length() > 150) {
            error = "Naslov aktivnosti ne sme biti duži od 150 karaktera!";
            return false;
        } else if(activityDescription.length() > 500) {
            error = "Deskripcija ne sme biti duža od 500 karaktera!";
            return false;
        } else if(startDate.getValue() == null || endDate.getValue() == null) {
            error = "Početni i krajnji datum moraju imati vrednosti!";
            return false;
        }
        return true;
    }
}
