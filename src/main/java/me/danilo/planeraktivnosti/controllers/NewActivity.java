package me.danilo.planeraktivnosti.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import me.danilo.planeraktivnosti.models.Activity;
import me.danilo.planeraktivnosti.models.builders.ActivityBuilder;

public class NewActivity {

    private ScreenController screenController = ScreenController.getInstance();

    @FXML
    private TextField title;
    @FXML
    private TextArea description;
    @FXML
    private DatePicker startTime;
    @FXML
    private DatePicker endTime;
    @FXML
    private CheckBox completed;

    private ActivityBuilder builder = new ActivityBuilder();




    public void onAddActivity() {
        String activityTitle = title.getText();
        String activityDescription = description.getText();
        boolean activityCompletion = completed.isSelected();
        System.out.println(activityTitle + " " + activityDescription + " " + activityCompletion);
        Activity activity = builder.setName(activityTitle)
                .setDescription(activityDescription)
                        .setCompleted(activityCompletion).build();

        screenController.changeScreen("main");
    }

    public void onReturnBtn() {
        screenController.changeScreen("main");
    }


}
