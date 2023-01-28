package com.istore.model;

import com.istore.controller.WhitelistController;

import java.sql.*;

public class WhiteList extends AbstractModel {
    private final Connection conn;

    public WhiteList(Connection conn) {
        this.conn = conn;
    }

    public ResultSet select() throws SQLException {
        String sql = "SELECT * FROM white_list";
        Statement statement = conn.createStatement();
        return statement.executeQuery(sql);
    }

    public ResultSet selectByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM white_list WHERE email=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, email);
        return statement.executeQuery();
    }

    public void delete(int white_list_id) throws SQLException {
        String sql = "DELETE FROM white_list WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, white_list_id);
        statement.executeUpdate();
        notifyObserver(select());
    }

    public void update(int white_list_id, String email) throws SQLException {
        String sql = "UPDATE white_list SET email=? WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, email);
        statement.setInt(2, white_list_id);
        statement.executeUpdate();
        notifyObserver(select());
    }

    public void insert(String email) throws SQLException {
        String sql = "INSERT INTO white_list (email) VALUES (?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, email);
        statement.executeUpdate();
        notifyObserver(select());
    }
}
