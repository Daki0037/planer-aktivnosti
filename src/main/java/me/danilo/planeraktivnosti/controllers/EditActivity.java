package me.danilo.planeraktivnosti.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import me.danilo.planeraktivnosti.interfaces.Observer;
import me.danilo.planeraktivnosti.models.Activity;
import me.danilo.planeraktivnosti.models.observers.EditObserver;
import me.danilo.planeraktivnosti.models.observers.FetchObserver;
import me.danilo.planeraktivnosti.utils.ActivityService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditActivity implements Observer {

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

    private ScreenController screenController = ScreenController.getInstance();
    private Activity activity;
    private ActivityService activityService = new ActivityService();
    private FetchObserver observer = FetchObserver.getInstance();
    private String error = "";

    @FXML
    public void initialize() {
        EditObserver editObserver = EditObserver.getInstance();
        editObserver.addListener(this);
    }

    public void onReturnButton() {
        screenController.changeScreen("main");
    }

    public void onSaveButton() throws IOException {

        if(!isValidActivity()) {
            System.out.println("Nije validna");
            return;
        }

        activity.setName(title.getText());
        activity.setDescription(description.getText());
        activity.setCompleted(completed.isSelected());
        activity.setPriority(getPriority());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate start = startDate.getValue();
        LocalDate end = endDate.getValue();
        String startDateText = start.format(formatter);
        String endDateText = end.format(formatter);

        activity.setStartDate(startDateText);
        activity.setEndDate(endDateText);

        activityService.saveActivity(activity);
        observer.update();
        screenController.changeScreen("main");
    }

    @Override
    public void update() {

    }

    @Override
    public void update(Activity activity) {
        this.activity = activity;
        title.setText(activity.getName());
        description.setText(activity.getDescription());
        completed.setSelected(activity.isCompleted());
        selectPriority(activity);

        String start = activityService.convertDate(activity.getStartDate());
        String end = activityService.convertDate(activity.getEndDate());
        LocalDate startLocalDate = LocalDate.parse(start);
        LocalDate endLocalDate = LocalDate.parse(end);

        startDate.setValue(startLocalDate);
        endDate.setValue(endLocalDate);
    }

    public void selectPriority(Activity activity) {
        unselectPriorities();
        if(activity.getPriority() == 1)
            low.setSelected(true);
        else if(activity.getPriority() == 2)
            medium.setSelected(true);
        else
            high.setSelected(true);
    }

    public void unselectPriorities() {
        low.setSelected(false);
        medium.setSelected(false);
        high.setSelected(false);
    }

    public int getPriority() {
        if(low.isSelected())
            return 1;
        else if(medium.isSelected())
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
        }
        return true;
    }


}
