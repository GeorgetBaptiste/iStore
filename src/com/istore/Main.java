package com.istore;

import com.istore.controller.UserController;
import com.istore.controller.WhitelistController;
import com.istore.model.User;
import com.istore.model.WhiteList;
import com.istore.view.AuthenticationView;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        // Database connection
        Connection conn = new DatabaseConnection().getConnection();
        // Models
        User user = new User(conn);
        WhiteList whiteList = new WhiteList(conn);
        // Controllers
        UserController userController = new UserController(user);
        WhitelistController whitelistController = new WhitelistController(whiteList);
        // View
        new AuthenticationView(userController, whitelistController, user, whiteList, conn);
    }
}
