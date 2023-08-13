package me.danilo.planeraktivnosti.interfaces;

import java.util.Date;

public interface Builder {

    void setId(int id);
    void setName(String name);
    void setDescription(String description);
    void setPriority(int priority);
    void setCompleted(boolean completed);
    void setStartTime(Date startTime);
    void setEndTime(Date endTime);


}
