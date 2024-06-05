package com.example.navalbattlefinal.model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Ship {
    //public final Integer label;

    Pane paneAircraftCarrier;
    Polygon aircraftCarrier;


    public Ship(Integer label){
        //this.label = label;

        paneAircraftCarrier= new Pane();
        paneAircraftCarrier.prefWidth(17);
        paneAircraftCarrier.prefHeight(17);
        aircraftCarrier= new Polygon(0,0,17,0,17,17,0,17);
        aircraftCarrier.setStroke(Color.BLACK);
        aircraftCarrier.setFill(Color.GRAY);
        paneAircraftCarrier.getChildren().addAll(aircraftCarrier);


    }

    public Pane getAircraftCarrier(){
        return paneAircraftCarrier;
    }

}
