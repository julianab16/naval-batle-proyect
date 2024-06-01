package com.example.navalbattlefinal.view.alert;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.Window;

public class AlertBox {
    public void showMessage(String title, String header, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.setWidth(1000); // Ajustar el ancho deseado
        alertStage.setHeight(1000); // Ajustar la altura deseada
        alert.showAndWait();
    }

}
