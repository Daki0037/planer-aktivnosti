package me.danilo.planeraktivnosti.models.builders;

import me.danilo.planeraktivnosti.interfaces.Builder;
import me.danilo.planeraktivnosti.models.Activity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ActivityBuilder implements Builder {

    private String name = "", description = "";
    private int id, priority = 2;
    private boolean completed = false;
    private LocalDate currentDate = LocalDate.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private String startDate = LocalDate.now().format(formatter);
    private String endDate = LocalDate.now().format(formatter);

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
    public ActivityBuilder setStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    @Override
    public ActivityBuilder setEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public Activity build() {
        return new Activity(id, name, description, priority, completed, startDate, endDate);
    }
}
