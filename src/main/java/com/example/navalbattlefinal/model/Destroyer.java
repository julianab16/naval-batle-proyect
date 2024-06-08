package com.example.navalbattlefinal.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * Class representing a destroyer boat in the game.
 */
public class Destroyer {
    private Polygon destroyer; // Polygon representing the destroyer boat

    /**
     * Constructor to create a destroyer boat with predefined polygon shape, fill color, and stroke.
     */
    public Destroyer() {
        destroyer = new Polygon(
                30.0, 0.0,
                40.0, 10.0,
                40.0, 40.0,
                30.0, 50.0,
                20.0, 40.0,
                20.0, 10.0);
        destroyer.setFill(Color.GRAY); // Set fill color to gray
        destroyer.setStroke(Color.BLACK); // Set stroke color to black
        destroyer.setStrokeWidth(2.0); // Set stroke width
    }
/**
 * Retrieves the polygon representing the destroyer boat.
 */
}



