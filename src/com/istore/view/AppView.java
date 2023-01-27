package com.istore.view;

import javax.swing.*;

public class AppView extends JFrame {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;

    public AppView(WhiteListView whiteListView) {
        setTitle("iStore");
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);
        tabbedPane.add("White List", whiteListView.getWhitelistPanel());
        pack();
        setVisible(true);
    }
}
