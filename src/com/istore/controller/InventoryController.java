package com.istore.controller;

import com.istore.model.Inventory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryController {
    private final Inventory model;
    public InventoryController(Inventory model) {
        this.model = model;
    }

    public ResultSet select(int id) throws SQLException {
        int store_id = model.getStoreId(id);
        return model.select(store_id);
    }

    public void delete(int user_id, String article) throws SQLException {
        int store_id = model.getStoreId(user_id);
        model.delete(store_id, article);
    }

    public void insert(int user_id, String article, int stock) throws SQLException {
        int store_id = model.getStoreId(user_id);
        model.insert(store_id, article, stock);
    }

    public void update(int user_id, String article, int stock) throws SQLException {
        int store_id = model.getStoreId(user_id);
        model.update(store_id, article, stock);
    }
}
