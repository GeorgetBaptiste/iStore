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

    public int getStoreId(int user_id) throws SQLException {
        String sql = "SELECT store_id FROM user WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, user_id);
        ResultSet result = statement.executeQuery();
        result.next();
        return result.getInt("store_id");
    }

    public ResultSet select(int store_id) throws SQLException {
        String sql = "SELECT article.name, store_article.stock FROM store_article LEFT JOIN article ON article.id = store_article.article_id WHERE store_id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, store_id);
        return statement.executeQuery();
    }

    public ResultSet selectById(int store_id, int article_id) throws SQLException {
        String sql = "SELECT * FROM store_article WHERE store_id=? AND article_id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, store_id);
        statement.setInt(2, article_id);
        return statement.executeQuery();
    }

    public void delete(int store_id, String article) throws SQLException {
        String sqlArticle = "SELECT id FROM article WHERE name=?";
        PreparedStatement statementArticle = conn.prepareStatement(sqlArticle);
        statementArticle.setString(1, article);
        ResultSet result = statementArticle.executeQuery();
        result.next();
        int article_id = result.getInt("id");
        String sql = "DELETE FROM store_article WHERE store_id=? AND article_id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, store_id);
        statement.setInt(2, article_id);
        statement.executeUpdate();
        notifyObserver(select(store_id));
    }

    public void update(int store_id, String article, int stock) throws SQLException {
        String sqlArticle = "SELECT id FROM article WHERE name=?";
        PreparedStatement statementArticle = conn.prepareStatement(sqlArticle);
        statementArticle.setString(1, article);
        ResultSet result = statementArticle.executeQuery();
        result.next();
        int article_id = result.getInt("id");
        String sql = "UPDATE store_article SET article_id=?, stock=? WHERE store_id=? AND article_id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, article_id);
        statement.setInt(2, stock);
        statement.setInt(3, store_id);
        statement.setInt(4, article_id);
        statement.executeUpdate();
        notifyObserver(select(store_id));
    }

    public void insert(int store_id, String article, int stock) throws SQLException {
        String sqlArticle = "SELECT id FROM article WHERE name = ?";
        PreparedStatement statementArticle = conn.prepareStatement(sqlArticle);
        statementArticle.setString(1, article);
        ResultSet result = statementArticle.executeQuery();
        if (result.next()) {
            int article_id = result.getInt("id");
            ResultSet resultArticle = selectById(store_id, article_id);
            if (!resultArticle.next()) {
                String sql = "INSERT INTO store_article (store_id, article_id, stock) VALUES (?,?,?)";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, store_id);
                statement.setInt(2, article_id);
                statement.setInt(3, stock);
                statement.executeUpdate();
                notifyObserver(select(store_id));
            }
        }
    }
}
