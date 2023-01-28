package com.istore.view;

import javax.swing.*;

public class AppView extends JFrame {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;

    public AppView(EmployeeView employeeView, InventoryView inventoryView) {
        tabbedPane.add("Inventory", inventoryView.getMainPanel());
        tabbedPane.add("Employee List", employeeView.getMainPanel());
        settings();
    }

    public AppView(WhiteListView whiteListView, ArticleView articleView, RoleView roleView, StoreView storeView, UserView userView) {
        tabbedPane.add("White List", whiteListView.getMainPanel());
        tabbedPane.add("User List", userView.getMainPanel());
        tabbedPane.add("Store List", storeView.getMainPanel());
        tabbedPane.add("Article List", articleView.getMainPanel());
        tabbedPane.add("Role List", roleView.getMainPanel());
        settings();
    }

    private void settings() {
        setTitle("iStore");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(350, 200);
        setContentPane(mainPanel);
        setSize(1280, 720);
        setVisible(true);
    }
}
