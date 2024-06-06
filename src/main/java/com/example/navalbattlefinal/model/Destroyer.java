package com.example.navalbattlefinal.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Destroyer {
    private Polygon destroyer;

    public Destroyer() {
        destroyer = new Polygon(
                30.0, 0.0,
                40.0, 10.0,
                40.0, 40.0,
                30.0, 50.0,
                20.0, 40.0,
                20.0, 10.0);
        destroyer.setFill(Color.GRAY);
        destroyer.setStroke(Color.BLACK);
        destroyer.setStrokeWidth(2.0);
    }

    public Polygon getDestroyer() {
        return destroyer;
    }
}

