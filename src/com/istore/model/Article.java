package com.istore.model;

import java.sql.*;

public class Article extends AbstractModel {
    private final Connection conn;

    public Article(Connection conn) {
        this.conn = conn;
    }

    public ResultSet select() throws SQLException {
        String sql = "SELECT * FROM article";
        Statement statement = conn.createStatement();
        return statement.executeQuery(sql);
    }

    public void delete(int article_id) throws SQLException {
        String sql = "DELETE FROM article WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, article_id);
        statement.executeUpdate();
        notifyObserver(select());
    }

    public void update(int article_id, String name, int price) throws SQLException {
        String sql = "UPDATE article SET name=?, price=? WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, name);
        statement.setInt(2, price);
        statement.setInt(3, article_id);
        statement.executeUpdate();
        notifyObserver(select());
    }

    public void insert(String name, int price) throws SQLException {
        String sql = "INSERT INTO article (name, price) VALUES (?,?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, name);
        statement.setInt(2, price);
        statement.executeUpdate();
        notifyObserver(select());
    }
}
