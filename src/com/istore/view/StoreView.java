package com.istore.view;

import com.istore.controller.StoreController;
import com.istore.observer.Observer;

import javax.swing.*;
import java.sql.ResultSet;

public class StoreView implements Observer {
    private JPanel mainPanel;

    public StoreView(StoreController storeController) {

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void update(ResultSet resultSet) {

    }
}
