package com.istore.view;

import com.istore.controller.RoleController;
import com.istore.observer.Observer;

import javax.swing.*;
import java.sql.ResultSet;

public class RoleView implements Observer {
    private JPanel mainPanel;

    public RoleView(RoleController roleController) {

    }
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void update(ResultSet resultSet) {

    }
}
