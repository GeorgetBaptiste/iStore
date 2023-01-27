package com.istore.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Role extends AbstractModel {

    public ResultSet select() throws SQLException {
        String sql = "SELECT * FROM role";
        Statement statement = conn.createStatement();
        return statement.executeQuery(sql);
    }

    public void delete(int store_id) throws SQLException {
        String sql = "DELETE FROM role WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, store_id);
        statement.executeUpdate();
        notifyObserver(select());
    }

    public void update(int store_id, String name) throws SQLException {
        String sql = "UPDATE role SET name=? WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, name);
        statement.setInt(2, store_id);
        statement.executeUpdate();
        notifyObserver(select());
    }

    public void insert(String name) throws SQLException {
        String sql = "INSERT INTO role (name) VALUES (?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, name);
        statement.executeUpdate();
        notifyObserver(select());
    }
}
