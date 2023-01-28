package com.istore.view;

import com.istore.controller.ArticleController;
import com.istore.observer.Observer;

import javax.swing.*;
import java.sql.ResultSet;

public class ArticleView implements Observer {
    private JPanel mainPanel;

    public ArticleView(ArticleController articleController) {

    }
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void update(ResultSet resultSet) {

    }
}
