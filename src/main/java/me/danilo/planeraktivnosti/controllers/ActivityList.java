package me.danilo.planeraktivnosti.controllers;

import me.danilo.planeraktivnosti.models.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityList {

    private static List<Activity> activityList = new ArrayList<>();

    public void addActivityToList(Activity activity) {
        activityList.add(activity);
    }

    public void clearActivityList() {
        activityList.clear();
    }

    public List<Activity> getActivityList() {
        return activityList;
    }
}
