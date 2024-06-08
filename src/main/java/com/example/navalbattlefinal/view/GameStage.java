package com.example.navalbattlefinal.view;

import com.example.navalbattlefinal.controller.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
/** Authors:
 Juliana Melissa Bolaños Araujo-2372224
 Ivan David Ausecha Salamanca-2328223
 Laura Stefania Salazar Blanco-2327896
 Emails:
 juliana.araujo@correounivalle.edu.co
 ivan.ausecha@correounivalle.edu.co
 laura.blanco@coreounivalle.edu.co
 */

/**
 * The GameStage class represents the stage for the game screen.
 */
public class GameStage extends Stage {
    private GameController gameController;

    /**
     * Constructs a new GameStage object.
     * @throws IOException If an I/O exception occurs.
     */
    public GameStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/navalbattlefinal/game-view.fxml"));
        Parent root = loader.load();
        gameController = loader.getController();
        setTitle("Ventana juego de Sudoku");
        Scene scene = new Scene(root);
        getIcons().add(new Image(String.valueOf(getClass().getResource("/com/example/navalbattlefinal/images/flaticon.jpeg"))));
        setScene(scene);
        setResizable(false);
        show();

        try {
            gameController.serialize();
            System.out.println("El estado del juego ha sido serializado.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the GameController associated with this GameStage.
     * @return The GameController.
     */
    public GameController getGameController() {
        return gameController;
    }

    /**
     * Retrieves the singleton instance of the GameStage.
     * @return The singleton instance of the GameStage.
     * @throws IOException If an I/O exception occurs.
     */
    public static GameStage getInstance() throws IOException {
        return GameStageHolder.INSTANCE = new GameStage();
    }

    /**
     * Deletes the singleton instance of the GameStage.
     */
    public static void deleteInstance() {
        GameStageHolder.INSTANCE.close();
        GameStageHolder.INSTANCE = null;
    }

    /**
     * Holder class for the singleton instance of the GameStage.
     */
    private static class GameStageHolder {
        private static GameStage INSTANCE;
    }
}
