package com.example.navalbattlefinal.model;
import java.util.List;


import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Ship {
    //public final Integer label;

    Polygon aircraftCarrier;

    public Ship(){
        //this.label = label;
        Polygon aircraftCarrier = new Polygon(
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
    /*public Pane getAircraftCarrier(){
        return paneAircraftCarrier;
    }*/
}
/*
public class Ship {
    private Pane paneAircraftCarrier;
    private Polygon aircraftCarrier;
    private boolean isHorizontal = true;
    private int size;

    public Ship(int size) {
        this.size = size;
        paneAircraftCarrier = new Pane();
        aircraftCarrier = new Polygon();
        updateShape();
        aircraftCarrier.setStroke(Color.BLACK);
        aircraftCarrier.setFill(Color.GRAY);
        paneAircraftCarrier.getChildren().addAll(aircraftCarrier);
    }

    public Pane getPane() {
        return paneAircraftCarrier;
    }

    public void rotate() {
        isHorizontal = !isHorizontal;
        updateShape();
    }

    private void updateShape() {
        aircraftCarrier.getPoints().clear();
        if (isHorizontal) {
            aircraftCarrier.getPoints().addAll(0.0, 0.0, size * 36.0, 0.0, size * 36.0, 36.0, 0.0, 36.0);
        } else {
            aircraftCarrier.getPoints().addAll(0.0, 0.0, 36.0, 0.0, 36.0, size * 36.0, 0.0, size * 36.0);
        }
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public int getSize() {
        return size;
    }

    public void setPosition(double x, double y) {
        paneAircraftCarrier.setLayoutX(x);
        paneAircraftCarrier.setLayoutY(y);
    }
}*/