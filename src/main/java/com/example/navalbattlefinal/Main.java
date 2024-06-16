package com.example.navalbattlefinal;

import com.example.navalbattlefinal.view.WelcomeStage;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * The Main class for launching the application.
 */
public class Main extends Application {

    /**
     * The main method to launch the application.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The start method for initializing the primary stage.
     * @param primaryStage The primary stage of the application.
     * @throws IOException If an I/O exception occurs.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        WelcomeStage.getInstance();
    }
}
