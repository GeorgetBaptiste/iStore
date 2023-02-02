package com.istore.view;

import com.istore.CreateModel;
import com.istore.controller.InventoryController;
import com.istore.observer.Observer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryView implements Observer {
    private JPanel mainPanel;
    private JTextField articleField;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton createButton;
    private JTable table;
    private JTextField stockField;
    private final int userId;
    private final InventoryController controller;

    public InventoryView(int userId, InventoryController inventoryController) throws SQLException {
        this.userId = userId;
        this.controller = inventoryController;
        getData();
        centerTable();
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String article = (String) table.getValueAt(row, 0);
                try {
                    controller.delete(userId, article);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        updateButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int stock = Integer.parseInt(this.stockField.getText());
                String article = (String) table.getValueAt(row, 0);
                try {
                    controller.update(userId, article, stock);
                    this.stockField.setText("");
                    this.articleField.setText("");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        createButton.addActionListener(e -> {
            String article = this.articleField.getText();
            int stock = Integer.parseInt(this.stockField.getText());
            try {
                controller.insert(userId, article, stock);
                this.articleField.setText("");
                this.stockField.setText("");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void getData() throws SQLException {
        ResultSet result = controller.select(userId);
        table.setModel(new CreateModel(result).getModel());
    }

    private void centerTable() {
        DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
        custom.setHorizontalAlignment(JLabel.CENTER);
        for (int i=0 ; i < table.getColumnCount() ; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(custom);
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void update(ResultSet resultSet) throws SQLException {
        table.setModel(new CreateModel(resultSet).getModel());
        centerTable();
    }
}
