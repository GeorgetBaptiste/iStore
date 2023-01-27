package com.istore;

import com.istore.controller.WhitelistController;
import com.istore.model.WhiteList;
import com.istore.view.AppView;
import com.istore.view.WhiteListView;

public class Main {

    public static void main(String[] args) {
        WhiteList whiteList = new WhiteList();
        WhitelistController whiteListController = new WhitelistController(whiteList);
        WhiteListView whiteListView = new WhiteListView(whiteListController);
        whiteList.addObserver(whiteListView);
        new AppView(whiteListView);
    }
}
