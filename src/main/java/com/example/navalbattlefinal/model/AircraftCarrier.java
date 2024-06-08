package com.example.navalbattlefinal.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * Class representing an Aircraft Carrier ship.
 */
public class AircraftCarrier {
    private Polygon aircraftCarrier;

    /**
     * Constructs an Aircraft Carrier ship with predefined dimensions and styling.
     */
    public AircraftCarrier() {
        aircraftCarrier = new Polygon(
                100.0, 0.0,
                110.0, 0.0,
                110.0, 60.0,
                90.0, 60.0,
                90.0, 20.0,
                100.0, 10.0
        );
        aircraftCarrier.setFill(Color.GRAY);
        aircraftCarrier.setStroke(Color.BLACK);
        aircraftCarrier.setStrokeWidth(2.0);
    }

    /**
     * Retrieves the Polygon representing the Aircraft Carrier ship.
     * @return The Polygon object representing the Aircraft Carrier ship.
     */
    public Polygon getAircraftCarrier() {
        return aircraftCarrier;
    }
}

