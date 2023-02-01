package com.istore.observer;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Observable {
    void addObserver(Observer obs);
    void notifyObserver(ResultSet resultSet) throws SQLException;
}
