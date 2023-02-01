package com.istore.view;

import com.istore.controller.InventoryController;
import com.istore.observer.Observer;

import javax.swing.*;
import java.sql.ResultSet;

public class InventoryView implements Observer {
    private JPanel mainPanel;
    private JTextField articleField;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton createButton;
    private JScrollPane scrollPane;
    private JTable table;
    private JTextField stockField;
    private int userId;

    public InventoryView(int userId, InventoryController inventoryController) {
        this.userId = userId;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void update(ResultSet resultSet) {

    }
}
