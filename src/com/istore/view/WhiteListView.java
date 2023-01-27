package com.istore.view;

import com.istore.controller.AbstractController;
import com.istore.controller.WhitelistController;
import com.istore.observer.Observer;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class WhiteListView implements Observer {
    private JTable whitelistTable;
    private JButton button1;
    private JPanel panel1;
    private JButton button2;
    private JButton button3;
    private JPanel whitelistPanel;
    private final WhitelistController controller;

    public WhiteListView(WhitelistController controller) {
        this.controller = controller;
        button1.addActionListener(e ->  {
            this.controller.add();
        });
    }

    public JPanel getWhitelistPanel() {
        return whitelistPanel;
    }

    public void update(ResultSet resultSet) {

    }
}
