package com.istore.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.istore.observer.Observable;
import com.istore.observer.Observer;

public abstract class AbstractModel implements Observable {
    private final ArrayList<Observer> listObserver = new ArrayList<>();

    public void addObserver(Observer obs) {
        this.listObserver.add(obs);
    }

    public void notifyObserver(ResultSet resultSet) throws SQLException {
        for (Observer obs : listObserver) {
            obs.update(resultSet);
        }
    }
}
