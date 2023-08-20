package me.danilo.planeraktivnosti.interfaces;

import me.danilo.planeraktivnosti.models.builders.ActivityBuilder;

import java.time.LocalDate;
import java.util.Date;

public interface Builder {

    ActivityBuilder setId(int id);
    ActivityBuilder setName(String name);
    ActivityBuilder setDescription(String description);
    ActivityBuilder setPriority(int priority);
    ActivityBuilder setCompleted(boolean completed);
    ActivityBuilder setStartDate(String startDate);
    ActivityBuilder setEndDate(String endDate);


}
