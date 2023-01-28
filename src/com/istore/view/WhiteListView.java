package com.istore.view;

import com.istore.controller.WhitelistController;
import com.istore.observer.Observer;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WhiteListView implements Observer {
    private JTable whitelistTable;
    private JButton button1;
    private JPanel mainPanel;
    private JButton button2;
    private JButton button3;
    private JPanel whitelistPanel;
    private final WhitelistController controller;

    public WhiteListView(WhitelistController controller) {
        this.controller = controller;
        button1.addActionListener(e ->  {

        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void update(ResultSet resultSet) {

    }
}
