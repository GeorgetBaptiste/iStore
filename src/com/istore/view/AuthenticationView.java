package com.istore.view;

import com.istore.controller.UserController;
import com.istore.controller.WhitelistController;
import com.istore.observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationView extends JFrame {
    private JPanel mainPanel;
    private JTextField registrationPseudo;
    private JTextField registrationEmail;
    private JPasswordField registrationPassword;
    private JTextField connectionEmail;
    private JPasswordField connectionPassword;
    private JButton registrationButton;
    private JButton connectionButton;
    private JLabel registrationInfo;
    private JLabel connectionInfo;
    private final UserController userController;
    private final WhitelistController whitelistController;

    public AuthenticationView(UserController userController, WhitelistController whitelistController) {
        this.userController = userController;
        this.whitelistController = whitelistController;
        settings();
        registrationButton.addActionListener(e -> {
            try {
                registration();
            } catch (SQLException | NoSuchAlgorithmException ex) {
                throw new RuntimeException(ex);
            }
        });
        connectionButton.addActionListener(e -> {
            try {
                connection();
            } catch (SQLException | NoSuchAlgorithmException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void settings() {
        setTitle("iStore : Authentication");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);
        pack();
        setVisible(true);
    }

    private void registration() throws SQLException, NoSuchAlgorithmException {
        boolean checkUser = userController.checkRegistration(this.registrationEmail.getText());
        boolean checkWhitelist = whitelistController.checkRegistration(this.registrationEmail.getText());
         if (checkUser && checkWhitelist) {
             userController.addRegistration(this.registrationEmail.getText(), this.registrationPseudo.getText(), this.registrationPassword.getPassword());
             registrationInfo.setForeground(new Color(0,255,0));
             registrationInfo.setText("Registration successful.");
         } else if (checkWhitelist) {
             registrationInfo.setForeground(new Color(255,0,0));
             registrationInfo.setText("User already exists.");
         } else {
             registrationInfo.setForeground(new Color(255,0,0));
             registrationInfo.setText("Email not in white list.");
         }
    }

    private void connection() throws SQLException, NoSuchAlgorithmException {
        boolean checkUser = userController.checkConnection(this.connectionEmail.getText(), this.connectionPassword.getPassword());
        if (checkUser) {
            dispose();
        } else {
            connectionInfo.setText("Invalid email or password.");
            connectionInfo.setForeground(new Color(255, 0, 0));
        }
    }
}
