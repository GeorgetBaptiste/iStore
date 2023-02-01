package com.istore.controller;

import com.istore.model.Store;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StoreController {
    private final Store model;

    public StoreController(Store model) {
        this.model = model;
    }

    public ResultSet select() throws SQLException {
        return model.select();
    }

    public void delete(int id) throws SQLException {
        model.delete(id);
    }

    public void insert(String name) throws SQLException {
        model.insert(name);
    }

    public void update(int id, String name) throws SQLException {
        model.update(id, name);
    }
}
