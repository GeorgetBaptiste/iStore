package com.istore.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Inventory extends AbstractModel {
    private final Connection conn;

    public Inventory(Connection conn) {
        this.conn = conn;
    }

    public ResultSet select(int store_id) throws SQLException {
        String sql = "SELECT * FROM store_article WHERE store_id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, store_id);
        return statement.executeQuery();
    }

    public void delete(int store_id, int article_id) throws SQLException {
        String sql = "DELETE FROM store_article WHERE store_id=? AND article_id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, store_id);
        statement.setInt(2, article_id);
        statement.executeUpdate();
        notifyObserver(select(store_id));
    }

    public void update(int store_id, int article_id, int stock) throws SQLException {
        String sql = "UPDATE store_article SET article_id=?, stock=? WHERE store_id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, article_id);
        statement.setInt(2, stock);
        statement.setInt(3, store_id);
        statement.executeUpdate();
        notifyObserver(select(store_id));
    }

    public void insert(int store_id, int article_id, int stock) throws SQLException {
        String sql = "INSERT INTO store_article (store_id, article_id, stock) VALUES (?,?,?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, store_id);
        statement.setInt(2, article_id);
        statement.setInt(3, stock);
        statement.executeUpdate();
        notifyObserver(select(store_id));
    }
}
