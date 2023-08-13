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
    public ActivityBuilder setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public ActivityBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public ActivityBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public ActivityBuilder setPriority(int priority) {
        this.priority = priority;
        return this;
    }

    @Override
    public ActivityBuilder setCompleted(boolean completed) {
        this.completed = completed;
        return this;
    }

    @Override
    public ActivityBuilder setStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    @Override
    public ActivityBuilder setEndTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }


    public Activity build() {
        return new Activity(id, name, description, priority, completed, startTime, endTime);
    }
}
