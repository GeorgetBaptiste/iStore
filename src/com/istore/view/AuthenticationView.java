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

    public AuthenticationView(UserController userController, WhitelistController whitelistController) {
        setTitle("iStore : Authentication");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);
        pack();
        setVisible(true);
        registrationButton.addActionListener(e -> {
            try {
                if (whitelistController.checkRegistration(this.registrationEmail.getText()) && userController.checkRegistration(this.registrationEmail.getText())) {
                    userController.addRegistration(this.registrationEmail.getText(), this.registrationPseudo.getText(), this.registrationPassword.getPassword());
                    setRegisterInfo(true);
                } else {
                    setRegisterInfo(false);
                }
            } catch (SQLException | NoSuchAlgorithmException ex) {
                throw new RuntimeException(ex);
            }
        });
        connectionButton.addActionListener(e -> {
            dispose();
        });
    }

    public void setRegisterInfo(boolean checkRegistration) {
         if (checkRegistration) {
             registrationInfo.setForeground(new Color(0,255,0));
             registrationInfo.setText("Registration successful.");
         } else {
             registrationInfo.setForeground(new Color(255,0,0));
             registrationInfo.setText("Error : incorrect email.");
         }
    }
}
