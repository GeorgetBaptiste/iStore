package com.istore.controller;

import com.istore.model.Article;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleController {
    private final Article model;

    public ArticleController(Article model) {
        this.model = model;
    }

    public ResultSet select() throws SQLException {
        return model.select();
    }

    public void delete(int id) throws SQLException {
        model.delete(id);
    }

    public void insert(String name, int price) throws SQLException {
        model.insert(name, price);
    }

    public void update(int id, String name, int price) throws SQLException {
        model.update(id, name, price);
    }
}
