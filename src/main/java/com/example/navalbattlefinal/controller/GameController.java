package com.example.navalbattlefinal.controller;
import com.example.navalbattlefinal.model.*;
import javafx.application.Platform;
import javafx.event.EventHandler;

import com.example.navalbattlefinal.view.Table;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import java.util.List;


/** Authors:
 Juliana Melissa Bolaños Araujo-2372224
 Ivan David Ausecha Salamanca-2328223
 Laura Stefania Salazar Blanco-2327896
 Emails:
 juliana.araujo@correounivalle.edu.co
 ivan.ausecha@correounivalle.edu.co
 laura.blanco@coreounivalle.edu.co
 */

public class GameController implements Serializable {
    @FXML
    private AnchorPane anchorPane;
    /**
     * AnchorPane for the player's board.
     */
    @FXML
    private AnchorPane anchorpaneOne;

    /**
     * AnchorPane for the enemy's board.
     */
    @FXML
    private AnchorPane anchorpaneTwo;

    /**
     * ImageView for displaying the enemy's board.
     */
    @FXML
    private ImageView imagenViewEnemy;

    /**
     * Serial version UID for serialization.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Player's board positions.
     */
    private int[][] position1 = new int[11][11];

    /**
     * Table object for the player.
     */
    Table tableOne = new Table();

    /**
     * Table object for the enemy.
     */
    Table tableTwo = new Table();

    /**
     * GridPane for displaying the player's board.
     */
    GridPane gridPane = new GridPane();

    /**
     * GridPane for displaying the enemy's board.
     */
    GridPane gridPaneTwo = new GridPane();

