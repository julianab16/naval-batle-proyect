package com.example.navalbattlefinal.model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class AircraftCarrier {
    private Polygon aircraftCarrier;

    public AircraftCarrier() {
        aircraftCarrier = new Polygon(100.0, 0.0,
                110.0, 0.0,
                110.0, 60.0,
                90.0, 60.0,
                90.0, 20.0,
                100.0, 10.0);
        aircraftCarrier.setFill(Color.GRAY);
        aircraftCarrier.setStroke(Color.BLACK);
        aircraftCarrier.setStrokeWidth(2.0);
    }

    public Polygon getAircraftCarrier() {
        return aircraftCarrier;
    }
}
