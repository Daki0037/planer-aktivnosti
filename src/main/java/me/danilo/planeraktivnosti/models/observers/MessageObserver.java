package me.danilo.planeraktivnosti.models.observers;

import me.danilo.planeraktivnosti.interfaces.Observer;

import java.util.ArrayList;
import java.util.List;

public class MessageObserver {

    private List<Observer> listeners = new ArrayList<>();

    private static MessageObserver instance;

    private MessageObserver() {}

    public static MessageObserver getInstance() {
        if(instance == null)
            instance = new MessageObserver();
        return instance;
    }

    public void addListener(Observer listener) {
        listeners.add(listener);
    }

    public void update(String text) {
        for(Observer listener : listeners) {
            listener.update(text);
        }
    }

}
