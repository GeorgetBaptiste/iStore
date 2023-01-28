package com.istore.controller;

import com.istore.model.WhiteList;

import java.sql.SQLException;

public class WhitelistController {

    private final WhiteList model;

    public WhitelistController(WhiteList model) {
        this.model = model;
    }

    public void add() throws SQLException {
        model.insert("test@test.com");
    }
}
