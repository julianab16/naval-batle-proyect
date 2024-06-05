package com.example.navalbattlefinal.model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class AircraftCarrier {

    private Polygon aircraftCarrier;

    public AircraftCarrier() {
        aircraftCarrier = new Polygon(
                13.0, 0.0,
                25.0, 0.0,
                25.0, 86.0,
                0.0, 86.0,
                0.0, 20.0,
                13.0, 10.0);
        aircraftCarrier.setFill(Color.GRAY);
        aircraftCarrier.setStroke(Color.BLACK);
        aircraftCarrier.setStrokeWidth(2.0);
    }

    public Polygon getAircraftCarrier() {
        return aircraftCarrier;
    }
}
