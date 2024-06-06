package com.example.navalbattlefinal.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Frigate {
    private Polygon frigate;

    public Frigate() {
        frigate = new Polygon(10.0, 0.0,
                20.0, 10.0,
                18.0, 25.0,
                2.0, 25.0,
                0.0, 10.0);
        frigate.setFill(Color.GRAY);
        frigate.setStroke(Color.BLACK);
        frigate.setStrokeWidth(2.0);
    }

    public Polygon getFrigate() {
        return frigate;
    }
}
