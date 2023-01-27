package com.istore.view;

import com.istore.observer.Observer;

import javax.swing.*;

public class AuthenticationView extends JFrame {
    private JPanel mainPanel;
    private JTextField registrationPseudo;
    private JTextField registrationEmail;
    private JPasswordField registrationPassword;
    private JTextField connectionEmail;
    private JPasswordField connectionPassword;
    private JButton registrationButton;
    private JButton connectionButton;

    public AuthenticationView() {
        setTitle("iStore : Authentication");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);
        pack();
        setVisible(true);
        registrationButton.addActionListener(e -> {
            dispose();
        });
        connectionButton.addActionListener(e -> {
            dispose();
        });
    }
}
