package com.istore.view;

import com.istore.controller.UserController;
import com.istore.observer.Observer;

import javax.swing.*;
import java.sql.ResultSet;

public class EmployeeView implements Observer {
    private JPanel mainPanel;
    private int userId;

    public EmployeeView(int userId, UserController userController) {
        this.userId = userId;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void update(ResultSet resultSet) {

    }
}
