package com.example.filesystem.controller;

import com.example.filesystem.module.DBFile;
import com.example.filesystem.service.DBSql;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;


public class Controller {

    private ArrayList<DBFile> sql = new ArrayList<>();

    @FXML
    private TextField searchBar;
    @FXML
    private TextArea output;


    @FXML
    protected void onHelloButtonClick() {

        String request = searchBar.getText();
        //Start the MySQL
        DBSql search = new DBSql();
        search.OpenSQL("search", sql, request);

        sql.clear();
        sql.addAll(search.getDBfile());
        printOutput();


    }
    //Print output at TextArea
    private void printOutput() {
        StringBuilder body = new StringBuilder();
        if (!sql.isEmpty()) {
            for (DBFile dbFile : sql) {
                body.append(dbFile.getPath());
                body.append("\n");
            }
        } else {
            body.append("Not Found");
        }

        output.setText(body.toString());

    }


}