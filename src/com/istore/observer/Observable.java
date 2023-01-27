package com.istore.observer;

import java.sql.ResultSet;

public interface Observable {
    public void addObserver(Observer obs);
    public void removeObserver();
    public void notifyObserver(ResultSet resultSet);
}
