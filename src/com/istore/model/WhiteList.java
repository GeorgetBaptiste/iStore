package com.istore.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WhiteList extends AbstractModel {

    public ResultSet select() throws SQLException {
        String sql = "SELECT * FROM white_list";
        Statement statement = conn.createStatement();
        return statement.executeQuery(sql);
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
