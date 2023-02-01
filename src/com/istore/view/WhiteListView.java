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
    private final WhitelistController controller;

    public WhiteListView(WhitelistController controller) throws SQLException {
        this.controller = controller;
        getData();
        centerTable();
        deleteButton.addActionListener(e ->  {
            int row = table.getSelectedRow();
            try {
                if (row >= 0) {
                    int id = (Integer) table.getValueAt(row, 0);
                    controller.delete(id);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        updateButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            String email = this.emailField.getText();
            try {
                if (row >= 0) {
                    int id = (Integer) table.getValueAt(row, 0);
                    controller.update(id, email);
                    this.emailField.setText("");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        createButton.addActionListener(e -> {
            try {
                controller.insert(this.emailField.getText());
                this.emailField.setText("");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void centerTable() {
        DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
        custom.setHorizontalAlignment(JLabel.CENTER);
        for (int i=0 ; i < table.getColumnCount() ; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(custom);
        }
    }

    private void getData() throws SQLException {
        ResultSet result = controller.select();
        table.setModel(new CreateModel(result).getModel());
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void update(ResultSet resultSet) throws SQLException {
        table.setModel(new CreateModel(resultSet).getModel());
        centerTable();
    }
}
