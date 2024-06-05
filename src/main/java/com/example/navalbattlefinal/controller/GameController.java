package com.example.navalbattlefinal.controller;


import com.example.navalbattlefinal.view.Table;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.Random;

public class GameController {

    @FXML
    private HBox hboxPanes;
    @FXML
    private AnchorPane anchorpaneOne;
    @FXML
    private AnchorPane anchorpaneTwo;
    @FXML
    private Label labelEnemy;

    private int[][] position1 = new int[11][11];
    private int previousCol = -1;
    private int previousRow = -1;
    private int[][] position2 = new int[11][11];
    Table tableOne = new Table();
    Table tableTwo = new Table();
    Table tableClicks = new Table();
    GridPane gridPane = new GridPane();
    GridPane gridPaneTwo = new GridPane();
    private static final int[] SHIP_SIZES = {4, 3, 3, 2, 2, 2, 1, 1, 1};
    private static final String[] list = new String[]{"portaAvion", "submarino", "destructor", "fragata"};
    private static final Random random = new Random();
    private static final int rows = 11;
    private static final int columns = 11;
    private static final int CELL_SIZE = 36;
    private int currentRow = 0;
    private int currentCol = 0;
    private boolean isHorizontal = true; // Estado inicial del barco
    private int currentShipIndex = 0; // Índice del barco actual

    private Rectangle ship;

    //Ship ship = new Ship();



    @FXML
    public void inicialize() {
    try {
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
                panel.setStyle("-fx-padding: 17px; -fx-border-color: GHOSTWHITE; -fx-opacity:50%; -fx-border-width:17 px; ");
                gridPane.setAlignment(Pos.CENTER);
                gridPane.setBackground((new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, null))));
                gridPane.add(panel, col, row);

