package com.istore;

import com.istore.controller.UserController;
import com.istore.controller.WhitelistController;
import com.istore.model.User;
import com.istore.model.WhiteList;
import com.istore.view.AuthenticationView;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        Connection conn = new DatabaseConnection().getConnection();
        if (conn != null) {
            User user = new User(conn);
            WhiteList whiteList = new WhiteList(conn);
            UserController userController = new UserController(user);
            WhitelistController whitelistController = new WhitelistController(whiteList);
            new AuthenticationView(userController, whitelistController, user, whiteList, conn);
        }
    }
}
