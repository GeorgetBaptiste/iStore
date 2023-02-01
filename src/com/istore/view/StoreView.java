package com.istore.view;

import com.istore.CreateModel;
import com.istore.controller.StoreController;
import com.istore.observer.Observer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StoreView implements Observer {
    private JPanel mainPanel;
    private JTextField storeField;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton createButton;
    private JTable table;
    private final StoreController controller;

    public StoreView(StoreController storeController) throws SQLException {
        this.controller = storeController;
        getData();
        centerTable();
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int id = (int) table.getValueAt(row, 0);
                try {
                    controller.delete(id);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        updateButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int id = (int) table.getValueAt(row, 0);
                String name = this.storeField.getText();
                try {
                    controller.update(id, name);
                    this.storeField.setText("");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        createButton.addActionListener(e -> {
            String name = this.storeField.getText();
            try {
                controller.insert(name);
                this.storeField.setText("");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void getData() throws SQLException {
        ResultSet result = controller.select();
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
