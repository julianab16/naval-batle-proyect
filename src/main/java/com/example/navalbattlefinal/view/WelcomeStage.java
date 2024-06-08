package com.example.navalbattlefinal.view;

import com.example.navalbattlefinal.controller.WelcomeController;
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
 * The WelcomeStage class represents the stage for the welcome screen of the game.
 */
public class WelcomeStage extends Stage {
    private WelcomeController welcomeController;

    /**
     * Constructs a new WelcomeStage object.
     * @throws IOException If an I/O exception occurs.
     */
    public WelcomeStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/navalbattlefinal/welcome-view.fxml"));
        Parent root = loader.load();
        welcomeController = loader.getController();
        setTitle("Juego Batalla Naval - Bienvenido");
        Scene scene = new Scene(root);
        getIcons().add(new Image(String.valueOf(getClass().getResource("/com/example/navalbattlefinal/images/flaticon.jpeg"))));
        setScene(scene);
        setResizable(false);
        show();
    }

    /**
     * Retrieves the WelcomeController associated with this WelcomeStage.
     * @return The WelcomeController.
     */
    public WelcomeController getWelcomeController() {
        return welcomeController;
    }

    /**
     * Retrieves the singleton instance of the WelcomeStage.
     * @return The singleton instance of the WelcomeStage.
     * @throws IOException If an I/O exception occurs.
     */
    public static WelcomeStage getInstance() throws IOException {
        return WelcomeStageHolder.INSTANCE = new WelcomeStage();
    }

    /**
     * Deletes the singleton instance of the WelcomeStage.
     */
    public static void deleteInstance() {
        WelcomeStageHolder.INSTANCE.close();
        WelcomeStageHolder.INSTANCE = null;
    }

    /**
     * Holder class for the singleton instance of the WelcomeStage.
     */
    private static class WelcomeStageHolder {
        private static WelcomeStage INSTANCE;
    }
}
