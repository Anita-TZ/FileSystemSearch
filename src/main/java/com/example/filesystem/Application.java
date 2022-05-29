package com.example.filesystem;

import com.example.filesystem.service.DBInitial;
import com.example.filesystem.service.DBWrite;
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

        //Create a text file with the above file system structure
        DBInitial data = new DBInitial(filePath);
        try {
            data.createFileTxt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Read and insert it into the database.
        DBWrite input = new DBWrite();
        input.getTxtData();
        input.OpenSQL();
    }

    public static void main(String[] args) {
        launch();
    }

}