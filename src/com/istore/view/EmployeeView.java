package com.istore.view;

import com.istore.controller.UserController;
import com.istore.observer.Observer;

import javax.swing.*;
import java.sql.ResultSet;

public class EmployeeView implements Observer {
    private JPanel mainPanel;
    private JTextField emailField;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton createButton;
    private JTextField pseudoField;
    private JTextField passwordField;
    private JScrollPane scrollPane;
    private JTable table;
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
