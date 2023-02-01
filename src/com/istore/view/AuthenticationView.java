package com.istore.view;

import com.istore.controller.*;
import com.istore.model.*;

import javax.swing.*;
import java.awt.*;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
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
    private final WhiteList whiteList;
    private final User user;
    private final Connection conn;

    public AuthenticationView(UserController userController, WhitelistController whitelistController, User user, WhiteList whiteList, Connection conn) {
        this.userController = userController;
        this.whitelistController = whitelistController;
        this.user = user;
        this.whiteList = whiteList;
        this.conn = conn;
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
        settings();
    }

    private void settings() {
        setTitle("iStore : Authentication");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(700, 400);
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
        int userId = userController.checkConnection(this.connectionEmail.getText(), this.connectionPassword.getPassword());
        if (userId != 0) {
            int roleId = userController.getRole(userId);
            Inventory inventory = new Inventory(conn);
            InventoryController inventoryController = new InventoryController(inventory);
            InventoryView inventoryView = new InventoryView(userId, inventoryController);
            EmployeeView employeeView = new EmployeeView(userId, userController);
            inventory.addObserver(inventoryView);
            user.addObserver(employeeView);
            if (roleId == 1) {
                Article article = new Article(conn);
                Store store = new Store(conn);
                ArticleController articleController = new ArticleController(article);
                StoreController storeController = new StoreController(store);
                ArticleView articleView = new ArticleView(articleController);
                StoreView storeView = new StoreView(storeController);
                UserView userView = new UserView(userController);
                WhiteListView whiteListView = new WhiteListView(whitelistController);
                article.addObserver(articleView);
                store.addObserver(storeView);
                user.addObserver(userView);
                whiteList.addObserver(whiteListView);
                new AppView(whiteListView, articleView, storeView, userView);
            } else {
                new AppView(employeeView, inventoryView);
            }
            setVisible(false);
        } else {
            connectionInfo.setText("Invalid email or password.");
            connectionInfo.setForeground(new Color(255, 0, 0));
        }
    }
}
