package com.example.navalbattlefinal.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Submarine {
    private Polygon submarine;
    public Submarine() {
        submarine = new Polygon(40.0, 0.0,
                50.0, 10.0,
                45.0, 30.0,
                50.0, 35.0,
                50.0, 40.0,
                40.0, 37.0,
                30.0, 40.0,
                30.0, 35.0,
                35.0, 30.0,
                30.0, 10.0);
        submarine.setFill(Color.GRAY);
        submarine.setStroke(Color.BLACK);
        submarine.setStrokeWidth(2.0);
    }
    public Polygon getSubmarine() {
        return submarine;
    }
}
