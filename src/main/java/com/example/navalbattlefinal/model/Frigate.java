package com.example.navalbattlefinal.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * Class representing a frigate boat in the game.
 */
public class Frigate {
    private Polygon frigate; // Polygon representing the frigate boat

    /**
     * Constructor to create a frigate boat with predefined polygon shape, fill color, and stroke.
     */
    public Frigate() {
        frigate = new Polygon(
                10.0, 0.0,
                20.0, 10.0,
                18.0, 25.0,
                2.0, 25.0,
                0.0, 10.0);
        frigate.setFill(Color.GRAY); // Set fill color to gray
        frigate.setStroke(Color.BLACK); // Set stroke color to black
        frigate.setStrokeWidth(2.0); // Set stroke width
    }

    /**
     * Retrieves the polygon representing the frigate boat.
     * @return The polygon representing the frigate boat.
     */
    public Polygon getFrigate() {
        return frigate;
    }
}
