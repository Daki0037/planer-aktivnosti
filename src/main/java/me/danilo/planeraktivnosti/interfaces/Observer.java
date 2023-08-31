package me.danilo.planeraktivnosti.interfaces;

import me.danilo.planeraktivnosti.models.Activity;

public interface Observer {

    public void update();
    public void update(Activity activity);

    public void update(String text);

}
