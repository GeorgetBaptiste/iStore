package com.istore.model;

import java.sql.*;

public class User extends AbstractModel {
    private final Connection conn;

    public User(Connection conn) {
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

    public ResultSet select() throws SQLException {
        String sql = "SELECT user.id, user.pseudo, user.email, role.name, store.name FROM user LEFT JOIN role ON role.id = user.role_id LEFT JOIN store ON store.id = user.store_id";
        Statement statement = conn.createStatement();
        return statement.executeQuery(sql);
    }

    public ResultSet selectEmployee(int store_id) throws SQLException {
        String sql = "SELECT user.id, user.pseudo, user.email, role.name FROM user LEFT JOIN role ON role.id = user.role_id WHERE store_id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, store_id);
        return statement.executeQuery();
    }

    public ResultSet selectByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM user WHERE email=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, email);
        return statement.executeQuery();
    }

    public ResultSet selectById(int user_id) throws SQLException {
        String sql = "SELECT * FROM user WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, user_id);
        return statement.executeQuery();
    }

    public void delete(int user_id) throws SQLException {
        String sql = "DELETE FROM user WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, user_id);
        statement.executeUpdate();
        notifyObserver(select());
    }

    public void deleteEmployee(int user_id, int store_id) throws SQLException {
        String sql = "DELETE FROM user WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, user_id);
        statement.executeUpdate();
        notifyObserver(selectEmployee(store_id));
    }

    public void update(int user_id, String email, String pseudo, String password, int role_id, String store) throws SQLException {
        int store_id = 0;
        String sqlStore = "SELECT id FROM store WHERE name = ?";
        PreparedStatement statementStore = conn.prepareStatement(sqlStore);
        statementStore.setString(1, store);
        ResultSet result = statementStore.executeQuery();
        if (result.next()) {
            store_id = result.getInt("id");
        }
        if (store_id != 0) {
            String sql = "UPDATE user SET email=?, pseudo=?, password=?, role_id=?, store_id=? WHERE id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, pseudo);
            statement.setString(3, password);
            statement.setInt(4, role_id);
            statement.setInt(5, store_id);
            statement.setInt(6, user_id);
            statement.executeUpdate();
            notifyObserver(select());
        } else {
            updateWithoutStore(user_id, email, pseudo, password, 2);
        }
    }

    public void updateEmployee(int user_id, String email, String pseudo, String password) throws SQLException {
        String sql = "UPDATE user SET email=?, pseudo=?, password=? WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, pseudo);
        statement.setString(3, password);
        statement.setInt(4, user_id);
        statement.executeUpdate();
        notifyObserver(selectEmployee(getStoreId(user_id)));
    }

    public void updateWithoutStore(int user_id, String email, String pseudo, String password, int role_id) throws SQLException {
        String sql = "UPDATE user SET email=?, pseudo=?, password=?, role_id=?, store_id=NULL WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, pseudo);
        statement.setString(3, password);
        statement.setInt(4, role_id);
        statement.setInt(5, user_id);
        statement.executeUpdate();
        notifyObserver(select());
    }

    public void insert(String email, String pseudo, String password, int role_id, String store) throws SQLException {
        int store_id = 0;
        String sqlStore = "SELECT id FROM store WHERE name = ?";
        PreparedStatement statementStore = conn.prepareStatement(sqlStore);
        statementStore.setString(1, store);
        ResultSet result = statementStore.executeQuery();
        if (result.next()) {
            store_id = result.getInt("id");
        }
        if (store_id != 0) {
            String sql = "INSERT INTO user (email, pseudo, password, role_id, store_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, pseudo);
            statement.setString(3, password);
            statement.setInt(4, role_id);
            statement.setInt(5, store_id);
            statement.executeUpdate();
            notifyObserver(select());
        } else {
            insertWithoutStore(email, pseudo, password, 2);
        }
    }

    public void insertWithoutStore(String email, String pseudo, String password, int role_id) throws SQLException {
        String sql = "INSERT INTO user (email, pseudo, password, role_id) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, pseudo);
        statement.setString(3, password);
        statement.setInt(4, role_id);
        statement.executeUpdate();
        notifyObserver(select());
    }
}
