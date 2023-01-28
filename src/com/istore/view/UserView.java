package com.istore.view;

import com.istore.controller.UserController;
import com.istore.observer.Observer;

import javax.swing.*;
import java.sql.ResultSet;

public class UserView implements Observer {
    private JPanel mainPanel;

    public UserView(UserController userController) {

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void update(ResultSet resultSet) {

    }
}
