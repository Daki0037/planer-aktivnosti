package me.danilo.planeraktivnosti.models;

import java.time.LocalDate;
import java.util.Date;

public class Activity {

    private String name, description;
    private int id, priority;
    private boolean completed;
    private int UserId;
    private User user = User.getInstance();

    private String startDate, endDate;
    public Activity(int id, String name, String description, int priority, boolean completed, String startDate, String endDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.completed = completed;
        this.startDate = startDate;
        this.endDate = endDate;
        this.UserId = user.getId();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getUserId() {
        return UserId;
    }
}
