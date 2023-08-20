package me.danilo.planeraktivnosti.models.observers;

import me.danilo.planeraktivnosti.interfaces.Observer;
import me.danilo.planeraktivnosti.models.User;

import java.util.ArrayList;
import java.util.List;

public class UsernameObserver {
    private User user;

    private List<Observer> listeners = new ArrayList<>();

    private static UsernameObserver instance;

    private UsernameObserver() {}

    public static UsernameObserver getInstance() {
        if(instance == null)
            instance =new UsernameObserver();
        return instance;
    }

    public void addListener(Observer listener) {
        listeners.add(listener);
    }

    public void updateUsernameLabel() {
        for(Observer listener : listeners) {
            listener.update();
        }
    }
}
