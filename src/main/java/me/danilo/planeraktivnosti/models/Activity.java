package me.danilo.planeraktivnosti.models;

import java.util.Date;

public class Activity {

    private String name, description;
    private int id, priority;
    private boolean completed;

    private Date startTime, endTime;

    public Activity(int id, String name, String description, int priority, boolean completed, Date startTime, Date endTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.completed = completed;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }
}
