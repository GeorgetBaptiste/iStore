package com.istore.view;

import com.istore.CreateModel;
import com.istore.controller.UserController;
import com.istore.observer.Observer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeView implements Observer {
    private JPanel mainPanel;
    private JTextField emailField;
    private JButton deleteButton;
    private JButton updateButton;
    private JTextField pseudoField;
    private JPasswordField passwordField;
    private JTable table;
    private final int userId;
    private final UserController controller;

    public EmployeeView(int userId, UserController userController) throws SQLException {
        this.userId = userId;
        this.controller = userController;
        getData();
        centerTable();
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            try {
                if (row >= 0) {
                    int id = (int) table.getValueAt(row, 0);
                    if (userId == id) {
                        controller.deleteEmployee(id);
                    }
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        updateButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            try {
                if (row >= 0) {
                    int id = (int) table.getValueAt(row, 0);
                    String pseudo = this.pseudoField.getText();
                    String email = this.emailField.getText();
                    char[] password = this.passwordField.getPassword();
                    if (userId == id) {
                        controller.updateEmployee(id, pseudo, email, password);
                        this.pseudoField.setText("");
                        this.emailField.setText("");
                        this.passwordField.setText("");
                    }
                }
            } catch (NoSuchAlgorithmException | SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void getData() throws SQLException {
        ResultSet result = controller.selectEmployee(userId);
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
