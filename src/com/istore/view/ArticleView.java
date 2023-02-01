package com.istore.view;

import com.istore.CreateModel;
import com.istore.controller.ArticleController;
import com.istore.observer.Observer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleView implements Observer {
    private JPanel mainPanel;
    private JTextField articleField;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton createButton;
    private JTextField priceField;
    private JTable table;
    private final ArticleController controller;

    public ArticleView(ArticleController articleController) throws SQLException {
        this.controller = articleController;
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
                String name = this.articleField.getText();
                int price = Integer.parseInt(this.priceField.getText());
                try {
                    controller.update(id, name, price);
                    this.articleField.setText("");
                    this.priceField.setText("");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        createButton.addActionListener(e -> {
            String name = this.articleField.getText();
            int price = Integer.parseInt(this.priceField.getText());
            try {
                controller.insert(name, price);
                this.articleField.setText("");
                this.priceField.setText("");
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
