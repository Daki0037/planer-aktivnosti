package me.danilo.planeraktivnosti.models;

public class Activity {

    private String name, description;
    private int id, priority;
    private boolean completed;

    public Activity(int id, String name, String description, int priority, boolean completed) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.completed = completed;
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

}
