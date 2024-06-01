package com.example.navalbattlefinal.controller;

import com.example.navalbattlefinal.view.Table;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GameController {
    private int[][] position1 = new int[10][10];
    private int[][] position2 = new int[10][10];
    Table tableOne = new Table();
    Table tableTwo = new Table();
    GridPane gridPane = new GridPane();
    GridPane gridPaneTwo = new GridPane();

    @FXML
    private AnchorPane anchorpaneOne;
    @FXML
    private AnchorPane anchorpaneTwo;
    @FXML
    private Label labelEnemy;

    private static final int[] SHIP_SIZES = {4, 3, 3, 2, 2, 2, 1, 1, 1};
    private static final String[] list = new String[]{"portaAvion", "submarino", "destructor", "fragata"};
    private Set<Integer> occupiedPositions = new HashSet<>();

    private static final Random random = new Random();
    private static final int CELL_SIZE = 36;
    private static final int SHIP_LENGTH = 4;
    private int currentRow = 0;
    private int currentCol = 0;

    private boolean isHorizontal = true; // Estado inicial del barco
    private int currentShipIndex = 0; // Índice del barco actual

    private Rectangle ship;

    @FXML
    public void inicialize() {
        int rows = 11;
        int columns = 11;
        gridPaneTwo.setVisible(false);
        anchorpaneTwo.setVisible(false);
        labelEnemy.setVisible(false);

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
                panel.setStyle("-fx-padding: 17px; -fx-border-color: GHOSTWHITE; -fx-opacity:40%; -fx-border-width:12 px; "); // Padding interno del panel
                gridPane.setBackground((new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, null))));
                gridPane.add(panel, col, row);
            }
        }
        anchorpaneOne.getChildren().add(gridPane);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Pane panelTwo = new Pane();
                panelTwo.setBorder(border);
                panelTwo.setStyle("-fx-padding: 17px; -fx-border-color: GHOSTWHITE; -fx-opacity:40%; -fx-border-width:12 px; "); // Padding interno del panel
                gridPaneTwo.setBackground((new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, null))));
                gridPaneTwo.add(panelTwo, col, row);
            }
        }

        anchorpaneTwo.getChildren().add(gridPaneTwo);
        //putBoatsEnemy();
        ship = new Rectangle(SHIP_LENGTH * CELL_SIZE, CELL_SIZE);
        ship.setFill(Color.GRAY);

        // Añadir un EventHandler para cambiar la orientación al hacer clic
        ship.setOnMouseClicked(this::handleShipClick);
        gridPane.setOnMouseMoved(this::handleMouseMoved);

        // Añadir el barco al grid
        gridPane.add(ship, 0, 0, SHIP_SIZES[currentShipIndex], 1);

    }

    private void handleShipClick(MouseEvent event) {

        if (event.getClickCount() == 2) { // Doble clic
            if (isHorizontal) {
                if (currentRow + SHIP_LENGTH <= 11) {
                    // Cambiar a vertical
                    ship.setWidth(CELL_SIZE);
                    ship.setHeight(SHIP_LENGTH * CELL_SIZE);
                    isHorizontal = false;
                }
            } else {
                if (currentCol + SHIP_LENGTH <= 11) {
                    // Cambiar a horizontal
                    ship.setWidth(SHIP_LENGTH * CELL_SIZE);
                    ship.setHeight(CELL_SIZE);
                    isHorizontal = true;
                }
            }
            // Reposicionar el barco para que quede en su lugar actual
            GridPane.setColumnIndex(ship, currentCol);
            GridPane.setRowIndex(ship, currentRow);
            GridPane.setColumnSpan(ship, isHorizontal ? SHIP_LENGTH : 1);
            GridPane.setRowSpan(ship, isHorizontal ? 1 : SHIP_LENGTH);
        }
        else {
            gridPane.setOnMouseClicked(mouseEvent -> handleCelClick(mouseEvent));
        }
    }

    private void handleMouseMoved(MouseEvent event) {
        int col = (int) (event.getX() / CELL_SIZE);
        int row = (int) (event.getY() / CELL_SIZE);

        // Verificar que el barco no se salga de los límites
        if (isHorizontal) {
            if (col + SHIP_LENGTH <= 11 && row < 11) {
                currentCol = col;
                currentRow = row;
                GridPane.setColumnIndex(ship, col);
                GridPane.setRowIndex(ship, row);
            }
        } else {
            if (row + SHIP_LENGTH <= 11 && col < 11) {
                currentCol = col;
                currentRow = row;
                GridPane.setColumnIndex(ship, col);
                GridPane.setRowIndex(ship, row);
            }
        }
    }

    private void handleCelClick(MouseEvent mouseEvent) {

        Integer shipCol = GridPane.getColumnIndex(ship);
        Integer shipRow = GridPane.getRowIndex(ship);

        int colSpan = GridPane.getColumnSpan(ship);
        int rowSpan = GridPane.getRowSpan(ship);

        for (int i = 0; i < colSpan; i++) {
            for (int j = 0; j < rowSpan; j++) {
                int col = shipCol + i;
                int row = shipRow + j;
                Pane pane = (Pane) gridPane.getChildren().get(shipRow + j * 11 + shipCol + i);
                pane.setOnMouseClicked(null);

                if ( checkCell(row,col, tableOne.getTable())) {
                    System.out.println("Ha puesto un barco at row " + row + ", column " + col);
                }
            }
        }
    }



    @FXML
    void buttonPlayGame(ActionEvent event) {
        gridPaneTwo.setVisible(true);
        anchorpaneTwo.setVisible(true);
        labelEnemy.setVisible(true);
        System.out.println("Matriz del Jugador:");
        tableOne.printPlayerBoard();

        System.out.println("Matriz del Enemigo:");
        placeShips(tableTwo.getTable());
        tableTwo.printPlayerBoard();

        ship.setVisible(false);
    }

    public boolean checkCell (int row, int cols, String[][] table){
        System.out.println();
        boolean r = false;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (table[row][cols] == ".") {
                    table[row][cols] = "X";
                    r = true;
                }
            }
        }
        return r;
    }

    private static void placeShips(String[][] table) {
        for (int size : SHIP_SIZES) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(10);
                int col = random.nextInt(10);
                boolean horizontal = random.nextBoolean();

                if (canPlaceShip(table, row, col, size, horizontal)) {
                    placeShip(table, row, col, size, horizontal);
                    placed = true;
                }
            }
        }
    }

    private static boolean canPlaceShip(String[][] table, int row, int col, int size, boolean horizontal) {
        if (horizontal) {
            if (col + size > 10) return false; // Check bounds
            for (int i = 0; i < size; i++) {
                if (!table[row][col + i].equals(".")) return false; // Check overlap
            }
        } else {
            if (row + size > 10) return false; // Check bounds
            for (int i = 0; i < size; i++) {
                if (!table[row + i][col].equals(".")) return false; // Check overlap
            }
        }
        return true;
    }

    private static void placeShip(String[][] table, int row, int col, int size, boolean horizontal) {
        if (horizontal) {
            for (int i = 0; i < size; i++) {
                table[row][col + i] = "x";
            }
        } else {
            for (int i = 0; i < size; i++) {
                table[row + i][col] = "x";
            }
        }
    }

}
