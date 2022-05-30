package com.example.filesystem;

import com.example.filesystem.service.FileManagement;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Setup-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("File System Management");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void init() {

        String filePath = Paths.get("").toAbsolutePath().toString();
        //search the file system
        FileManagement first = new FileManagement(filePath);
        //create the txt file
        first.createFileTxt();
        //read the txt file
        first.getTxtData();

    }

    public static void main(String[] args) {
        launch();
    }

}