package com.example.navalbattlefinal.controller;

import com.example.navalbattlefinal.view.GameStage;
import com.example.navalbattlefinal.view.WelcomeStage;
import com.example.navalbattlefinal.view.alert.AlertBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class WelcomeController {
    @FXML
    void buttonStart(ActionEvent event) throws IOException {
        GameStage.getInstance().getGameController().inicialize();
        WelcomeStage.deleteInstance();
    }

    @FXML
    void buttonRules(ActionEvent event) {
        String title = "¿Como jugar?";
        String header = "Rules";
        String content = "Batalla Naval es un juego de estrategia y suerte para dos participantes (jugador vs computador).\n"+
                "\nEl objetivo del juego es hundir todos los barcos del oponente primero. \n"+
                "\nCada jugador tiene 2 tableros de 10x10: \n"+
                "\n-> Tablero de posición: Muestra la ubicación de tu flota y los disparos del oponente. Solo es de observación. \n"+
                "\n-> Tablero principal: Muestra el territorio del enemigo donde disparas para hundir sus barcos. Registra tus movimientos, mostrando disparos fallidos y aciertos. \n"+
                "\nCada jugador tiene una flota de 9 barcos: \n"+
                "\n• 1 portaaviones (4 casillas) "+"\n• 2 submarinos (3 casillas cada uno)"+"\n• 3 destructores (2 casillas cada uno)"+"\n• 4 fragatas (1 casilla cada una)"+
                "\n\nLos barcos se colocan horizontal o verticalmente en el tablero de posición. \n\nTerminología y movimientos: \n\n"+
                "• Agua: Disparo en una casilla vacía. Se marca con una X y pasa el turno al oponente. "+
                "\n• Tocado: Disparo en una parte de un barco mayor de 1 casilla. Se marca la parte tocada y el jugador vuelve a disparar. "+
                "\n• Hundido: Disparo en una fragata o un barco con todas sus partes tocadas. Se marca el barco completo y el jugador vuelve a disparar. Gana quien hunda toda la flota enemiga.";;

                new AlertBox().showMessage(title, header, content);
    }
}
