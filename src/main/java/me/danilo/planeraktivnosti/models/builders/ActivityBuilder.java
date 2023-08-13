package me.danilo.planeraktivnosti.models.builders;

import me.danilo.planeraktivnosti.interfaces.Builder;
import me.danilo.planeraktivnosti.models.Activity;

import java.util.Date;

public class ActivityBuilder implements Builder {

    private String name, description;
    private int id, priority;
    private boolean completed;
    private Date startTime, endTime;

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Override
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


    public Activity build() {
        return new Activity(id, name, description, priority, completed, startTime, endTime);
    }
}
