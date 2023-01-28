package com.istore.controller;

import com.istore.HashPassword;
import com.istore.model.User;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class UserController {

    private final User model;

    public UserController(User model) {
        this.model = model;
    }

    public boolean checkRegistration(String email) throws SQLException {
        ResultSet result = model.selectByEmail(email);
        return !result.next();
    }

    public boolean checkConnection(String email, char[] password) throws SQLException, NoSuchAlgorithmException {
        ResultSet result = model.selectByEmail(email);
        String hashPassword = new HashPassword(password).getHashPassword();
        return result.next() && Objects.equals(result.getString("password"), hashPassword);
    }

    public void addRegistration(String email, String pseudo, char[] password) throws SQLException, NoSuchAlgorithmException {
        String hashPassword = new HashPassword(password).getHashPassword();
        model.insertByRegistration(email, pseudo, hashPassword, 2);
    }
}
