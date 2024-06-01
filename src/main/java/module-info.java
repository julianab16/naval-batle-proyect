module com.example.navalbattlefinal {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.navalbattlefinal to javafx.fxml;
    exports com.example.navalbattlefinal;
    exports com.example.navalbattlefinal.view;
    opens com.example.navalbattlefinal.controller to javafx.fxml;
}