                if (row == 0 && col > 0) {
                    Label label = new Label(Integer.toString(col));
                    label.setStyle("-fx-padding:5px; -fx-font-size: 16 px; -fx-text-fill: black; -fx-background-color:#9b9898; -fx-alignment: center");
                    label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    GridPane.setHgrow(label, Priority.ALWAYS);
                    GridPane.setVgrow(label, Priority.ALWAYS);
                    GridPane.setHalignment(label, HPos.CENTER); // Alinear el texto al centro horizontalmente
                    GridPane.setValignment(label, VPos.CENTER);
                    gridPane.add(label, col, row); // Agregar el número a la celda
                }
                if (col == 0 && row > 0) {
                    Label label = new Label(Integer.toString(row));
                    label.setStyle("-fx-padding: 5 px; -fx-font-size: 16px; -fx-text-fill: black; -fx-background-color:#9b9898; -fx-alignment: center");
                    label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    GridPane.setHgrow(label, Priority.ALWAYS);
                    GridPane.setVgrow(label, Priority.ALWAYS);
                    GridPane.setHalignment(label, HPos.CENTER); // Alinear el texto al centro horizontalmente
                    GridPane.setValignment(label, VPos.CENTER);
                    gridPane.add(label, col, row);// Agregar el número a la celda
                } else if (row == 0 && col == 0) { // Si es la esquina superior izquierda
                    // No hacer nada, ya que aquí no queremos números ni barcos
                }
            }
        }
        anchorpaneOne.getChildren().add(gridPane);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Pane panelTwo = new Pane();
                panelTwo.setBorder(border);
                panelTwo.setStyle("-fx-padding: 17px; -fx-border-color: GHOSTWHITE; -fx-opacity:50%; -fx-border-width:12 px; "); // Padding interno del panel
                gridPane.setAlignment(Pos.CENTER);
                gridPaneTwo.setBackground((new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, null))));
                gridPaneTwo.add(panelTwo, col, row);

                if (row == 0 && col > 0) {
                    Label label = new Label(Integer.toString(col));
                    label.setStyle("-fx-padding: 5px; -fx-font-size: 16px; -fx-text-fill: black; -fx-background-color:#9b9898; -fx-alignment: center"); // Fondo blanco y tamaño de letra 16px
                    label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    GridPane.setHgrow(label, Priority.ALWAYS);
                    GridPane.setVgrow(label, Priority.ALWAYS);
                    GridPane.setHalignment(label, HPos.CENTER); // Alinear el texto al centro horizontalmente
                    GridPane.setValignment(label, VPos.CENTER);
                    GridPane.setHalignment(label, HPos.CENTER); // Alinear el texto al centro horizontalmente
                    gridPaneTwo.add(label, col, row); // Agregar el número a la celda
                }
                if (col == 0 && row > 0) {
                    Label label = new Label(Integer.toString(row));
                    label.setStyle("-fx-padding: 5px; -fx-font-size: 16px; -fx-text-fill: black; -fx-background-color:#9b9898; -fx-alignment: center");
                    label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    GridPane.setHgrow(label, Priority.ALWAYS);
                    GridPane.setVgrow(label, Priority.ALWAYS);
                    GridPane.setHalignment(label, HPos.CENTER); // Alinear el texto al centro horizontalmente
                    GridPane.setValignment(label, VPos.CENTER);
                    gridPaneTwo.add(label, col, row); // Agregar el número a la celda
                }

            }
        }

        anchorpaneTwo.getChildren().add(gridPaneTwo);
        //putBoatsEnemy();
        ship = new Rectangle(SHIP_SIZES[currentShipIndex] * CELL_SIZE, CELL_SIZE);
        ship.setFill(Color.GRAY);

        // Añadir un EventHandler para cambiar la orientación al hacer clic
        ship.setOnMouseClicked(this::handleShipClick);
        System.out.println(ship);
        gridPane.setOnMouseMoved(this::handleMouseMoved);

        // Añadir el barco al grid
        gridPane.add(ship, 1, 1, SHIP_SIZES[currentShipIndex], 1);

        Scene scene = gridPane.getScene();
        if (scene != null) {
            scene.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKeyPressed);
        } else {
            gridPane.sceneProperty().addListener((observable, oldScene, newScene) -> {
                if (newScene != null) {
                    newScene.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKeyPressed);
                }
            });
        }
    }catch (Exception e) {
        System.err.println("Error Initialize: " + e.getMessage());
    }
    }

    private void handleKeyPressed(KeyEvent event) {
        try {
            switch (event.getCode()) {
                case R: // Tecla para rotar
                    rotateShip();
                    break;
                default:
                    break;
            }
        }catch (Exception e){
        System.err.println("Error HandleKeyPressed: " + e.getMessage());
    }
    }
    private void placeShipOnBoard() {
        try {
            int shipSize = SHIP_SIZES[currentShipIndex];

            if (isHorizontal) {
                for (int i = 0; i < shipSize; i++) {
                    // Actualiza la posición de la tabla del jugador aquí
                    position1[currentRow][currentCol + i] = 1;
                }
            } else {
                for (int i = 0; i < shipSize; i++) {
                    // Actualiza la posición de la tabla del jugador aquí
                    position1[currentRow + i][currentCol] = 1;
                }
            }

            //anchorpaneTwo.getChildren().add(gridPaneTwo);

            // Pane appearPane= ship.getAircraftCarrier();

            //hboxPanes.getChildren().add(appearPane);
        } catch (Exception e) {
            System.err.println("Error placeShipOnBoard: " + e.getMessage());
        }
    }





    private void updateShipSize() {
        try {
            int shipSize = SHIP_SIZES[currentShipIndex];
            if (isHorizontal) {
                ship.setWidth(shipSize * CELL_SIZE);
                ship.setHeight(CELL_SIZE);
            } else {
                ship.setWidth(CELL_SIZE);
                ship.setHeight(shipSize * CELL_SIZE);
            }

            // Reposicionar el barco para que quede en su lugar actual
            GridPane.setColumnSpan(ship, isHorizontal ? shipSize : 1);
            GridPane.setRowSpan(ship, isHorizontal ? 1 : shipSize);
        } catch (Exception e) {
            System.err.println("Error update Ship Size: " + e.getMessage());
        }
    }

    private void rotateShip() {
        try {
            int shipSize = SHIP_SIZES[currentShipIndex];
            if (isHorizontal) {
                if (currentRow + shipSize <= 11) {
                    // Cambiar a vertical
                    ship.setWidth(CELL_SIZE);
                    ship.setHeight(shipSize * CELL_SIZE);
                    isHorizontal = false;
                }
            } else {
                if (currentCol + shipSize <= 11) {
                    // Cambiar a horizontal
                    ship.setWidth(shipSize * CELL_SIZE);
                    ship.setHeight(CELL_SIZE);
                    isHorizontal = true;
                }
            }
            // Reposicionar el barco para que quede en su lugar actual
            GridPane.setColumnIndex(ship, currentCol);
            GridPane.setRowIndex(ship, currentRow);
            GridPane.setColumnSpan(ship, isHorizontal ? shipSize : 1);
            GridPane.setRowSpan(ship, isHorizontal ? 1 : shipSize);
        }catch (Exception e) {
            System.err.println("Error rotateShip: " + e.getMessage());
        }
    }

    private void handleShipClick(MouseEvent event) {
        Integer shipCol = gridPane.getColumnIndex(ship);
        Integer shipRow = gridPane.getRowIndex(ship);
        System.out.println(shipCol + " " + shipRow);
        Integer shipCol2 = gridPaneTwo.getColumnIndex(ship);
        Integer shipRow2 = gridPaneTwo.getRowIndex(ship);
        System.out.println(shipCol2 +" "+ shipRow2);
        try {
            int shipSize = SHIP_SIZES[currentShipIndex];
            boolean canPlace = true;

            for (int i = 0; i < shipSize; i++) {
                int col = isHorizontal ? shipCol + i : shipCol;
                int row = isHorizontal ? shipRow : shipRow + i;
                if (!checkCell(row, col, tableOne.getTable())) {
                    canPlace = false;
                    break;
                }
            }
            if (canPlace) {
                placeShipOnBoard();
                currentShipIndex++;
                if (currentShipIndex < SHIP_SIZES.length) {
                    updateShipSize();
                } else {
                    System.out.println("All ships placed.");
                    gridPane.getChildren().remove(ship);
                }
            } else {
                System.out.println("Cannot place ship here.");
            }

            GridPane.setColumnIndex(ship, currentCol);
            GridPane.setRowIndex(ship, currentRow);
            GridPane.setColumnSpan(ship, isHorizontal ? shipSize : 1);
            GridPane.setRowSpan(ship, isHorizontal ? 1 : shipSize);

        /*for (int i = 0; i < colSpan; i++) {
            for (int j = 0; j < rowSpan; j++) {
                int col = shipCol + i;
                int row = shipRow + j;
                Pane pane = (Pane) gridPane.getChildren().get(shipRow + j * 11 + shipCol + i);
                pane.setOnMouseClicked(null);

                if (checkCell(row,col, tableOne.getTable())) {
                    System.out.println("Ha puesto un barco at row " + row + ", column " + col);

                    placeShipOnBoard();

                    // Cambiar al siguiente barco
                    currentShipIndex++;
                    if (currentShipIndex < SHIP_SIZES.length) {
                        updateShipSize();
                    } else {
                        System.out.println("All ships placed.");
                    }
         */
        }catch (Exception e) {
            System.err.println("Error HandleHandClick: " + e.getMessage());
        }
    }

    private void handleMouseMoved(MouseEvent event) {
        try {

            int col = (int) (event.getX() / CELL_SIZE);
            int row = (int) (event.getY() / CELL_SIZE);


            String[][] tableEnemy = tableTwo.getTable();
            //System.out.println(tableEnemy);
            //if ([col][row] == tableEnemy[col][row])
            //System.out.println("Col: " + col + ", Row: " + row);
            //System.out.println("tableEnemy length: " + tableEnemy.length);
            if (col != previousCol || row != previousRow) {
                // Realizar acciones solo si la posición del mouse ha cambiado
                previousCol = col;
                previousRow = row;

                // Verificar que el barco no se salga de los límites
                int shipSize = SHIP_SIZES[currentShipIndex];
                if (isHorizontal) {
                    if (col + shipSize <= 11 && row < 11 && row > 0 && col > 0) {
                        currentCol = col;
                        currentRow = row;
                        GridPane.setColumnIndex(ship, col);
                        GridPane.setRowIndex(ship, row);
                    }
                } else {
                    if (row + shipSize <= 11 && col < 11 && row > 0 && col > 0) {
                        currentCol = col;
                        currentRow = row;
                        GridPane.setColumnIndex(ship, col);
                        GridPane.setRowIndex(ship, row);
                    }
            }

            }
        }catch (Exception e) {
            System.err.println("Error handleMouseMoved: " + e.getMessage());
        }
    }
    @FXML
    void buttonPlayGame(ActionEvent event) {
        try {
            tableTwo.setTable();
            gridPaneTwo.setVisible(true);
            anchorpaneTwo.setVisible(true);
            labelEnemy.setVisible(true);

            System.out.println("Matriz del Jugador:");
            tableOne.printPlayerBoard();
            gridPaneTwo.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                             @Override
                                             public void handle(MouseEvent event) {
                                                 try {
                                                     double x = event.getX();
                                                     double y = event.getY();

                                                     // Obtener el tamaño de las celdas
                                                     double cellWidth = gridPaneTwo.getWidth() / columns;
                                                     double cellHeight = gridPaneTwo.getHeight() / rows;

                                                     // Calcular la columna y la fila basándose en las coordenadas del evento
                                                     int clickedCol = (int) (x / cellWidth);
                                                     int clickedRow = (int) (y / cellHeight);

                                                     System.out.println("Clic en fila " + clickedRow + ", columna " + clickedCol);
                                                 } catch (Exception e) {
                                                     System.err.println("Error ButtonPlayGame: " + e.getMessage());
                                                 }
                                             }
            });

            System.out.println("Matriz del Enemigo:");
            placeShips(tableTwo.getTable());
            tableTwo.printPlayerBoard();

            ship.setVisible(false);
        }catch (Exception e) {
            System.err.println("Error ButtonPlayGame: " + e.getMessage());
            }
    }

    public boolean checkCell (int row, int cols, String[][] table){
        System.out.println();
        boolean r = false;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (table[row][cols] == ".") {
                    table[row][cols] = "X";
                    r = true;
                }
            }
        }
        return r;
    }

    private static void placeShips(String[][] table) {
            try{
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
            }catch (Exception e) {
                System.err.println("Error placeShips: " + e.getMessage());
            }
    }

    private static boolean canPlaceShip(String[][] table, int row, int col, int size, boolean horizontal) {
        try{
            if (horizontal) {
                if (col + size > 11) return false; // Check bounds
                for (int i = 0; i < size; i++) {
                    if (!table[row][col + i].equals(".")) return false; // Check overlap
                }
            } else {
                if (row + size > 11) return false; // Check bounds
                for (int i = 0; i < size; i++) {
                    if (!table[row + i][col].equals(".")) return false; // Check overlap
                }
            }
        }catch (Exception e) {
            System.err.println("Error CanPlaceShip: " + e.getMessage());
        }
        return true;
    }

    private static void placeShip(String[][] table, int row, int col, int size, boolean horizontal) {
        try{
            if (horizontal) {
                for (int i = 0; i < size; i++) {
                    table[row][col + i] = "X";
                }
            } else {
                for (int i = 0; i < size; i++) {
                    table[row + i][col] = "X";
                }
            }
        }catch (Exception e) {
            System.err.println("Error placeShip: " + e.getMessage());
        }
    }

}
