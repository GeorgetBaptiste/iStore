package com.istore.observer;

import java.sql.ResultSet;

public interface Observer {
    public void update(ResultSet resultSet);
}