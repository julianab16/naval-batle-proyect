package com.example.navalbattlefinal.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
/**
 * Represents a submarine object.
 */
public class Submarine {
    private Polygon submarine; // Polygon representing the submarine shape

    /**
     * Constructs a new Submarine object.
     */
    public Submarine() {
        // Define the points of the submarine polygon
        submarine = new Polygon(
                40.0, 0.0,
                50.0, 10.0,
                45.0, 30.0,
                50.0, 35.0,
                50.0, 40.0,
                40.0, 37.0,
                30.0, 40.0,
                30.0, 35.0,
                35.0, 30.0,
                30.0, 10.0
        );
        // Set the fill color, stroke color, and stroke width of the submarine
        submarine.setFill(Color.GRAY);
        submarine.setStroke(Color.BLACK);
        submarine.setStrokeWidth(2.0);
    }

    /**
     * Gets the polygon representing the submarine.
     * @return The submarine polygon.
     */
    public Polygon getSubmarine() {
        return submarine;
    }
}