    /**
     * Sizes of the ships in the game.
     */
    private static final int[] SHIP_SIZES = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};

    /**
     * Constants for the board size and cell size.
     */
    private static final int rows = 11;
    private static final int columns = 11;
    private static final int CELL_SIZE = 38;

    /**
     * Current row index.
     */
    private int currentRow = 0;

    /**
     * Current column index.
     */
    private int currentCol = 0;

    /**
     * Flag for ship orientation (horizontal or vertical).
     */
    private boolean isHorizontal = true;

    /**
     * Index of the current ship being placed.
     */
    private int currentShipIndex = 0;

    /**
     * Rectangle representing the ship.
     */
    private Rectangle ship;

    private List<int[]> shotsTaken;

    /**
     * Flag to indicate if the rotate key is pressed.
     */
    private boolean rotateKeyPressed = false;

    /**
     * Set to store already shot positions.
     */
    private Set<String> alreadyShotPositions = new HashSet<>();
    /**
     * Represents a Frigate ship in the game.
     */
    Frigate frigate = new Frigate();

    /**
     * Represents a Submarine ship in the game.
     */
    Submarine submarine = new Submarine();

    /**
     * Represents a Destroyer ship in the game.
     */
    Destroyer destroyer = new Destroyer();

    /**
     * Represents an Aircraft Carrier ship in the game.
     */
    AircraftCarrier aircraftCarrier = new AircraftCarrier();

    /**
     * VBox container for organizing UI components in a vertical column.
     */
    VBox vBox = new VBox();

    /**
     * Main VBox container for the primary layout of the user interface.
     */
    VBox mainVbox = new VBox();

    /**
     * TextField for user input, potentially for the Frigate details.
     */
    TextField textField = new TextField();

    /**
     * TextField for user input, potentially for the Submarine details.
     */
    TextField textField2 = new TextField();

    /**
     * TextField for user input, potentially for the Destroyer details.
     */
    TextField textField3 = new TextField();

    /**
     * TextField for user input, potentially for the Aircraft Carrier details.
     */
    TextField textField4 = new TextField();


    private static final Random random = new Random();
    /**
     * Initializes the game controller.
     */
    public void inicialize(){
        this.shotsTaken = new ArrayList<>();
        showBoats(true);
        try {
            /**Hide player 2's board initially*/
            anchorpaneTwo.setVisible(false);
            /**Hide enemy's image initially*/
            imagenViewEnemy.setVisible(false);
            /**Define the border style for the grid cells*/
            BorderStroke borderStroke = new BorderStroke(
                    Color.BLACK,
                    BorderStrokeStyle.SOLID,
                    CornerRadii.EMPTY,
                    new BorderWidths(1)
            );

            Border border = new Border(borderStroke);
            /**Create the grid for player*/
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < columns; col++) {
                    Pane panel = new Pane();
                    panel.setStyle("-fx-padding: 18px; -fx-border-color: GHOSTWHITE; -fx-opacity:50%; -fx-border-width:17 px; ");
                    gridPane.setAlignment(Pos.CENTER);
                    gridPane.setBackground((new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, null))));
                    gridPane.add(panel, col, row);

                    if (row == 0 && col > 0) {
                        Label label = new Label(Integer.toString(col));
                        label.setStyle("-fx-padding:5px; -fx-font-size: 16 px; -fx-text-fill: black; -fx-background-color:#9b9898; -fx-alignment: center");
                        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                        GridPane.setHgrow(label, Priority.ALWAYS);
                        GridPane.setVgrow(label, Priority.ALWAYS);
                        GridPane.setHalignment(label, HPos.CENTER);
                        GridPane.setValignment(label, VPos.CENTER);
                        /** Add the number to the cell*/
                        gridPane.add(label, col, row);
                    }
                    if (col == 0 && row > 0) {
                        Label label = new Label(Integer.toString(row));
                        label.setStyle("-fx-padding: 5 px; -fx-font-size: 16px; -fx-text-fill: black; -fx-background-color:#9b9898; -fx-alignment: center");
                        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                        GridPane.setHgrow(label, Priority.ALWAYS);
                        GridPane.setVgrow(label, Priority.ALWAYS);
                        GridPane.setHalignment(label, HPos.CENTER);
                        /** Add the number to the cell*/
                        gridPane.add(label, col, row);
                    }
                }
            }
            anchorpaneOne.getChildren().add(gridPane);

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < columns; col++) {
                    Pane panelTwo = new Pane();
                    panelTwo.setBorder(border);
                    panelTwo.setStyle("-fx-padding: 18px; -fx-border-color: GHOSTWHITE; -fx-opacity:50%; -fx-border-width:12 px; "); // Padding interno del panel
                    gridPane.setAlignment(Pos.CENTER);
                    gridPaneTwo.setBackground((new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, null))));
                    gridPaneTwo.add(panelTwo, col, row);

                    if (row == 0 && col > 0) {
                        Label label = new Label(Integer.toString(col));
                        label.setStyle("-fx-padding: 5px; -fx-font-size: 16px; -fx-text-fill: black; -fx-background-color:#9b9898; -fx-alignment: center"); // Fondo blanco y tamaño de letra 16px
                        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                        GridPane.setHgrow(label, Priority.ALWAYS);
                        GridPane.setVgrow(label, Priority.ALWAYS);
                        GridPane.setHalignment(label, HPos.CENTER);
                        GridPane.setValignment(label, VPos.CENTER);
                        GridPane.setHalignment(label, HPos.CENTER);
                        gridPaneTwo.add(label, col, row);
                    }
                    if (col == 0 && row > 0) {
                        Label label = new Label(Integer.toString(row));
                        label.setStyle("-fx-padding: 5px; -fx-font-size: 16px; -fx-text-fill: black; -fx-background-color:#9b9898; -fx-alignment: center");
                        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                        GridPane.setHgrow(label, Priority.ALWAYS);
                        GridPane.setVgrow(label, Priority.ALWAYS);
                        GridPane.setHalignment(label, HPos.CENTER);
                        GridPane.setValignment(label, VPos.CENTER);
                        gridPaneTwo.add(label, col, row);
                    }

                }
            }

            ship = new Rectangle(SHIP_SIZES[currentShipIndex] * CELL_SIZE, CELL_SIZE);
            ship.setFill(Color.GRAY);
            /** Add a EventHandler to change the orientation on click*/
            ship.setOnMouseClicked(this::handleShipClick);
            gridPane.setOnMouseMoved(this::handleMouseMoved);
            /** Add the ship to the grid*/
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
        } catch (Exception e) {
            e.printStackTrace();
        }
}
    /**
     * Handles key press events.
     **/
    private void handleKeyPressed(KeyEvent event) {
        try {
            switch (event.getCode()) {
                case R:
                    rotateShip();
                    break;
                default:
                    break;
            }
        }catch (Exception e){
            System.err.println("Error HandleKeyPressed: " + e.getMessage());
        }
    }
    /**
     * Places the current ship on the player's board.
     */
    private void placeShipOnBoard() {
        try {
            int shipSize = SHIP_SIZES[currentShipIndex];

            if (isHorizontal) {
                for (int i = 1; i < shipSize; i++) {
                    position1[currentRow][currentCol + i] = 1;
                }
            } else {
                for (int i = 1; i < shipSize; i++) {
                    position1[currentRow + i][currentCol] = 1;
                }
            }

        } catch (Exception e) {
            System.err.println("Error placeShipOnBoard: " + e.getMessage());
        }
    }
    /**
     * Updates the size of the current ship based on its orientation and index.
     */
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
            GridPane.setColumnSpan(ship, isHorizontal ? shipSize : 1);
            GridPane.setRowSpan(ship, isHorizontal ? 1 : shipSize);
        } catch (Exception e) {
            System.err.println("Error update Ship Size: " + e.getMessage());
        }
    }
    /**
     * Rotates the current ship between horizontal and vertical orientations.
     * Ensures the ship fits within the board bounds after rotation.
     */
    private void rotateShip() {
        try {
            int shipSize = SHIP_SIZES[currentShipIndex];
            if (isHorizontal) {
                if (currentRow + shipSize <= 11) {
                    ship.setWidth(CELL_SIZE);
                    ship.setHeight(shipSize * CELL_SIZE);
                    isHorizontal = false;
                }
            } else {
                if (currentCol + shipSize <= 11) {
                    ship.setWidth(shipSize * CELL_SIZE);
                    ship.setHeight(CELL_SIZE);
                    isHorizontal = true;
                }
            }
            GridPane.setColumnSpan(ship, isHorizontal ? shipSize : 1);
            GridPane.setRowSpan(ship, isHorizontal ? 1 : shipSize);
        }catch (Exception e) {
            System.err.println("Error rotateShip: " + e.getMessage());
        }
    }
    /**
     * Rotates the current ship between horizontal and vertical orientations.
     * Ensures the ship fits within the board bounds after rotation.
     */
    private void rotateShi(Rectangle rectangle) {
        try {
            int shipSize = SHIP_SIZES[currentShipIndex];
            if (isHorizontal) {
                rectangle.setWidth(CELL_SIZE);
                rectangle.setHeight(shipSize * CELL_SIZE);

            } else {
                rectangle.setWidth(CELL_SIZE);
                rectangle.setHeight(shipSize * CELL_SIZE);
            }
        }catch (Exception e) {
            System.err.println("Error rotateShip: " + e.getMessage());
        }
    }
    /**
     * Handles the event when a ship is clicked, attempting to place the ship on the grid.
     * If the ship can be placed at the clicked location, it updates the grid and advances to the next ship.
     * If not, it visually indicates the placement failure.
     **/
    private void handleShipClick(MouseEvent event) {
        Integer shipCol = gridPane.getColumnIndex(ship);
        Integer shipRow = gridPane.getRowIndex(ship);
        System.out.println(shipCol + " " + shipRow);

        Rectangle sh = new Rectangle(SHIP_SIZES[currentShipIndex] * CELL_SIZE, CELL_SIZE);
        sh.setFill(Color.BLUEVIOLET);
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
                ship.setFill(Color.GRAY);
                placeShipOnBoard();
                currentShipIndex++;

                if (isHorizontal) {
                    gridPane.add(sh, shipCol, shipRow, shipSize, 1);
                } else {
                    rotateShi(sh);
                    gridPane.add(sh, shipCol, shipRow, 1, shipSize);

                }
                if (currentShipIndex < SHIP_SIZES.length) {
                    updateShipSize();
                } else {
                    System.out.println("All ships placed.");
                    gridPane.getChildren().remove(ship);
                }

            } else {
                ship.setFill(Color.RED);
                System.out.println("Cannot place ship here.");
            }
            GridPane.setColumnIndex(ship, currentCol);
            GridPane.setRowIndex(ship, currentRow);
            GridPane.setColumnSpan(ship, isHorizontal ? shipSize : 1);
            GridPane.setRowSpan(ship, isHorizontal ? 1 : shipSize);

        }catch (Exception e) {
            System.err.println("Error HandleHandClick: " + e.getMessage());
        }
    }
    /**
     * Handles the event when the mouse is moved, updating the position of the ship on the grid.
     * If the 'R' key has been pressed, it will rotate the ship before updating its position.
     **/
    private void handleMouseMoved(MouseEvent event) {
        int r = (int) event.getX()/CELL_SIZE;
        int c = (int) event.getY()/CELL_SIZE;
        if (r > 0 && c > 0) {
            try {
                /** Handle ship rotation if the 'R' key was pressed
                 **/
                if (rotateKeyPressed) {
                    rotateShip();
                    rotateKeyPressed = false;
                }
                int shipSize = SHIP_SIZES[currentShipIndex];
                Node node = event.getPickResult().getIntersectedNode();
                if (node != null && GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null) {
                    int col = GridPane.getColumnIndex(node);
                    int row = GridPane.getRowIndex(node);
                    if (isHorizontal && col + shipSize <= 11 && row >= 1 && row <= 11) {
                        currentCol = col;
                        currentRow = row;
                    } else if (!isHorizontal && row + shipSize <= 11 && col >= 1 && col <= 11) {
                        currentCol = col;
                        currentRow = row;
                    }
                    if (isHorizontal) {
                        GridPane.setColumnIndex(ship, currentCol);
                        GridPane.setRowIndex(ship, currentRow);
                    } else {
                        GridPane.setColumnIndex(ship, currentCol);
                        GridPane.setRowIndex(ship, currentRow);
                    }
                }
            } catch (Exception e) {
                System.err.println("");
            }
        }
    }
    /**
     * Initializes the game when the play button is clicked. Sets up the game boards for the player and the enemy,
     * makes the necessary UI elements visible, and sets up the event handler for player actions.
     **/
    boolean gameFinished = false;
    private boolean playerTurn = true;

    @FXML
    void buttonPlayGame(ActionEvent event) {
        try {
            showBoats(false);
            tableTwo.setTable();
            gridPaneTwo.setVisible(true);
            anchorpaneTwo.setVisible(true);
            imagenViewEnemy.setVisible(true);
            ship.setVisible(false);
            System.out.println("Matriz del Jugador:");
            tableOne.printPlayerBoard();
            anchorpaneTwo.getChildren().add(gridPaneTwo);
            System.out.println("Matriz del Enemigo:");
            placeShips(tableTwo.getTable());
            tableTwo.printPlayerBoard();
            /**Set up the event handler for mouse clicks on the enemy's grid
             **/
            gridPaneTwo.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        if (!gameFinished) {
                            double x = event.getX();
                            double y = event.getY();
                            double cellWidth = gridPaneTwo.getWidth() / columns;
                            double cellHeight = gridPaneTwo.getHeight() / rows;
                            int clickedCol = (int) (x / cellWidth);
                            int clickedRow = (int) (y / cellHeight);

                            System.out.println("Clic en fila " + clickedRow + ", columna " + clickedCol);

                            if (playerTurn) {
                                handlePlayerTurn(clickedRow, clickedCol);
                            } else {
                                handleMachineTurn();
                            }
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks if a ship located at the specified row and column in the given table has been sunk.
     * A ship is considered sunk if all its parts are hit (indicated by "*"). This method checks
     * both horizontally and vertically to determine the ship's status.
     **/

    public List<int[]> isShipSunk(int row, int col, String[][] table) {
        List<int[]> shipPositions = new ArrayList<>();

        /** Check horizontally to the right
         **/
        int startCol = col;
        while (startCol > 0 && (table[row][startCol - 1].equals("X") || table[row][startCol - 1].equals("*"))) {
            startCol--;
        }
        shipPositions.clear();

        boolean shipSunk = true;
        for (int i = startCol; i < table[0].length && (table[row][i].equals("X") || table[row][i].equals("*")); i++) {
            if (table[row][i].equals("X")) {
                shipSunk = false;
                break;
            }
            shipPositions.add(new int[]{row, i});
        }
        if (shipSunk) return shipPositions;

        /** Check vertically downwards
         **/
        int startRow = row;
        while (startRow > 0 && (table[startRow - 1][col].equals("X") || table[startRow - 1][col].equals("*"))) {
            startRow--;
        }

        shipSunk = true;
        for (int i = startRow; i < table.length && (table[i][col].equals("X") || table[i][col].equals("*")); i++) {
            if (table[i][col].equals("X")) {
                shipSunk = false;
                break;
            }
            shipPositions.add(new int[]{i, col});
        }
        if (shipSunk) return shipPositions;

        return null;
    }
    /**
     * Checks if all ships of the player on the given table are sunk.
     * A ship is considered sunk if all its parts (indicated by "X") are hit.
     **/
    private boolean allPlayersShipsSunk(String[][] table) {
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[row].length; col++) {
                if (table[row][col].equals("X")) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Handles the player's turn when they click on a cell in the enemy's grid.
     * This method checks the validity of the click, updates the game state, and
     * determines if the player has won the game.
     **/
    private void handlePlayerTurn(int clickedRow, int clickedCol) {
        if ((clickedRow < 1 || clickedRow > 11) || (clickedCol < 1 || clickedCol > 11)) {
            return;
        }
        if (alreadyShotPositions.contains(clickedRow + "," + clickedCol)) {
            return;
        }
        alreadyShotPositions.add(clickedRow + "," + clickedCol);

        if (tableTwo.getTable()[clickedRow][clickedCol].equals("X")) {
            System.out.println("¡Impacto! El jugador golpeó un barco enemigo en la posición " + clickedRow + ", " + clickedCol);
            tableTwo.getTable()[clickedRow][clickedCol] = "H";
            String IMAGE_PATH = "/com/example/navalbattlefinal/images/tocado.png";
            Image image = new Image(getClass().getResourceAsStream(IMAGE_PATH));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(37);
            imageView.setFitHeight(37);
            imageView.setPreserveRatio(false);
            gridPaneTwo.add(imageView, clickedCol, clickedRow);

            List<int[]> sunkShipPositions = isShipSunk(currentRow, currentCol, tableTwo.getTable());
            if (sunkShipPositions != null) {
                System.out.println("Posiciones del barco derribado:");
                for (int[] position : sunkShipPositions) {
                    String IMAGE = "/com/example/navalbattlefinal/images/hundido.png";
                    Image imagen = new Image(getClass().getResourceAsStream(IMAGE));
                    ImageView imageViews = new ImageView(imagen);
                    imageView.setFitWidth(37);
                    imageView.setFitHeight(37);
                    imageView.setPreserveRatio(false);
                    gridPaneTwo.add(imageViews, position[1], position[0]);
                    System.out.println("Fila: " + position[0] + ", Columna: " + position[1]);
                }
            }
            else {
                imageView.setImage(image);
            }

        } else {
            System.out.println("El jugador no golpeó ningún barco enemigo en la posición " + clickedRow + ", " + clickedCol);
            String IMAGE_PATH = "/com/example/navalbattlefinal/images/agua.png";
            Image image = new Image(getClass().getResourceAsStream(IMAGE_PATH));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(37);
            imageView.setFitHeight(37);
            imageView.setPreserveRatio(false);
            gridPaneTwo.add(imageView, clickedCol, clickedRow);
        }
        if (allPlayersShipsSunk(tableTwo.getTable())) {
            System.out.println("¡Felicidades! Has ganado el juego.");
            gameFinished = true;
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("¡Juego Terminado!");
                alert.setHeaderText(null);
                alert.setContentText("¡Felicidades! Has ganado el juego.");
                alert.showAndWait();
                /**End the game or perform other necessary actions
                 **/
                Platform.exit();
            });
        } else {
            playerTurn = false;
            handleMachineTurn();
        }
    }

    /**
     * Handles the machine's turn to shoot at the player's grid.
     * The machine shoots at a random position on the player's board.
     * It checks the validity of the shot, updates the game state, and determines if the machine has won the game.
     */
    private void handleMachineTurn() {
        RandomShooter machineShooter = new RandomShooter(10, 10);
        int[] machineShotPosition;
        do {
            machineShotPosition = machineShooter.shoot();
        } while (!isValidShot(machineShotPosition) || isShotRepeated(machineShotPosition));
        if (machineShotPosition != null) {
            int machineRow = machineShotPosition[0] + 1;
            int machineCol = machineShotPosition[1] + 1;
            shotsTaken.add(machineShotPosition);

            if (tableOne.getTable()[machineRow][machineCol].equals("X")) {
                System.out.println("¡Impacto! La máquina golpeó un barco enemigo en la posición " + machineRow + ", " + machineCol);
                tableOne.getTable()[machineRow][machineCol] = "H";
                String IMAGE_PATH = "/com/example/navalbattlefinal/images/tocado.png";
                Image image = new Image(getClass().getResourceAsStream(IMAGE_PATH));
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(37);
                imageView.setFitHeight(37);
                imageView.setPreserveRatio(false);
                gridPane.add(imageView, machineCol, machineRow);

                List<int[]> sunkShipPositions = isShipSunk(currentRow, currentCol, tableOne.getTable());
                if (sunkShipPositions != null) {
                    System.out.println("Posiciones del barco derribado:");
                    for (int[] position : sunkShipPositions) {
                        String IMAGE = "/com/example/navalbattlefinal/images/hundido.png";
                        Image imagen = new Image(getClass().getResourceAsStream(IMAGE));
                        imageView.setImage(imagen);
                        gridPane.add(imageView, position[1], position[0]);
                        System.out.println("Fila: " + position[0] + ", Columna: " + position[1]);
                    }
                }else {
                    imageView.setImage(image);
                }

            } else {
                System.out.println("La máquina no golpeó ningún barco enemigo en la posición " + machineRow + ", " + machineCol);
                String IMAGE_PATH = "/com/example/navalbattlefinal/images/agua.png";
                Image image = new Image(getClass().getResourceAsStream(IMAGE_PATH));
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(37);
                imageView.setFitHeight(37);
                imageView.setPreserveRatio(false);
                imageView.setImage(image);
                gridPane.add(imageView, machineCol, machineRow);
            }
            /** Check if the machine has won
             **/
            if (allPlayersShipsSunk(tableOne.getTable())) {
                System.out.println("¡La máquina ha ganado el juego!");
                gameFinished = true;

                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("¡Juego Terminado!");
                    alert.setHeaderText("GAME OVER");
                    alert.setContentText("¡La máquina ha ganado el juego!");
                    alert.showAndWait();
                    /**End the game or perform other necessary actions
                     **/
                    Platform.exit();
                });
            } else {
                playerTurn = true;
            }
        }
    }
    /**
     * Verifies if a shot position has already been taken.
     * @param shotPosition An array containing the row and column of the shot position.
     * @return true if the shot has already been taken, false otherwise.
     */

    private boolean isShotRepeated(int[] shotPosition) {
        for (int[] shot : shotsTaken) {
            if (shot[0] == shotPosition[0] && shot[1] == shotPosition[1]) {
                return true;
            }
        }
        return false;
    }
    /**
     * Verifies if a shot position is valid.
     * @param shotPosition An array containing the row and column of the shot position.
     * @return true if the shot position is within the valid range of the board, false otherwise.
     */
    private boolean isValidShot(int[] shotPosition) {
        return shotPosition != null && shotPosition[0] >= 0 && shotPosition[0] < 10 && shotPosition[1] >= 0 && shotPosition[1] < 10;
    }
    /**
     * Checks if a specific cell in the table can be used to place a part of a ship.
     * @param row The row index of the cell.
     * @param cols The column index of the cell.
     * @param table The table representing the game board.
     * @return true if the cell is empty and can be used, false otherwise.
     */
    public boolean checkCell (int row, int cols, String[][] table){

        System.out.println();
        boolean r = false;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (table[row][cols] == ".") {
                    table[row][cols] = "X";
                    System.out.println("Rectángulo en fila " + row + ", columna " + cols);
                    r = true;
                }
            }
        }
        System.out.println(r);
        return r;
    }
    /**
     * Randomly places ships on the game board.
     * @param table The table representing the game board.
     */
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
                        System.out.println("Rectángulo enemy en fila " + row + ", columna " + col);

                        placed = true;
                    }
                }
            }
        }catch (Exception e) {
            System.err.println("Error placeShips: " + e.getMessage());
        }
    }
    /**
     * Checks if a ship can be placed at the given position on the game board.
     * @param table The table representing the game board.
     * @param row The starting row index for placing the ship.
     * @param col The starting column index for placing the ship.
     * @param size The size of the ship to be placed.
     * @param horizontal Flag indicating whether the ship should be placed horizontally (true) or vertically (false).
     * @return true if the ship can be placed at the given position, false otherwise.
     */
    private static boolean canPlaceShip(String[][] table, int row, int col, int size, boolean horizontal) {
        try{
            if (horizontal) {
                if (col + size > 11) return false;
                for (int i = 0; i < size; i++) {
                    if (!table[row][col + i].equals(".")) return false;
                }
            } else {
                if (row + size > 11) return false;
                for (int i = 0; i < size; i++) {
                    if (!table[row + i][col].equals(".")) return false;
                }
            }
        }catch (Exception e) {
            System.err.println("Error CanPlaceShip: " + e.getMessage());
        }
        return true;
    }
    /**
     * Places a ship on the game board.
     * @param table The table representing the game board.
     * @param row The starting row index for placing the ship.
     * @param col The starting column index for placing the ship.
     * @param size The size of the ship to be placed.
     * @param horizontal Flag indicating whether the ship should be placed horizontally (true) or vertically (false).
     */
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
    /**
     * Displays or hides the ship information on the user interface.
     *
     * @param show a boolean indicating whether to show (true) or hide (false) the ship information.
     */
    public void showBoats(boolean show){
        if (show) {
            vBox.getChildren().add(frigate.getFrigate());
            vBox.getChildren().add(submarine.getSubmarine());
            vBox.getChildren().add(destroyer.getDestroyer());
            vBox.getChildren().add(aircraftCarrier.getAircraftCarrier());

            mainVbox.getChildren().add(vBox);
            mainVbox.setLayoutY(200);
            mainVbox.setLayoutX(730);
            anchorPane.getChildren().add(mainVbox);

            textField.setText("4 Fragatas");
            anchorPane.getChildren().add(textField);
            textField.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, null)));
            textField.setLayoutX(820);
            textField.setLayoutY(190);
            textField.setStyle("-fx-font-size: 25px; -fx-font-family: 'Cambria Bold';-fx-font-weight: 'bolder';");

            textField2.setText("2 Submarinos");
            anchorPane.getChildren().add(textField2);
            textField2.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, null)));
            textField2.setLayoutX(820);
            textField2.setLayoutY(240);
            textField2.setStyle("-fx-font-size: 25px; -fx-font-family: 'Cambria Bold';-fx-font-weight: 'bolder';");

            textField3.setText("3 Destructores");
            anchorPane.getChildren().add(textField3);
            textField3.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, null)));
            textField3.setLayoutX(820);
            textField3.setLayoutY(290);
            textField3.setStyle("-fx-font-size: 25px; -fx-font-family: 'Cambria Bold';-fx-font-weight: 'bolder';");

            textField4.setText("1 Portaviones");
            anchorPane.getChildren().add(textField4);
            textField4.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, null)));
            textField4.setLayoutX(820);
            textField4.setLayoutY(340);
            textField4.setStyle("-fx-font-size: 25px; -fx-font-family: 'Cambria Bold';-fx-font-weight: 'bolder';");

        }
        else {
            mainVbox.setVisible(false);
            textField.setVisible(false);
            textField2.setVisible(false);
            textField3.setVisible(false);
            textField4.setVisible(false);
        }
    }
    public void serialize() throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("gameController.ser"))) {
            out.writeObject(this);
        }
    }
}
