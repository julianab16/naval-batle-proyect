package com.example.navalbattlefinal.view;

import com.example.navalbattlefinal.controller.WelcomeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeStage extends Stage {
    private WelcomeController welcomeController;
    public WelcomeStage () throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/example/navalbattlefinal/welcome-view.fxml"));
        Parent root = loader.load();
        welcomeController = loader.getController();
        setTitle("Juego Batalla Naval - Bienvenido");
        Scene scene = new Scene(root);
        getIcons().add(
                new Image(
                        String.valueOf(getClass().getResource("/com/example/navalbattlefinal/images/flaticon.jpeg"))));
        setScene(scene);
        setResizable(false);
        show();
    }

    public WelcomeController getWelcomeController(){
        return welcomeController;
    }

    public static WelcomeStage getInstance() throws IOException{
        return WelcomeStageHolder.INSTANCE = new WelcomeStage();
    }

    private static class WelcomeStageHolder {
        private static WelcomeStage INSTANCE;
    }

    public static void deleteInstance() {
        WelcomeStageHolder.INSTANCE.close();
        WelcomeStageHolder.INSTANCE = null;
    }
}
