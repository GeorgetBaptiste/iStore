package com.istore.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.istore.DatabaseConnection;
import com.istore.observer.Observable;
import com.istore.observer.Observer;

public abstract class AbstractModel implements Observable {
    private ArrayList<Observer> listObserver = new ArrayList<>();

    public void addObserver(Observer obs) {
        this.listObserver.add(obs);
    }

    public void notifyObserver(ResultSet resultSet) {
        for (Observer obs : listObserver) {
            obs.update(resultSet);
        }
    }

    public void removeObserver() {
        listObserver = new ArrayList<>();
    }
}
