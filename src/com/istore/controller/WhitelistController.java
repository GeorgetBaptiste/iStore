package com.istore.controller;

import com.istore.model.WhiteList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WhitelistController {
    private final WhiteList model;

    public WhitelistController(WhiteList model) {
        this.model = model;
    }

    public boolean checkRegistration(String email) throws SQLException {
        ResultSet result = model.selectByEmail(email);
        return result.next();
    }

    public ResultSet select() throws SQLException {
        return model.select();
    }

    public void insert(String email) throws SQLException {
        model.insert(email);
    }

    public void delete(int id) throws SQLException {
        model.delete(id);
    }

    public void update(int id, String email) throws SQLException {
        model.update(id, email);
    }
}
