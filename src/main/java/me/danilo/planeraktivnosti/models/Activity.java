package me.danilo.planeraktivnosti.models;

import java.util.Date;

public class Activity {

    private String name, description;
    private int id, priority;
    private boolean completed;

    private Date startDate, endDate;
    public Activity(int id, String name, String description, int priority, boolean completed, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.completed = completed;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
