package com.example.navalbattlefinal.view.alert;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.Window;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * Utility class to display alert messages.
 */
public class AlertBox {
    /**
     * Shows a message in an alert box with customizable width and height.
     * @param title The title of the alert box.
     * @param header The header text of the alert box.
     * @param content The content text of the alert box.
     */
    public void showMessage(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.setWidth(900);
        alertStage.setHeight(800);
        alert.showAndWait();
    }
}
