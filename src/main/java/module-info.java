module com.example.filesystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.filesystem to javafx.fxml;
    exports com.example.filesystem;
    opens com.example.filesystem.controller to javafx.fxml;
    exports com.example.filesystem.controller;
}