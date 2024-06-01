package com.example.navalbattlefinal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class GameController {
    private int[][] position1 = new int[10][10];
    private int[][] position2 = new int[10][10];
    static String[][] tablePlayer1 = new String[10][10];
    GridPane gridPane = new GridPane();
    GridPane gridPaneTwo = new GridPane();

    @FXML
    private AnchorPane anchorpaneOne;
    @FXML
    private AnchorPane anchorpaneTwo;

    @FXML
    public void inicialize() {
        int rows = 11;
        int columns = 11;

        BorderStroke borderStroke = new BorderStroke(
                Color.BLACK, // Color del borde
                BorderStrokeStyle.SOLID, // Estilo del borde
                CornerRadii.EMPTY, // Radios de las esquinas
                new BorderWidths(1) // Grosor del borde
        );

        Border border = new Border(borderStroke);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Pane panel = new Pane();
                panel.setBorder(border);
                panel.setStyle("-fx-padding: 17px; -fx-border-color: GHOSTWHITE; -fx-opacity:40%; -fx-border-width:12 px; "); // Padding interno del panel
                gridPane.setBackground((new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, null))));
                gridPane.add(panel,col,row);
                panel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    Integer rowIndex = GridPane.getRowIndex(panel);
                    Integer colIndex = GridPane.getColumnIndex(panel);
                    System.out.println("Button clicked at row " + rowIndex + ", column " + colIndex);
                });
            }
        }
        anchorpaneOne.getChildren().add(gridPane);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Pane panelTwo = new Pane();
                panelTwo.setBorder(border);
                panelTwo.setStyle("-fx-padding: 17px; -fx-border-color: GHOSTWHITE; -fx-opacity:40%; -fx-border-width:12 px; "); // Padding interno del panel
                gridPaneTwo.setBackground((new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, null))));
                gridPaneTwo.add(panelTwo,col,row);
                panelTwo.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    Integer rowIndex = GridPane.getRowIndex(panelTwo);
                    Integer colIndex = GridPane.getColumnIndex(panelTwo);
                    System.out.println("Button clicked at row " + rowIndex + ", column " + colIndex);
                });
            }
        }
        anchorpaneTwo.getChildren().add(gridPaneTwo);
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void setGridpane(GridPane gridPane) {
        this.gridPane= gridPane;
    }

    private Pane createColoredPane() {
        Pane pane = new Pane();
        //Rectangle rect = new Rectangle(55, 55);
        //rect.setFill(Color.color(Math.random(), Math.random(), Math.random()));
        //pane.getChildren().add(rect);
        return pane;
    }
    String[] list = new String[]{"portaAvion", "submarino", "destructor", "fragata"};
    @FXML
    void buttonPlayGame(ActionEvent event) {

    }
}
