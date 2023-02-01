package com.istore.view;

import com.istore.controller.RoleController;
import com.istore.observer.Observer;

import javax.swing.*;
import java.sql.ResultSet;

public class RoleView implements Observer {
    private JPanel mainPanel;
    private JTextField roleField;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton createButton;
    private JScrollPane scrollPane;
    private JTable table;

    public RoleView(RoleController roleController) {

    }
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void update(ResultSet resultSet) {

    }
}
