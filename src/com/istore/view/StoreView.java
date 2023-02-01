package com.istore.view;

import com.istore.controller.StoreController;
import com.istore.observer.Observer;

import javax.swing.*;
import java.sql.ResultSet;

public class StoreView implements Observer {
    private JPanel mainPanel;
    private JTextField storeField;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton createButton;
    private JScrollPane scrollPane;
    private JTable table;

    public StoreView(StoreController storeController) {

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void update(ResultSet resultSet) {

    }
}
