package com.istore.controller;

import com.istore.model.AbstractModel;

public abstract class AbstractController {

    protected AbstractModel model;

    public AbstractController(AbstractModel model) {
        this.model = model;
    }
}
