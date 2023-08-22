package me.danilo.planeraktivnosti.models.observers;

import me.danilo.planeraktivnosti.interfaces.Observer;
import me.danilo.planeraktivnosti.models.User;

import java.util.ArrayList;
import java.util.List;

public class FetchObserver {
    private User user;

    private List<Observer> listeners = new ArrayList<>();

    private static FetchObserver instance;

    private FetchObserver() {}

    public static FetchObserver getInstance() {
        if(instance == null)
            instance =new FetchObserver();
        return instance;
    }

    public void addListener(Observer listener) {
        listeners.add(listener);
    }

    public void update() {
        for(Observer listener : listeners) {
            listener.update();
        }
    }
}
