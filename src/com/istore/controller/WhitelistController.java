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
}
