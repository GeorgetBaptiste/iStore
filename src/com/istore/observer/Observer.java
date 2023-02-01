package com.istore.observer;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Observer {
    void update(ResultSet resultSet) throws SQLException;
}