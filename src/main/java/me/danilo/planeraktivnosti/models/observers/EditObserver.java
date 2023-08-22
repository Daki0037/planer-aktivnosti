package me.danilo.planeraktivnosti.models.observers;

import me.danilo.planeraktivnosti.interfaces.Observer;
import me.danilo.planeraktivnosti.models.Activity;

import java.util.ArrayList;
import java.util.List;

public class EditObserver {

    private List<Observer> listeners = new ArrayList<>();

    private static EditObserver instance;

    private EditObserver() {}

    public static EditObserver getInstance() {
        if(instance == null)
            instance = new EditObserver();
        return instance;
    }

    public void addListener(Observer listener) {
        listeners.add(listener);
    }

    public void update(Activity activity) {
        for(Observer listener : listeners) {
            listener.update(activity);
        }
    }
}
