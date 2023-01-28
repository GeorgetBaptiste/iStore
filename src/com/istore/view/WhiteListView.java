package com.istore.view;

import com.istore.CreateModel;
import com.istore.controller.WhitelistController;
import com.istore.observer.Observer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WhiteListView implements Observer {
    private JButton deleteButton;
    private JPanel mainPanel;
    private JTextField emailField;
    private JButton updateButton;
    private JButton createButton;
    private JTable table;
    private JScrollPane scrollPane;
    private final WhitelistController controller;

    public WhiteListView(WhitelistController controller) throws SQLException {
        this.controller = controller;
        getData();
        centerTable();
        deleteButton.addActionListener(e ->  {

        });
        updateButton.addActionListener(e -> {

        });
        createButton.addActionListener(e -> {

        });
    }

    private void centerTable() {
        DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
        custom.setHorizontalAlignment(JLabel.CENTER); // centre les données de ton tableau
        for (int i=0 ; i < table.getColumnCount() ; i++) // centre chaque cellule de ton tableau
            table.getColumnModel().getColumn(i).setCellRenderer(custom);
    }

    private void getData() throws SQLException {
        ResultSet result = controller.select();
        table.setModel(new CreateModel(result).getModel());
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void update(ResultSet resultSet) {

    }
}
