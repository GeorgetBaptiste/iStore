package com.istore.view;

import com.istore.CreateModel;
import com.istore.controller.UserController;
import com.istore.observer.Observer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class UserView implements Observer {
    private JPanel mainPanel;
    private JTextField emailField;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton createButton;
    private JTable table;
    private JTextField pseudoField;
    private JPasswordField passwordField;
    private JTextField storeField;
    private final UserController controller;

    public UserView(UserController userController) throws SQLException {
        this.controller = userController;
        getData();
        centerTable();
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            try {
                if (row >= 0) {
                    String role = (String) table.getValueAt(row, 3);
                    if (!Objects.equals(role, "admin")) {
                        int id = (int) table.getValueAt(row, 0);
                        controller.delete(id);
                    }
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        updateButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            String pseudo = this.pseudoField.getText();
            String email = this.emailField.getText();
            char[] password = this.passwordField.getPassword();
            String store = this.storeField.getText();
            try {
                if (row >= 0) {
                    int id = (int) table.getValueAt(row, 0);
                    if (controller.checkRegistration(email) || Objects.equals(email, table.getValueAt(row, 2))) {
                        controller.update(id, email, pseudo, password, store);
                        this.pseudoField.setText("");
                        this.emailField.setText("");
                        this.passwordField.setText("");
                        this.storeField.setText("");
                    }
                }
            } catch (SQLException | NoSuchAlgorithmException ex) {
                throw new RuntimeException(ex);
            }
        });
        createButton.addActionListener(e -> {
            String pseudo = this.pseudoField.getText();
            String email = this.emailField.getText();
            char[] password = this.passwordField.getPassword();
            String store = this.storeField.getText();
            try {
                if (controller.checkRegistration(email)) {
                    controller.insert(email, pseudo, password, store);
                    this.pseudoField.setText("");
                    this.emailField.setText("");
                    this.passwordField.setText("");
                    this.storeField.setText("");
                }
            } catch (SQLException | NoSuchAlgorithmException ex) {
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
